package adventofcode.dayfive

import scala.io.Source

object DayFive extends App {
  val input = Source.fromFile("src/main/resources/dayfive/input.txt").mkString
  val polymerReactor = new PolymerReactor()
  val reactedResult = polymerReactor.react(input)
  println(s"Part One: ${reactedResult.size}")

  val badUnit = new BadUnitFinder().findWorstUnit(input)
  println(s"Part Two: Unit ${badUnit._1} was the worst, resulting polymer size ${badUnit._2}")
}
