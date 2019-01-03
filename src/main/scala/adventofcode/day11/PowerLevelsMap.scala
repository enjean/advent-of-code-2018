package adventofcode.day11

case class PowerLevelsMap(powerLevels: Map[(Int, Int), Int]) {
  def powerOfSquareRegion(x: Int, y: Int, sideLength: Int): Int = {
    val powersInArea = for (
      regionX <- x until x + sideLength;
      regionY <- y until y + sideLength
    ) yield {
      powerLevels(regionX, regionY)
    }
    powersInArea.sum
  }

  def highestPowerRegionAtCoord(x: Int, y: Int) : (Int, Int) = {
    val maxSquareSize = Math.min(301 - x, 301 - y)
    val powersPerSideLength = (1 to maxSquareSize).map { squareSize =>
      squareSize -> powerOfSquareRegion(x, y, squareSize)
    }
    powersPerSideLength.maxBy(_._2)
  }
}

object PowerLevelsMap {
  def apply(serialNumber: Int): PowerLevelsMap = {
    val powerLevels: Seq[((Int, Int), Int)] = for (
      x <- 1 to 300;
      y <- 1 to 300
    ) yield {
      (x, y) -> FuelCell(x, y).powerLevel(serialNumber)
    }
    new PowerLevelsMap(powerLevels.toMap)
  }
}
