package adventofcode.daytwo

import scala.io.Source

object DayTwo extends App {
  val analyzer = new Analyzer()
  private val ids = Source.fromFile("src/main/resources/daytwo/Input.txt").getLines().toList
  val analyses = ids
    .map(analyzer.analyze)
  println(analyses.count(_.hasTwo) * analyses.count(_.hasThree))

  val matchingIds = new WordMatcher().findCloseMatch(ids)
  val partTwo = matchingIds.head.zipWithIndex.map {
    case(char, index) => if (char == matchingIds.last.charAt(index)) char else ""
  }
      .mkString
  println(partTwo)
}
