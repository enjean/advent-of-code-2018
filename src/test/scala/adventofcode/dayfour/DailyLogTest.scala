package adventofcode.dayfour

import org.scalatest.{FlatSpec, Matchers}

class DailyLogTest extends FlatSpec with Matchers{
  "minutes asleep with one sleep period" should "count minutes of sleep period" in {
    DailyLog(99, Seq((40, 50))).minutesAsleep shouldBe 10
  }

  "minutes asleep with two sleep periods" should "count minutes of sleep period" in {
    DailyLog(10, Seq((5, 25), (30, 55))).minutesAsleep shouldBe 45
  }
}
