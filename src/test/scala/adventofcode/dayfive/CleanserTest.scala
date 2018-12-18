package adventofcode.dayfive

import org.scalatest.{FlatSpec, Matchers}

class CleanserTest extends FlatSpec with Matchers{
  private val cleanser = new Cleanser()
  "cleanse" should "remove upper and lower instances of char" in {
//    Removing all A/a units produces dbcCCBcCcD. Fully reacting this polymer produces dbCBcD, which has length 6.
//    Removing all B/b units produces daAcCaCAcCcaDA. Fully reacting this polymer produces daCAcaDA, which has length 8.
//    Removing all C/c units produces dabAaBAaDA. Fully reacting this polymer produces daDA, which has length 4.
//    Removing all D/d units produces abAcCaCBAcCcaA. Fully reacting this polymer produces abCBAc, which has length 6.

    val polymer = "dabAcCaCBAcCcaDA"
    cleanser.cleanse(polymer, 'a') shouldBe "dbcCCBcCcD"
    cleanser.cleanse(polymer, 'b') shouldBe "daAcCaCAcCcaDA"
    cleanser.cleanse(polymer, 'c') shouldBe "dabAaBAaDA"
    cleanser.cleanse(polymer, 'd') shouldBe "abAcCaCBAcCcaA"
  }
}
