package adventofcode.day18

import adventofcode.day18.AcreType.AcreType

case class AreaState(acres: Vector[Vector[AcreType]]) {
  def evolve: AreaState = {
    AreaState(acres.indices.foldLeft(Vector[Vector[AcreType]]()) {
      (rowsSoFar, rowIndex) =>
        val row = acres(rowIndex)
        val newRow = row.indices.foldLeft(Vector[AcreType]()) {
          (rowSoFar, columnIndex) =>
            rowSoFar :+ getNextState(rowIndex, columnIndex)
        }
        rowsSoFar :+ newRow
    })
  }

  def getNextState(row: Int, column: Int): AcreType = {
    //    An open acre will become filled with trees if three or more adjacent acres contained trees. Otherwise, nothing happens.
    //    An acre filled with trees will become a lumberyard if three or more adjacent acres were lumberyards. Otherwise, nothing happens.
    //    An acre containing a lumberyard will remain a lumberyard if it was adjacent to at least one other lumberyard and
    //    at least one acre containing trees. Otherwise, it becomes open.
    val adjacent = getAdjacent(row, column).groupBy(identity)

    def adjacentOfType(acreType: AcreType) = adjacent.get(acreType).map(_.size).getOrElse(0)

    acres(row)(column) match {
      case AcreType.OpenGround =>
        if (adjacentOfType(AcreType.Trees) >= 3) AcreType.Trees else AcreType.OpenGround
      case AcreType.Trees =>
        if (adjacentOfType(AcreType.Lumberyard) >= 3) AcreType.Lumberyard else AcreType.Trees
      case AcreType.Lumberyard =>
        if (adjacentOfType(AcreType.Lumberyard) >= 1 && adjacentOfType(AcreType.Trees) >= 1) AcreType.Lumberyard else AcreType.OpenGround
    }
  }

  private def getAdjacent(row: Int, column: Int): Seq[AcreType] = {
    Seq(
      (row - 1, column - 1),
      (row - 1, column),
      (row - 1, column + 1),
      (row, column - 1),
      (row, column + 1),
      (row + 1, column - 1),
      (row + 1, column),
      (row + 1, column + 1)
    ).filter(_._1 >= 0)
      .filter(_._1 < acres.size)
      .filter(_._2 >= 0)
      .filter(_._2 < acres(0).size)
      .map { case (r, c) =>
        acres(r)(c)
      }
  }

  def getTotalResourceValue: Int = {
    val acreTypes = acres.flatten
    val lumber = acreTypes.count(acreType => acreType == AcreType.Lumberyard)
    val trees = acreTypes.count(acreType => acreType == AcreType.Trees)
    lumber * trees
  }
}
