package adventofcode.day18

import scala.io.Source

object Day18 extends App {
  val input = Source.fromFile("src/main/resources/day18/input.txt").getLines()
  val initialArea = new AreaParser().parseArea(input.toList)
  val result = new WoodsSimulator().runSimulation(initialArea, 10)
  println(s"Part One: ${result.getTotalResourceValue}")
}
