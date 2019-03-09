package adventofcode.day18

import org.scalatest.{FlatSpec, Matchers}

class WoodsSimulatorTest extends FlatSpec with Matchers {
  "10 minute example" should "match resource count" in {
    val initialArea = new AreaParser().parseArea(
      List(
        ".#.#...|#.",
        ".....#|##|",
        ".|..|...#.",
        "..|#.....#",
        "#.#|||#|#|",
        "...#.||...",
        ".|....|...",
        "||...#|.#|",
        "|.||||..|.",
        "...#.|..|."
      )
    )
    new WoodsSimulator().runSimulation(initialArea, 10).getTotalResourceValue shouldBe 1147
  }
}
