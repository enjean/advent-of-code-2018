package adventofcode.dayfive

import scala.io.Source

object DayFive extends App {
  val input = Source.fromFile("src/main/resources/dayfive/input.txt").mkString
  val polymerReactor = new PolymerReactor()
  val reactedResult = polymerReactor.react(input)
  println(s"Part One: ${reactedResult.size}")
}
