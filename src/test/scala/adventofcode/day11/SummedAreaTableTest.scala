package adventofcode.day11

import org.scalatest.{FlatSpec, Matchers}

class SummedAreaTableTest extends FlatSpec with Matchers {
  "powerOfSquareRegion" should "work for size 3" in {
    SummedAreaTable(18).powerOfSquareRegion(33, 45, 3) shouldBe 29
    SummedAreaTable(42).powerOfSquareRegion(21, 61, 3) shouldBe 30
  }

  "powerOfSquareRegion" should "work for sizes other than 3" in {
    SummedAreaTable(18).powerOfSquareRegion(90, 269, 16) shouldBe 113
    SummedAreaTable(42).powerOfSquareRegion(232, 251, 12) shouldBe 119
    SummedAreaTable(18).powerOfSquareRegion(33, 45, 1) shouldBe 4
  }

  "highestPowerRegionAtCoord" should "return power and square size" in {
    SummedAreaTable(18).highestPowerRegionAtCoord(90, 269) shouldBe (16, 113)
    SummedAreaTable(42).highestPowerRegionAtCoord(232, 251) shouldBe (12, 119)
  }
}
