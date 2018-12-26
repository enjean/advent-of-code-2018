package adventofcode.daysix

import org.scalatest.{FlatSpec, Matchers}

class CoordinateTest extends FlatSpec with Matchers{
  "manhattan distance" should "calculate correctly" in {
    Coordinate(0, 0).manhattanDistance(Coordinate(1, 0)) shouldBe 1
    Coordinate(0, 0).manhattanDistance(Coordinate(0, 1)) shouldBe 1
    Coordinate(0, 0).manhattanDistance(Coordinate(1, 1)) shouldBe 2
    Coordinate(2, 0).manhattanDistance(Coordinate(0, 0)) shouldBe 2
    Coordinate(0, 2).manhattanDistance(Coordinate(0, 0)) shouldBe 2
    Coordinate(1, 2).manhattanDistance(Coordinate(5, 6)) shouldBe 8
  }

  "closest" should "return closest by Manhattan distance" in {
    val A = Coordinate(1, 1)
    val B = Coordinate(1, 6)
    val C = Coordinate(8, 3)
    val D = Coordinate(3, 4)
    val E = Coordinate(5, 5)
    val F = Coordinate(8, 9)
    val coords = Seq(A, B, C, D, E, F)

    //    0123456789
    //  0 aaaaa.cccc
    //  1 aAaaa.cccc
    //  2 aaaddecccc
    //  3 aadddeccCc
    //  4 ..dDdeeccc
    //  5 bb.deEeecc
    //  6 bBb.eeee..
    //  7 bbb.eeefff
    //  8 bbb.eeffff
    //  9 bbb.ffffFf

    Coordinate(1, 2).closestTo(coords) shouldBe Seq(A)
    Coordinate(2, 6).closestTo(coords) shouldBe Seq(B)
    Coordinate(7, 7).closestTo(coords) shouldBe Seq(F)
    Coordinate(5, 1).closestTo(coords) should contain theSameElementsAs Seq(A, E)
  }

  "total distance" should "return sum of Manhattan distances" in {
    val A = Coordinate(1, 1)
    val B = Coordinate(1, 6)
    val C = Coordinate(8, 3)
    val D = Coordinate(3, 4)
    val E = Coordinate(5, 5)
    val F = Coordinate(8, 9)
    val coords = Seq(A, B, C, D, E, F)

    Coordinate(4, 3).totalDistance(coords) shouldBe 30
  }
}
