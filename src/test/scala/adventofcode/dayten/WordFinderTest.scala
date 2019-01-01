package adventofcode.dayten

import org.scalatest.FlatSpec

class WordFinderTest extends FlatSpec {

  "find word" should "find example" in {
    val skyGrid = SkyGrid(Seq(
      Point(Position(9,  1), Velocity(0,  2)),
      Point(Position(7,  0), Velocity(-1,  0)),
      Point(Position(3, -2), Velocity(-1,  1)),
      Point(Position(6, 10), Velocity(-2, -1)),
      Point(Position(2, -4), Velocity(2,  2)),
      Point(Position(-6, 10), Velocity(2, -2)),
      Point(Position(1,  8), Velocity(1, -1)),
      Point(Position(1,  7), Velocity(1,  0)),
      Point(Position(-3, 11), Velocity(1, -2)),
      Point(Position(7,  6), Velocity(-1, -1)),
      Point(Position(-2,  3), Velocity(1,  0)),
      Point(Position(-4,  3), Velocity(2,  0)),
      Point(Position(10, -3), Velocity(-1,  1)),
      Point(Position(5, 11), Velocity(1, -2)),
      Point(Position(4,  7), Velocity(0, -1)),
      Point(Position(8, -2), Velocity(0,  1)),
      Point(Position(15,  0), Velocity(-2,  0)),
      Point(Position(1,  6), Velocity(1,  0)),
      Point(Position(8,  9), Velocity(0, -1)),
      Point(Position(3,  3), Velocity(-1,  1)),
      Point(Position(0,  5), Velocity(0, -1)),
      Point(Position(-2,  2), Velocity(2,  0)),
      Point(Position(5, -2), Velocity(1,  2)),
      Point(Position(1,  4), Velocity(2,  1)),
      Point(Position(-2,  7), Velocity(2, -2)),
      Point(Position(3,  6), Velocity(-1, -1)),
      Point(Position(5,  0), Velocity(1,  0)),
      Point(Position(-6,  0), Velocity(2,  0)),
      Point(Position(5,  9), Velocity(1, -2)),
      Point(Position(14,  7), Velocity(-2,  0)),
      Point(Position(-3,  6), Velocity(2, -1))))

    val result = new WordFinder().findGridShowingWord(skyGrid)
    println(result.prettyPrint)
  }
}
