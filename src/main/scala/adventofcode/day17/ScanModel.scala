package adventofcode.day17

case class ScanModel(claySpots: Set[Coordinate]) {

  def isClay(coordinate: Coordinate) : Boolean = claySpots.contains(coordinate)

  def minY: Int = claySpots.map(_.y).min
  def maxY: Int = claySpots.map(_.y).max

  def prettyPrint(undergroundState: UndergroundState): Unit = {
    val minX = Math.min(claySpots.map(_.x).min, undergroundState.waterHasMovedThrough.map(_.x).min)
    val maxX = claySpots.map(_.x).max
    (0 to maxY).foreach { y =>
      val line = (minX to maxX).map { x =>
        if (x == 500 && y == 0) '+'
        else if (undergroundState.waterAtRest.contains(Coordinate(x, y))) '~'
        else if (undergroundState.waterHasMovedThrough.contains(Coordinate(x, y))) '|'
        else if (claySpots.contains(Coordinate(x, y))) '#'
        else '.'
      }.mkString
      println(s"$line")
    }
  }
}
