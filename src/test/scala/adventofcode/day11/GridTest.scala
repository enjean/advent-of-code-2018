package adventofcode.day11

import org.scalatest.{FlatSpec, Matchers}

class GridTest extends FlatSpec with Matchers {
  "findAreaOfHighestPower" should "return coords of 3x3 square" in {
    Grid(18).findAreaOfHighestPower shouldBe (33, 45)
    Grid(42).findAreaOfHighestPower shouldBe (21, 61)
  }

  "findVariableAreOfHighestPower" should "return coords and square size" in {
    Grid(18).findVariableAreOfHighestPower shouldBe (90, 269, 16)
    Grid(42).findVariableAreOfHighestPower shouldBe (232, 251, 12)
  }
}
