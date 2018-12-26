package adventofcode.daysix

import scala.io.Source

object DaySix extends App {
  private val coordRegex = """(\d+), (\d+)""".r

  val coordinates = Source.fromFile("src/main/resources/daysix/input.txt")
    .getLines()
    .map {
      case coordRegex(x, y) => Coordinate(x.toInt, y.toInt)
    }
    .toSeq

  val gridAnalyzer = new GridAnalyzer()
  println(s"Part One: Biggest area = ${gridAnalyzer.findLargestNonInfiniteArea(coordinates)}")
}
