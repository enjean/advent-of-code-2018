package adventofcode.daysix

class GridGenerator {
  def generateGrid(coordinates: Seq[Coordinate]) : Map[Coordinate, Coordinate] = {
    val xCoords = coordinates.map(_.x)
    val yCoords = coordinates.map(_.y)

    val allCoords = for {
      x <- xCoords.min to xCoords.max
      y <- yCoords.min to yCoords.max
    } yield Coordinate(x, y)

    val grid = allCoords.filterNot(coordinates.contains(_))

    grid.map(coord => (coord, coord.closestTo(coordinates)))
      .filter(_._2.size == 1)
      .map(pair => (pair._1, pair._2.head))
      .toMap
  }
}
