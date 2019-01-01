package adventofcode.day11

import org.scalatest.{FlatSpec, Matchers}

class FuelCellTest extends FlatSpec with Matchers {
  "power level" should "be computed per formula" in {
    FuelCell(3, 5).powerLevel(8) shouldBe 4
    FuelCell(122, 79).powerLevel(57) shouldBe -5
    FuelCell(217, 196).powerLevel(39) shouldBe 0
    FuelCell(101, 153).powerLevel(71) shouldBe 4
  }
}
