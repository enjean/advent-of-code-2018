package adventofcode.day17

import org.scalatest.{FlatSpec, Matchers}

class ScanParserTest extends FlatSpec with Matchers {
  "parseScan" should "build ScanModel" in {
    new ScanParser().parseScan(List(
      "x=495, y=2..7",
      "y=7, x=495..501",
      "x=501, y=3..7",
      "x=498, y=2..4",
      "x=506, y=1..2",
      "x=498, y=10..13",
      "x=504, y=10..13",
      "y=13, x=498..504"
    )) shouldBe ScanModel(Set(
      Coordinate(495, 2),
      Coordinate(495, 3),
      Coordinate(495, 4),
      Coordinate(495, 5),
      Coordinate(495, 6),
      Coordinate(495, 7),
      Coordinate(496, 7),
      Coordinate(497, 7),
      Coordinate(498, 7),
      Coordinate(499, 7),
      Coordinate(500, 7),
      Coordinate(501, 7),
      Coordinate(501, 3),
      Coordinate(501, 4),
      Coordinate(501, 5),
      Coordinate(501, 6),
      Coordinate(498, 2),
      Coordinate(498, 3),
      Coordinate(498, 4),
      Coordinate(506, 1),
      Coordinate(506, 2),
      Coordinate(498, 10),
      Coordinate(498, 11),
      Coordinate(498, 12),
      Coordinate(498, 13),
      Coordinate(504, 10),
      Coordinate(504, 11),
      Coordinate(504, 12),
      Coordinate(504, 13),
      Coordinate(498, 13),
      Coordinate(499, 13),
      Coordinate(500, 13),
      Coordinate(501, 13),
      Coordinate(502, 13),
      Coordinate(503, 13)
    ))
  }
}
