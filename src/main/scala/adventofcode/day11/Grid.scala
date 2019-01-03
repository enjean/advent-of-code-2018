package adventofcode.day11

import scala.collection.immutable

case class Grid(serialNumber: Int) {

  def findAreaOfHighestPower : (Int, Int) = {
    val powerLevelsMap = PowerLevelsMap(serialNumber)

    val regionPowerLevels: Seq[((Int, Int), Int)] = for(
      x <- 1 to 298;
      y <- 1 to 298
    ) yield {
      (x, y) -> powerLevelsMap.powerOfSquareRegion(x, y, 3)
    }

    regionPowerLevels.maxBy(_._2)._1
  }

  def findVariableAreOfHighestPower : (Int, Int, Int) = {
    val powerLevelsMap = SummedAreaTable(serialNumber)

    val maxPowerAtCoord: immutable.Seq[((Int, Int), (Int, Int))] = for(
      x <- 1 to 300;
      y <- 1 to 300
    ) yield {
      (x, y) -> powerLevelsMap.highestPowerRegionAtCoord(x, y)
    }

    val max: ((Int, Int), (Int, Int)) = maxPowerAtCoord.maxBy(_._2._2)
    (max._1._1, max._1._2, max._2._1)
  }
}
