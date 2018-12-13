package adventofcode.daythree

import org.scalatest.{FlatSpec, Matchers}

class OverlapCalculatorTest  extends FlatSpec with Matchers {
  "overlap from example" should "be four middle squares" in {
    new OverlapCalculator().overlapSquares(Seq(Claim("#1", 1,3,4,4), Claim("#2",3,1,4,4), Claim("#3",5,5,2,2))) should contain theSameElementsAs Seq(
      Square(3, 3),
      Square(3, 4),
      Square(4, 3),
      Square(4, 4)
    )
  }
}
