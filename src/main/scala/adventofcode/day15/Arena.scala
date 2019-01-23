package adventofcode.day15

import adventofcode.day15.UnitType.UnitType

import scala.annotation.tailrec

case class Arena(units: Map[Coordinate, FightingUnit], map: Map[Coordinate, Char],
                 attackStrengths: Map[UnitType, Int] = Map(UnitType.Elf -> 3, UnitType.Goblin -> 3)) {

  def hasUnitOfType(unitType: UnitType) : Boolean = {
    units.exists(_._2.unitType == unitType)
  }

  def findClosestInRangeSquare(start: Coordinate, unitType: UnitType): Option[Coordinate] = {
//    println(s"Finding closest in range square to $start")
    val nearestInRangeSquares = nearestInRangeSquareSearch(unitType, start)
    if (nearestInRangeSquares.nonEmpty) Some(nearestInRangeSquares.minBy(c => (c.y, c.x)))
    else None
  }

  def nearestInRangeSquareSearch(unitType: UnitType, start: Coordinate): Seq[Coordinate] = {
    def squareIsInRangeOfType(c: Coordinate) : Boolean = squareIsInRangeOf(c, unitType)
    nearestMatchingSquareSearch(
      Seq(start -> 0),
      Set(),
      squareIsInRangeOfType,
      Seq()
    )
  }

  @tailrec
  final def nearestMatchingSquareSearch(coordinatesToProcess: Seq[(Coordinate, Int)],
                                 alreadyProcessed: Set[Coordinate],
                                 coordinateIsMatch: Coordinate => Boolean,
                                 closestFoundSoFar: Seq[(Coordinate, Int)]): Seq[Coordinate] = {

    if (coordinatesToProcess.isEmpty) closestFoundSoFar.map(_._1)
    else {
      val coordinateToConsider = coordinatesToProcess.head
      if (closestFoundSoFar.nonEmpty && coordinateToConsider._2 > closestFoundSoFar.head._2) {
        closestFoundSoFar.map(_._1)
      }
      else {
        val newCoordinatesToProcess = availableAdjacent(coordinateToConsider._1)
          .filterNot(c => coordinatesToProcess.map(_._1).contains(c))
          .filterNot(alreadyProcessed.contains)
          .map(_ -> (coordinateToConsider._2 + 1))

        val newFoundSoFar = if (coordinateIsMatch(coordinateToConsider._1)) {
          closestFoundSoFar :+ coordinateToConsider
        }
        else {
          closestFoundSoFar
        }

        nearestMatchingSquareSearch(coordinatesToProcess.tail ++ newCoordinatesToProcess,
          alreadyProcessed + coordinateToConsider._1,
          coordinateIsMatch,
          newFoundSoFar)
      }
    }
  }

  private def squareIsInRangeOf(coordinate: Coordinate, unitType: UnitType): Boolean = {
    coordinate.adjacent.exists(adjCoord => units.get(adjCoord).exists(_.unitType == unitType))
  }

  def findNextStepTowards(start: Coordinate, target: Coordinate): Coordinate = {
    def adjacentToStart(coordinate: Coordinate):Boolean = start.adjacent.contains(coordinate)
    val nearestAdjacentSquaresToTarget = nearestMatchingSquareSearch(
      Seq(target -> 0),
      Set(),
      adjacentToStart,
      Seq()
    )
    nearestAdjacentSquaresToTarget.minBy(c => (c.y, c.x))
  }

  def availableAdjacent(coordinate: Coordinate): Seq[Coordinate] =
    coordinate.adjacent
      .filterNot(c => map(c) == '#')
      .filterNot(c => units.contains(c))

  def moveUnit(unitLocation: Coordinate): (Arena, Coordinate) = {
//    println(s"Moving $unitLocation")
    val unitToMove = units(unitLocation)
    if (isInRange(unitLocation, unitToMove.opponentType) || !isInRangeSquare(unitToMove.opponentType)) {
      (this, unitLocation)
    }
    else {
      val targetSquare = findClosestInRangeSquare(unitLocation, unitToMove.opponentType)
//      println(s"Moving towards $targetSquare")
      val newLocation = targetSquare.map(square => findNextStepTowards(unitLocation, square)).getOrElse(unitLocation)
//      println(s"Taking step to $newLocation")
      val newUnits = units - unitLocation + (newLocation -> unitToMove)
      (this.copy(units = newUnits), newLocation)
    }
  }

  private def isInRange(coordinate: Coordinate, unitType: UnitType): Boolean = {
    coordinate.adjacent.exists(
      units.get(_).exists(_.unitType == unitType)
    )
  }

  private def isInRangeSquare(unitType: UnitType): Boolean = {
    units.filter(_._2.unitType == unitType)
      .flatMap(u => availableAdjacent(u._1))
      .nonEmpty
  }

  def fight(unitLocation: Coordinate): Arena = {
    val unitToFight = units(unitLocation)
    val adjacentOpponents = unitLocation.adjacent
      .map(loc => loc -> units.get(loc))
      .filter(_._2.exists(_.unitType == unitToFight.opponentType))
      .map(ao => ao._1 -> ao._2.get)
    if (adjacentOpponents.nonEmpty) {
      val opponentToFight = adjacentOpponents.minBy(opp => (opp._2.hitPoints, opp._1.y, opp._1.x))
      val opponentAfterAttack = opponentToFight._2.takeDamage(attackStrengths(unitToFight.unitType))
      val unitsAfterAttack = if (opponentAfterAttack.hitPoints <= 0) units - opponentToFight._1
      else units + (opponentToFight._1 -> opponentAfterAttack)
      this.copy(units = unitsAfterAttack)
    }
    else {
      this
    }
  }

  def score : Int = units.map(_._2.hitPoints).sum

  def withElfAttackPower(value: Int): Arena = this.copy(attackStrengths = this.attackStrengths + (UnitType.Elf -> value))

  def prettyPrint = {
    val maxX = map.keys.map(_.x).max
    val maxY = map.keys.map(_.y).max

    (0 to maxY).foreach { y =>
      val mapLine = (0 to maxX).foldLeft("") {
        (lineSoFar, x) =>
          lineSoFar + units.get(Coordinate(x, y)).map(_.toChar).getOrElse(map(Coordinate(x, y)))
      }
      val unitLine = units.filter(_._1.y == y).toList.sortBy(_._1.x).map(u => s"${u._2.toChar}(${u._2.hitPoints})").mkString(",")
      println(s"$mapLine $unitLine")
    }
  }
}
