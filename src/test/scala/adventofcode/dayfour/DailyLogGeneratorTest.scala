package adventofcode.dayfour

import java.time.LocalDateTime

import org.scalatest.{FlatSpec, Matchers}

class DailyLogGeneratorTest extends FlatSpec with Matchers {
  "generate log" should "parse events into daily logs" in {
    val events = Seq(
      Event(LocalDateTime.of(1518, 11, 1, 0, 0), "Guard #10 begins shift"),
      Event(LocalDateTime.of(1518, 11, 1, 0, 5), "falls asleep"),
      Event(LocalDateTime.of(1518, 11, 1, 0, 25), "wakes up"),
      Event(LocalDateTime.of(1518, 11, 1, 0, 30), "falls asleep"),
      Event(LocalDateTime.of(1518, 11, 1, 0, 55), "wakes up"),
      Event(LocalDateTime.of(1518, 11, 1, 23, 58), "Guard #99 begins shift"),
      Event(LocalDateTime.of(1518, 11, 2, 0, 40), "falls asleep"),
      Event(LocalDateTime.of(1518, 11, 2, 0, 50), "wakes up"),
      Event(LocalDateTime.of(1518, 11, 3, 0, 5), "Guard #10 begins shift"),
      Event(LocalDateTime.of(1518, 11, 3, 0, 24), "falls asleep"),
      Event(LocalDateTime.of(1518, 11, 3, 0, 29), "wakes up"),
      Event(LocalDateTime.of(1518, 11, 4, 0, 2), "Guard #99 begins shift"),
      Event(LocalDateTime.of(1518, 11, 4, 0, 36), "falls asleep"),
      Event(LocalDateTime.of(1518, 11, 4, 0, 46), "wakes up"),
      Event(LocalDateTime.of(1518, 11, 5, 0, 3), "Guard #99 begins shift"),
      Event(LocalDateTime.of(1518, 11, 5, 0, 45), "falls asleep"),
      Event(LocalDateTime.of(1518, 11, 5, 0, 55), "wakes up"))

    //    Date   ID   Minute
    //                000000000011111111112222222222333333333344444444445555555555
    //                012345678901234567890123456789012345678901234567890123456789
    //    11-01  #10  .....####################.....#########################.....
    //    11-02  #99  ........................................##########..........
    //    11-03  #10  ........................#####...............................
    //    11-04  #99  ....................................##########..............
    //    11-05  #99  .............................................##########.....
    val expectedDailyLogs = Seq(
      DailyLog(10, Seq((5, 25), (30, 55))),
      DailyLog(99, Seq((40, 50))),
      DailyLog(10, Seq((24, 29))),
      DailyLog(99, Seq((36, 46))),
      DailyLog(99, Seq((45, 55)))
    )
    new DailyLogGenerator().generateDailyLogs(events) shouldBe expectedDailyLogs
  }


}
