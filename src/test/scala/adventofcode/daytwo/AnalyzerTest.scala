package adventofcode.daytwo

import org.scalatest.{FlatSpec, Matchers}

class AnalyzerTest extends FlatSpec with Matchers {
  private val analyzer = new Analyzer
  "abcdef" should "does not have 2 or 3" in {
    analyzer.analyze("abcdef") shouldBe Analysis(false, false)
  }

  "bababc" should "count for both" in {
    analyzer.analyze("bababc") shouldBe Analysis(true, true)
  }

  "abbcde" should "has two but not three" in {
    analyzer.analyze("abbcde") shouldBe Analysis(true, false)
  }

  "abcccd" should "has three but not two" in {
    analyzer.analyze("abcccd") shouldBe Analysis(false, true)
  }

  "aabcdd" should "has two but not three" in {
    analyzer.analyze("aabcdd") shouldBe Analysis(true, false)
  }

  "abcdee" should "has two but not three" in {
    analyzer.analyze("abcdee") shouldBe Analysis(true, false)
  }

  "ababab" should "has three but not two" in {
    analyzer.analyze("ababab") shouldBe Analysis(false, true)
  }
}
