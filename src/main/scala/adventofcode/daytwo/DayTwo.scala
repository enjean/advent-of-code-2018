package adventofcode.daytwo

import scala.io.Source

object DayTwo extends App {
  val analyzer = new Analyzer()
  private val ids = Source.fromFile("src/main/resources/daytwo/Input.txt").getLines().toList
  val analyses = ids
    .map(analyzer.analyze)
  val twoCount = analyses.map(a => if (a.hasTwo) 1 else 0).sum
  val threeCount = analyses.map(a => if (a.hasThree) 1 else 0).sum
  println(twoCount * threeCount)

  val matchingIds = new WordMatcher().findCloseMatch(ids)
  val partTwo = matchingIds.head.zipWithIndex.map {
    case(char, index) => if (char == matchingIds.last.charAt(index)) char else ""
  }
      .mkString
//    .reduce(_ + _)
  println(partTwo)
}
