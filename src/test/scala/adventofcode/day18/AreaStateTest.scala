package adventofcode.day18

import org.scalatest.{Matchers, WordSpec}

class AreaStateTest extends WordSpec with Matchers {


  "getNextState" should {
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

    "for open acre" should {
      "fill with trees if three or more adjacent acres contained trees" in {
        initialArea.getNextState(3, 4) shouldBe AcreType.Trees
        initialArea.getNextState(9, 4) shouldBe AcreType.Trees
      }

      "stay open if less than three adjacent acres contained trees" in {
        initialArea.getNextState(1, 1) shouldBe AcreType.OpenGround
        initialArea.getNextState(9, 9) shouldBe AcreType.OpenGround
      }
    }

    "for tree acre" should {
      "become a lumberyard if three or more adjacent acres were lumberyards" in {
        initialArea.getNextState(0, 7) shouldBe AcreType.Lumberyard
        initialArea.getNextState(1, 9) shouldBe AcreType.Lumberyard
        initialArea.getNextState(4, 3) shouldBe AcreType.Lumberyard
      }

      "stay trees if less than three adjacent acres were lumberyards" in {
        initialArea.getNextState(1, 6) shouldBe AcreType.Trees
        initialArea.getNextState(8, 5) shouldBe AcreType.Trees
      }
    }

    "for lumberyard acre" should {
      "remain a lumberyard if it was adjacent to at least one other lumberyard and at least one acre containing trees" in {
        initialArea.getNextState(0, 8) shouldBe AcreType.Lumberyard
        initialArea.getNextState(1, 7) shouldBe AcreType.Lumberyard
      }

      "become open if not adjacent to at least one other lumberyard and at least one acre containing trees" in {
        initialArea.getNextState(4, 0) shouldBe AcreType.OpenGround
        initialArea.getNextState(9, 3) shouldBe AcreType.OpenGround
        initialArea.getNextState(7, 8) shouldBe AcreType.OpenGround
      }
    }

    //    An open acre will become filled with trees if three or more adjacent acres contained trees. Otherwise, nothing happens.
    //    An acre filled with trees will become a lumberyard if three or more adjacent acres were lumberyards. Otherwise, nothing happens.
    //    An acre containing a lumberyard will remain a lumberyard if it was adjacent to at least one other lumberyard and
    //    at least one acre containing trees. Otherwise, it becomes open.
  }

  "evolve" should {
    "update area as appropriate" in {
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

      val after1 = initialArea.evolve
      after1 shouldBe new AreaParser().parseArea(
        List(
          ".......##.",
          "......|###",
          ".|..|...#.",
          "..|#||...#",
          "..##||.|#|",
          "...#||||..",
          "||...|||..",
          "|||||.||.|",
          "||||||||||",
          "....||..|."
        )
      )
    }
  }

}
