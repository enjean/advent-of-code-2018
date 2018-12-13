package adventofcode.dayone

import adventofcode.dayone.partone.FrequencyFinder
import org.scalatest.{FlatSpec, Matchers}

class PartOneTest extends FlatSpec with Matchers {
  private val finder: FrequencyFinder = new FrequencyFinder()

  "Example 1" should "be 3" in {
    finder.frequency("src/test/resources/Part1/Test1.txt") shouldBe 3
  }

  "Example 2" should "be 0" in {
    finder.frequency("src/test/resources/Part1/Test2.txt") shouldBe 0
  }

  "Example 3" should "be -6" in {
    finder.frequency("src/test/resources/Part1/Test3.txt") shouldBe -6
  }
}
