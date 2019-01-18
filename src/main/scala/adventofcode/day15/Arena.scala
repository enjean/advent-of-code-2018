package adventofcode.day15

import adventofcode.day15.UnitType.UnitType

import scala.annotation.tailrec

case class Arena(units: Map[Coordinate, FightingUnit], map: Map[Coordinate, Char]) {

  def hasUnitOfType(unitType: UnitType) : Boolean = {
    units.exists(_._2.unitType == unitType)
  }

  def findClosestInRangeSquare(start: Coordinate, unitType: UnitType): Option[Coordinate] = {
    println(s"Finding closest in range square to $start")
    val nearestInRangeSquares = nearestInRangeSquareSearch(
      unitType,
      Seq(start -> 0),
      Set(),
      Seq()
    )
    if (nearestInRangeSquares.nonEmpty) Some(nearestInRangeSquares.minBy(c => (c.y, c.x)))
    else None
  }

  def nearestInRangeSquareSearch(unitType: UnitType,
                                 coordinatesToProcess: Seq[(Coordinate, Int)],
                                 alreadyProcessed: Set[Coordinate],
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

        val newFoundSoFar = if (squareIsInRangeOf(coordinateToConsider._1, unitType)) {
          closestFoundSoFar :+ coordinateToConsider
        }
        else {
          closestFoundSoFar
        }

        nearestInRangeSquareSearch(unitType,
          coordinatesToProcess.tail ++ newCoordinatesToProcess,
          alreadyProcessed + coordinateToConsider._1,
          newFoundSoFar)
      }
    }
  }

  private def squareIsInRangeOf(coordinate: Coordinate, unitType: UnitType): Boolean = {
    coordinate.adjacent.exists(adjCoord => units.get(adjCoord).exists(_.unitType == unitType))
  }

  def findNextStepTowards(start: Coordinate, target: Coordinate): Coordinate = {
    val distancesToFromNeighbors = availableAdjacent(start)
      .map(c => c -> distanceTo(c, target))
    distancesToFromNeighbors
      .filter(_._2.isDefined)
      .minBy(c => (c._2, c._1.y, c._1.x))
      ._1
  }

  def distanceTo(from: Coordinate, to: Coordinate): Option[Int] = {
    distanceTo(from, to, Set())
  }

  def distanceTo(from: Coordinate, to: Coordinate, onPath: Set[Coordinate]): Option[Int] = {
    if (from == to) Some(0)
    else {
      val nextLevel = availableAdjacent(from)
        .filterNot(onPath.contains)
      if (nextLevel.isEmpty) None
      else {
        val childDistances = nextLevel
          .flatMap(adj => distanceTo(adj, to, onPath + from).map(_ + 1))

        println(s"$from $onPath $nextLevel $childDistances")
        if (childDistances.nonEmpty) Some(childDistances.min)
        else None
      }
    }
  }

  def availableAdjacent(coordinate: Coordinate): Seq[Coordinate] =
    coordinate.adjacent
      .filterNot(c => map(c) == '#')
      .filterNot(c => units.contains(c))

  def moveUnit(unitLocation: Coordinate): (Arena, Coordinate) = {
    println(s"Moving $unitLocation")
    val unitToMove = units(unitLocation)
    if (isInRange(unitLocation, unitToMove.opponentType) || !isInRangeSquare(unitToMove.opponentType)) {
      (this, unitLocation)
    }
    else {
      val targetSquare = findClosestInRangeSquare(unitLocation, unitToMove.opponentType)
      println(s"Moving towards $targetSquare")
      val newLocation = targetSquare.map(square => findNextStepTowards(unitLocation, square)).getOrElse(unitLocation)
      println(s"Taking step to $newLocation")
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
      val opponentAfterAttack = opponentToFight._2.takeDamage(3)
      val unitsAfterAttack = if (opponentAfterAttack.hitPoints <= 0) units - opponentToFight._1
      else units + (opponentToFight._1 -> opponentAfterAttack)
      this.copy(units = unitsAfterAttack)
    }
    else {
      this
    }
  }

  def score : Int = units.map(_._2.hitPoints).sum
}
