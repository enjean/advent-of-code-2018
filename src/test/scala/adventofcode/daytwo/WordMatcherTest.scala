package adventofcode.daytwo

import org.scalatest.{FlatSpec, Matchers}

class WordMatcherTest extends FlatSpec with Matchers {
  private val wordMatcher = new WordMatcher()

  "website example" should "be fgij" in {
    wordMatcher.findCloseMatch(Seq("abcde",
      "fghij",
      "klmno",
      "pqrst",
      "fguij",
      "axcye",
      "wvxyz")) should contain theSameElementsAs Seq("fghij", "fguij")
  }

  "words with only first letter different" should "match" in {
    wordMatcher.findCloseMatch(Seq("abcde",
      "fghij",
      "klmno",
      "pqrst",
      "axcye",
      "wvxyz",
      "vghij")) should contain theSameElementsAs Seq("fghij", "vghij")
  }

  "words with same letters but different order" should "not match" in {
    wordMatcher.findCloseMatch(Seq("abcde",
      "edcba",
      "fghij",
      "klmno",
      "pqrst",
      "fguij",
      "axcye",
      "wvxyz")) should contain theSameElementsAs Seq("fghij", "fguij")
  }

  "first letter different" should "match" in {
    wordMatcher.oneLetterDiff("abcde", "bbcde") shouldBe true
  }

  "second letter different" should "match" in {
    wordMatcher.oneLetterDiff("abcde", "azcde") shouldBe true
  }

  "third letter different" should "match" in {
    wordMatcher.oneLetterDiff("abcde", "abzde") shouldBe true
  }

  "same letters different order" should "not match" in {
    wordMatcher.oneLetterDiff("abcde", "edcba") shouldBe false
  }
}
