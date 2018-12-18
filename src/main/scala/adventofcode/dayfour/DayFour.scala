package adventofcode.dayfour

import scala.io.Source

object DayFour extends App {
  val lines = Source.fromFile("src/main/resources/dayfour/input.txt")
    .getLines()
    .toList

  val events = new EventParser().parseAndSortEvents(lines)
  val dailyLogs = new DailyLogGenerator().generateDailyLogs(events)

  val logAnalyzer = new LogAnalyzer()

  val sleepiestGuard = logAnalyzer.findSleepiestGuard(dailyLogs)
  val guardsSleepiestMinute = logAnalyzer.findSleepiestMinuteForGuard(dailyLogs, sleepiestGuard)
  println(s"Part 1: Guard $sleepiestGuard is sleepiest at minute $guardsSleepiestMinute = ${sleepiestGuard * guardsSleepiestMinute}")
}
