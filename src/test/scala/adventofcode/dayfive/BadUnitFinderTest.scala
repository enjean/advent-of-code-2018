package adventofcode.dayfive

import org.scalatest.{FlatSpec, Matchers}

class BadUnitFinderTest extends FlatSpec with Matchers {

  "website example" should "find c" in {
    new BadUnitFinder().findWorstUnit("dabAcCaCBAcCcaDA") shouldBe ('c', 4)
  }
}
