package adventofcode.daysix

class GridAnalyzer {

  def findLargestNonInfiniteArea(coordinates: Seq[Coordinate]): Int = {
    val grid = new GridGenerator().generateGrid(coordinates)

    val coordsByClosestTo = grid
      .groupBy(_._2)
      .filterNot(entry => areaIsInfinite(grid, entry._1))
    val areasForCoordinates = coordsByClosestTo
      .map(cm => (cm._1, cm._2.size))

    areasForCoordinates.maxBy(_._2)._2 + 1 // For coord itself
  }

  def areaIsInfinite(grid: Map[Coordinate, Coordinate], coordinate: Coordinate): Boolean = {
    val xCoords = grid.map(_._1.x)
    val minX = xCoords.min
    val maxX = xCoords.max
    val yCoords = grid.map(_._1.y)
    val minY = yCoords.min
    val maxY = yCoords.max

    grid.exists {
      case (coord, closest) =>
        closest == coordinate &&
          (
            coord.x == minX ||
              coord.x == maxX ||
              coord.y == minY ||
              coord.y == maxY
            )
    }
  }
}
