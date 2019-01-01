package adventofcode.day11

case class Grid(serialNumber: Int) {

  def findAreaOfHighestPower : (Int, Int) = {
    val powerLevels: Seq[((Int, Int), Int)] = for(
      x <- 1 to 300;
      y <- 1 to 300
    ) yield {
      (x, y) -> FuelCell(x, y).powerLevel(serialNumber)
    }

    val powerLevelsMap = powerLevels.toMap

    val regionPowerLevels: Seq[((Int, Int), Int)] = for(
      x <- 1 to 298;
      y <- 1 to 298
    ) yield {
      val areaPowers =
        for (
          regionX <- x to x+2;
          regionY <- y to y+2
        ) yield {
          powerLevelsMap(regionX, regionY)
        }
      (x, y) -> areaPowers.sum
    }

    regionPowerLevels.maxBy(_._2)._1
  }
}
