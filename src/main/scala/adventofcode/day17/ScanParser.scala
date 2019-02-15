package adventofcode.day17

class ScanParser {
  //x=495, y=2..7
  private val horizontalVein = """x=(\d+), y=(\d+)\.\.(\d+)""".r
  private val verticalVein = """y=(\d+), x=(\d+)\.\.(\d+)""".r
  def parseScan(lines: List[String]) : ScanModel = {
    val clayCoordinates = lines.foldLeft(Set[Coordinate]()) {
      (coordinatesSoFar, line) =>
        val newCoords: Seq[Coordinate] = line match {
          case horizontalVein(x, startY, endY) =>
            (startY.toInt to endY.toInt).map(y => Coordinate(x.toInt, y))
          case verticalVein(y, startX, endX) =>
            (startX.toInt to endX.toInt).map(x => Coordinate(x, y.toInt))
        }
        coordinatesSoFar ++ newCoords
    }
    ScanModel(clayCoordinates)
  }
}
