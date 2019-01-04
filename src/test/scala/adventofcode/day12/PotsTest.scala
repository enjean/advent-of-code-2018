package adventofcode.day12

import org.scalatest.{FlatSpec, Matchers}

class PotsTest extends FlatSpec with Matchers {
  "currentState" should "show LLCRR notation" in {
    Pots(Set(2)).currentState(2) shouldBe "..#.."
    Pots(Set(0, 1, 3, 4)).currentState(2) shouldBe "##.##"
    Pots(Set(1, 2, 4)).currentState(2) shouldBe ".##.#"
    Pots(Set(-1, 1)).currentState(0) shouldBe ".#.#."
  }

  "willHavePlantInNextGen" should "return whether current state matches pattern" in {
    Pots(Set(1, 2, 4)).willHavePlantInNextGen(2, Set(".##.#")) shouldBe true
    Pots(Set(1, 2, 4)).willHavePlantInNextGen(3, Set(".##.#")) shouldBe false
  }

  "growGeneration" should "apply patterns" in {
    val initialState = Pots("#..#.#..##......###...###")
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
    "####."
    )

    val nextGeneration = initialState.growGeneration(growthPatterns)
    nextGeneration.potsWithPlants shouldBe Set(0, 4, 9, 15, 18, 21, 24)
  }

  "growGeneration" should "grow past upper bound" in {
    val initialState = Pots("#...#....#.....#..#..#..#")
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
      "####."
    )

    val nextGeneration = initialState.growGeneration(growthPatterns)
    nextGeneration.potsWithPlants.contains(25) shouldBe true
  }

  "growGeneration" should "grow past lower bound" in {
    val initialState = Pots("##..##...##....#..#..#..##")
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
      "####."
    )

    val nextGeneration = initialState.growGeneration(growthPatterns)
    nextGeneration.potsWithPlants.contains(-1) shouldBe true
  }
}
