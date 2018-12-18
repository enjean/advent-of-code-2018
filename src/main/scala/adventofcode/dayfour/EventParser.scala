package adventofcode.dayfour

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventParser {
  def parseAndSortEvents(eventStrings: Seq[String]): Seq[Event] = {
    //[1518-11-01 00:00] Guard #10 begins shift
    val eventRegex = "\\[(.*)\\] (.*)".r
    eventStrings.map {
      case eventRegex(ts, es) => Event(LocalDateTime.parse(ts, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), es)
    }
      .sortWith((e1, e2) => e1.timestamp.isBefore(e2.timestamp))
  }
}
