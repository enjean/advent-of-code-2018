package adventofcode.day11

import org.scalatest.{FlatSpec, Matchers}

class GridTest extends FlatSpec with Matchers {
  "findAreaOfHighestPower" should "return coords of 3x3 square" in {
    Grid(18).findAreaOfHighestPower shouldBe (33, 45)
    Grid(42).findAreaOfHighestPower shouldBe (21, 61)
  }
}
