package adventofcode.day17

import scala.io.Source

object Day17 extends App {

  val scanModel = new ScanParser().parseScan(Source.fromFile("src/main/resources/day17/input.txt").getLines().toList)
 // scanModel.prettyPrint(UndergroundState(Set(), Set()))

  val waterFlowSimulator = new WaterFlowSimulator(scanModel)

  val result = waterFlowSimulator.flowWaterFrom(Coordinate(500, 0))
  println("done")
  val waterAtRest = result.waterAtRest.filter(c => c.y >= scanModel.minY)
  val waterHasMovedThrough = result.waterHasMovedThrough.filter(c => c.y >= scanModel.minY)

  //scanModel.prettyPrint(result)
  println(s"Part One: ${waterAtRest.size} atrRest + ${waterHasMovedThrough.size} Has Moved Through = ${waterAtRest.size + waterHasMovedThrough.size} ")
}
