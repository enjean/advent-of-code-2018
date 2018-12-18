package adventofcode.dayfour

import org.scalatest.{FlatSpec, Matchers}

class DailyLogTest extends FlatSpec with Matchers{
  "minutes asleep with one sleep period" should "count minutes of sleep period" in {
    DailyLog(99, Seq((40, 50))).minutesAsleep shouldBe 10
  }

  "minutes asleep with two sleep periods" should "count minutes of sleep period" in {
    DailyLog(10, Seq((5, 25), (30, 55))).minutesAsleep shouldBe 45
  }

  "sleep minutes with one sleep period" should "have all minutes" in {
    DailyLog(99, Seq((40, 50))).sleepMinutesList shouldBe Seq(40, 41, 42, 43, 44, 45, 46, 47, 48, 49)
  }

  "sleep minutes with two sleep periods" should "have minutes from both" in {
    DailyLog(10, Seq((5, 25), (30, 55))).sleepMinutesList shouldBe Seq(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
      30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54)
  }
}
