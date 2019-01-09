package adventofcode.day13

import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

class CartSimulatorTest extends WordSpec with Matchers {
  private val cartSimulator = new CartSimulator()

  "tick" must {
    "move carts in simple example" in {
      cartSimulator.tick(
        Seq(
          (Coordinate(0, 1), Cart(Direction.South)),
          (Coordinate(0, 5), Cart(Direction.North))
        ),
        Map(
          Coordinate(0, 0) -> '|',
          Coordinate(0, 1) -> '|',
          Coordinate(0, 2) -> '|',
          Coordinate(0, 3) -> '|',
          Coordinate(0, 4) -> '|',
          Coordinate(0, 5) -> '|',
          Coordinate(0, 6) -> '|',
        ))._1 shouldBe
        Seq(
          (Coordinate(0, 2), Cart(Direction.South)),
          (Coordinate(0, 4), Cart(Direction.North))
        )
    }

    "move carts in a complex example" in {
      //    /->-\
      //    |   |  /----\
      //    | /-+--+-\  |
      //    | | |  | v  |
      //    \-+-/  \-+--/
      //      \------/
      val initialState = new TracksParser().parse(Source.fromFile("src/test/resources/day13/Example2.txt").getLines().toSeq)
      //       0123456789
      //     0 /-->\
      //     1 |   |  /----\
      //     2 | /-+--+-\  |
      //     3 | | |  | |  |
      //     4 \-+-/  \->--/
      //     5   \------/
      cartSimulator.tick(initialState._1, initialState._2) shouldBe (
        Seq(
          (Coordinate(3, 0), Cart(Direction.East)),
          (Coordinate(9, 4), Cart(Direction.East, Turn.Straight))
        ),
        Nil
      )
    }
  }

  "findCollision" must {
    "find first collision" in {
      val initialState = new TracksParser().parse(Source.fromFile("src/test/resources/day13/Example2.txt").getLines().toSeq)
      cartSimulator.findCollision(initialState._1, initialState._2) shouldBe Coordinate(7,3)
    }
  }
}
