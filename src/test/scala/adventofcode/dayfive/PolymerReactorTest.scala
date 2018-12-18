package adventofcode.dayfive

import org.scalatest.{FlatSpec, Matchers}

class PolymerReactorTest extends FlatSpec with Matchers {

  private val polymerReactor = new PolymerReactor()
//  In aA, a and A react, leaving nothing behind.
//    In abBA, bB destroys itself, leaving aA. As above, this then destroys itself, leaving nothing.
//  In abAB, no two adjacent units are of the same type, and so nothing happens.
//  In aabAAB, even though aa and AA are of the same type, their polarities match, and so nothing happens.

  "lower and upper of same" should "react" in {
    polymerReactor.reactionPass("aA") shouldBe ""
  }

  "upper and lower of same" should "react" in {
    polymerReactor.reactionPass("Aa") shouldBe ""
  }

  "separated upper and lower of same" should "not react on one pass" in {
    polymerReactor.reactionPass("abBA") shouldBe "aA"
  }

  "no two adjacent units of the same type" should "not react" in {
    polymerReactor.reactionPass("abAB") shouldBe "abAB"
  }

  "matching polarities" should "not react" in {
    polymerReactor.reactionPass("aabAAB") shouldBe "aabAAB"
  }

  "two reactions" should "chain" in {
    polymerReactor.react("abBA") shouldBe ""
  }

//  Now, consider a larger example, dabAcCaCBAcCcaDA:
//
//    dabAcCaCBAcCcaDA  The first 'cC' is removed.
//  dabAaCBAcCcaDA    This creates 'Aa', which is removed.
//    dabCBAcCcaDA      Either 'cC' or 'Cc' are removed (the result is the same).
//    dabCBAcaDA        No further actions can be taken.
  "full reaction" should "chain reactions" in {
    polymerReactor.react("dabAcCaCBAcCcaDA") shouldBe "dabCBAcaDA"
  }
}
