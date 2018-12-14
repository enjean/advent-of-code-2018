package adventofcode.daytwo

import scala.io.Source

object DayTwo extends App {
  val analyzer = new Analyzer()
  private val ids = Source.fromFile("src/main/resources/daytwo/Input.txt").getLines().toList
  val analyses = ids
    .map(analyzer.analyze)
  println(analyses.count(_.hasTwo) * analyses.count(_.hasThree))

  val matchingIds = new WordMatcher().findCloseMatch(ids)
  println(matchingIds.head intersect matchingIds.last)
}
