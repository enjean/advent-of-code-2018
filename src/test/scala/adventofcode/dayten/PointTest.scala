package adventofcode.dayten

import org.scalatest.{FlatSpec, Matchers}

class PointTest extends FlatSpec with Matchers{

  "advance" should "change position based on velocity" in {
    val point = Point(Position(3, 9), Velocity(1, -2))
    val atSecondOne = point.advance
    atSecondOne shouldBe Point(Position(4, 7), Velocity(1, -2))
  }

  "isAdjacentTo" should "be true is next to, false otherwise" in {
    Position(1, 2).isAdjacentTo(Position(1, 3)) shouldBe true
    Position(1, 2).isAdjacentTo(Position(1, 1)) shouldBe true
    Position(1, 2).isAdjacentTo(Position(2, 2)) shouldBe true
    Position(1, 2).isAdjacentTo(Position(0, 2)) shouldBe true
    Position(1, 2).isAdjacentTo(Position(1, 4)) shouldBe false
    Position(1, 2).isAdjacentTo(Position(1, 0)) shouldBe false
    Position(1, 2).isAdjacentTo(Position(3, 2)) shouldBe false
    Position(1, 2).isAdjacentTo(Position(-1, 2)) shouldBe false
  }

  "isAdjacentTo" should "be true for diagonal" in {
    Position(1, 2).isAdjacentTo(Position(2, 3)) shouldBe true
  }
}
