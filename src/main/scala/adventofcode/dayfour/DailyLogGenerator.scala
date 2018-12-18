package adventofcode.dayfour

class DailyLogGenerator {

  // Guard #10 begins shift
  private val guardStartsRegex = """Guard #(\d+) begins shift""".r

  case class LogCollector(guard: Int, fellAsleep: Int, sleepTimes: Seq[(Int, Int)])
  case class Acc(finishedLogs: Seq[LogCollector], currentLog: Option[LogCollector])

  def generateDailyLogs(events: Seq[Event]) : Seq[DailyLog] = {
    val finalAcc = events.foldLeft(Acc(Seq(), None)) {
      (acc, event) =>
            event.action match {
              case guardStartsRegex(guard) => Acc(acc.currentLog.map(acc.finishedLogs :+ _).getOrElse(acc.finishedLogs),
                Some(LogCollector(guard.toInt, 0, Nil)))
              case "falls asleep" => acc.copy(currentLog = acc.currentLog.map(_.copy(fellAsleep = event.timestamp.getMinute)))
              case "wakes up" => acc.copy(currentLog = acc.currentLog.map(cl => cl.copy(sleepTimes = cl.sleepTimes :+ (cl.fellAsleep, event.timestamp.getMinute))))
            }
        }

    val logCollectors = finalAcc.finishedLogs :+ finalAcc.currentLog.get
    logCollectors.map(lc => DailyLog(lc.guard, lc.sleepTimes))
  }
}
