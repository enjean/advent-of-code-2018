package adventofcode.dayfour

import org.scalatest.{FlatSpec, Matchers}

class LogAnalyzerTest extends FlatSpec with Matchers {

  private val logAnalyzer = new LogAnalyzer()

  "findSleepiestGuard" should "find guard with most time sleeping" in {
    val dailyLogs = Seq(
      DailyLog(10, Seq((5, 25), (30, 55))),
      DailyLog(99, Seq((40, 50))),
      DailyLog(10, Seq((24, 29))),
      DailyLog(99, Seq((36, 46))),
      DailyLog(99, Seq((45, 55)))
    )

    logAnalyzer.findSleepiestGuard(dailyLogs) shouldBe 10
  }

  "findSleepiestMinuteForGuard" should "find minute that guard is most asleep with times asleep" in {
    val dailyLogs = Seq(
      DailyLog(10, Seq((5, 25), (30, 55))),
      DailyLog(99, Seq((40, 50))),
      DailyLog(10, Seq((24, 29))),
      DailyLog(99, Seq((36, 46))),
      DailyLog(99, Seq((45, 55)))
    )

    logAnalyzer.findSleepiestMinuteForGuard(dailyLogs, 10) shouldBe (24, 2)
  }

  "find guard with sleepiest minute" should "find guard asleep the most on a given minute with that minute" in {
    val dailyLogs = Seq(
      DailyLog(10, Seq((5, 25), (30, 55))),
      DailyLog(99, Seq((40, 50))),
      DailyLog(10, Seq((24, 29))),
      DailyLog(99, Seq((36, 46))),
      DailyLog(99, Seq((45, 55)))
    )

    logAnalyzer.findGuardWithSleepiestMinute(dailyLogs) shouldBe (99, 45)
  }
}
