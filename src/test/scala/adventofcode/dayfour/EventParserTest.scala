package adventofcode.dayfour

import java.time.LocalDateTime

import scala.util.Random
import org.scalatest.{FlatSpec, Matchers}

class EventParserTest extends FlatSpec with Matchers {
  "parse" should "convert to event and sort" in {
    new EventParser().parseAndSortEvents(Seq("[1518-11-01 00:00] Guard #10 begins shift")).head shouldBe Event(LocalDateTime.of(1518, 11, 1, 0, 0), "Guard #10 begins shift")
    new EventParser().parseAndSortEvents(
      Random.shuffle(Seq(
        "[1518-11-01 00:00] Guard #10 begins shift",
        "[1518-11-01 00:05] falls asleep",
        "[1518-11-01 00:25] wakes up",
        "[1518-11-01 00:30] falls asleep",
        "[1518-11-01 00:55] wakes up",
        "[1518-11-01 23:58] Guard #99 begins shift",
        "[1518-11-02 00:40] falls asleep",
        "[1518-11-02 00:50] wakes up",
        "[1518-11-03 00:05] Guard #10 begins shift",
        "[1518-11-03 00:24] falls asleep",
        "[1518-11-03 00:29] wakes up",
        "[1518-11-04 00:02] Guard #99 begins shift",
        "[1518-11-04 00:36] falls asleep",
        "[1518-11-04 00:46] wakes up",
        "[1518-11-05 00:03] Guard #99 begins shift",
        "[1518-11-05 00:45] falls asleep",
        "[1518-11-05 00:55] wakes up"
      ))
    ) should contain theSameElementsAs Seq(
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
  }


}
