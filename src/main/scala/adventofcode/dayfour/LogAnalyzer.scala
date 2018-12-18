package adventofcode.dayfour

class LogAnalyzer {

  def findSleepiestGuard(dailyLogs: Seq[DailyLog]) : Int = {
    dailyLogs.groupBy(_.guard)
        .maxBy(dlGroup => dlGroup._2.map(_.minutesAsleep).sum)
        ._1
  }

  def findSleepiestMinuteForGuard(dailyLogs: Seq[DailyLog], guard: Int): Int = {
    dailyLogs.filter(_.guard == guard)
        .flatMap(_.sleepMinutesList)
        .groupBy(identity)
        .maxBy(_._2.size)
        ._1
  }
}
