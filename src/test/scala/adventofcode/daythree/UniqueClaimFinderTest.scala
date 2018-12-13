package adventofcode.daythree

import org.scalatest.{FlatSpec, Matchers}

class UniqueClaimFinderTest extends FlatSpec with Matchers {
  "unique from example" should "be 3" in {
    new UniqueClaimFinder().uniqueClaim(Seq(Claim("#1", 1,3,4,4), Claim("#2",3,1,4,4), Claim("#3",5,5,2,2))).id shouldBe "#3"
  }
}
