package adventofcode.daythree

import org.scalatest.{FlatSpec, Matchers}

class ClaimTest extends FlatSpec with Matchers {
  "parse from string" should "parse all fields" in {
    Claim("#123 @ 3,2: 5x4") shouldBe Claim("#123", 3, 2, 5, 4)
  }

  "get squares" should "return all squares in rectangle" in {
    Claim("#123", 3, 2, 5, 4).getSquares should contain theSameElementsAs
      Seq(
        Square(3, 2),
        Square(3, 3),
        Square(3, 4),
        Square(3, 5),
        Square(4, 2),
        Square(4, 3),
        Square(4, 4),
        Square(4, 5),
        Square(5, 2),
        Square(5, 3),
        Square(5, 4),
        Square(5, 5),
        Square(6, 2),
        Square(6, 3),
        Square(6, 4),
        Square(6, 5),
        Square(7, 2),
        Square(7, 3),
        Square(7, 4),
        Square(7, 5)
      )
  }
}
