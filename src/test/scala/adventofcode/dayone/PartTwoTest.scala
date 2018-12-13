package adventofcode.dayone

import adventofcode.dayone.parttwo.DuplicateFrequencyFinder
import org.scalatest.{FlatSpec, Matchers}

class PartTwoTest extends FlatSpec with Matchers {

  private val duplicateFrequencyFinder = new DuplicateFrequencyFinder()

  "Example 1" should "be 2" in {
    duplicateFrequencyFinder.findFirstDupe("src/test/resources/Part2/Example1.txt") shouldBe 2
  }

  "Example 2" should "be 0" in {
    duplicateFrequencyFinder.findFirstDupe("src/test/resources/Part2/Example2.txt") shouldBe 0
  }

  "Example 3" should "be 10" in {
    duplicateFrequencyFinder.findFirstDupe("src/test/resources/Part2/Example3.txt") shouldBe 10
  }

  "Example 4" should "be 5" in {
    duplicateFrequencyFinder.findFirstDupe("src/test/resources/Part2/Example4.txt") shouldBe 5
  }

  "Example 5" should "be 14" in {
    duplicateFrequencyFinder.findFirstDupe("src/test/resources/Part2/Example5.txt") shouldBe 14
  }
}
