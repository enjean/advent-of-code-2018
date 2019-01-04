package adventofcode.day12

import org.scalatest.{FlatSpec, Matchers}

class GrowthSimulatorTest extends FlatSpec with Matchers {
  "runSimulation" should "return sum of indices of pots containing plants after 20 generations" in {
    val initialState = "#..#.#..##......###...###"
    val growthPatterns = Set(
      "...##",
      "..#..",
      ".#...",
      ".#.#.",
      ".#.##",
      ".##..",
      ".####",
      "#.#.#",
      "#.###",
      "##.#.",
      "##.##",
      "###..",
      "###.#",
      "####.")

    new GrowthSimulator().runSimulation(initialState, growthPatterns) shouldBe 325

  }
}
