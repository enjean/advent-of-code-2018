package adventofcode.dayfour

class LogAnalyzer {

  def findSleepiestGuard(dailyLogs: Seq[DailyLog]) : Int = {
    dailyLogs.groupBy(_.guard)
        .maxBy(dlGroup => dlGroup._2.map(_.minutesAsleep).sum)
        ._1
  }

  def findSleepiestMinuteForGuard(dailyLogs: Seq[DailyLog], guard: Int): (Int, Int) = {
    val sleepiestCollection = dailyLogs.filter(_.guard == guard)
        .flatMap(_.sleepMinutesList)
        .groupBy(identity)
        .maxBy(_._2.size)

    (sleepiestCollection._1, sleepiestCollection._2.size)
  }

  def findGuardWithSleepiestMinute(dailyLogs: Seq[DailyLog]) : (Int, Int) = {
    val sleepiestMinutesPerGuard = dailyLogs.groupBy(_.guard)
      .filter(dlByGuard => dlByGuard._2.map(_.minutesAsleep).sum > 0)
      .map(guardLogs => (guardLogs._1, guardLogs._2.flatMap(_.sleepMinutesList).groupBy(identity).maxBy(_._2.size)))
    val maxGuard = sleepiestMinutesPerGuard.maxBy(_._2._2.size)
    (maxGuard._1, maxGuard._2._1)
  }
}
