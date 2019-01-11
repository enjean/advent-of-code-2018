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
      cartSimulator.tick(initialState._1, initialState._2) shouldBe(
        Seq(
          (Coordinate(3, 0), Cart(Direction.East)),
          (Coordinate(9, 4), Cart(Direction.East, Turn.Straight))
        ),
        Nil
      )
    }

    "crash carts moving east back to back" in {
//      ">>-" should be ---
      cartSimulator.tick(
        Seq(
          Coordinate(0, 0) -> Cart(Direction.East),
        Coordinate(1, 0) -> Cart(Direction.East)
        ),
        Map(
          Coordinate(0, 0) -> '-',
          Coordinate(1, 0) -> '-',
          Coordinate(2, 0) -> '-'
        )
      ) shouldBe (
        Nil,
        Seq(Coordinate(1, 0))
      )
    }

    "not crash carts moving west back to back" in {
      //      "-<<" should be <<-
      cartSimulator.tick(
        Seq(
          Coordinate(1, 0) -> Cart(Direction.West),
          Coordinate(2, 0) -> Cart(Direction.West)
        ),
        Map(
          Coordinate(0, 0) -> '-',
          Coordinate(1, 0) -> '-',
          Coordinate(2, 0) -> '-'
        )
      ) shouldBe (
        Seq(
          Coordinate(0, 0) -> Cart(Direction.West),
          Coordinate(1, 0) -> Cart(Direction.West)
        ),
        Nil
      )
    }

    "crash carts facing each other" in {
      //      "><" should be --
      cartSimulator.tick(
        Seq(
          Coordinate(0, 0) -> Cart(Direction.East),
          Coordinate(1, 0) -> Cart(Direction.West)
        ),
        Map(
          Coordinate(0, 0) -> '-',
          Coordinate(1, 0) -> '-'
        )
      ) shouldBe (
        Nil,
        Seq(Coordinate(1, 0))
      )
    }
  }

  "findCollision" must {
    "find first collision" in {
      val initialState = new TracksParser().parse(Source.fromFile("src/test/resources/day13/Example2.txt").getLines().toSeq)
      cartSimulator.findCollision(initialState._1, initialState._2) shouldBe Coordinate(7, 3)
    }
  }

  "findFinalRemainingCart" must {
    "find coordinate of last cart remaining" in {
      val initialState = new TracksParser().parse(Source.fromFile("src/test/resources/day13/crashTrack.txt").getLines().toSeq)
      cartSimulator.findFinalRemainingCart(initialState._1, initialState._2) shouldBe Coordinate(6, 4)
    }

    "big example" in {
      val initialState = new TracksParser().parse(Source.fromFile("src/main/resources/day13/input.txt").getLines().toSeq)
      val initialCarts = initialState._1
      val tracks = initialState._2
      val tick1 = cartSimulator.tick(initialCarts, tracks)
      println(tick1)
      tick1._1 should contain theSameElementsAs Seq(
        Coordinate(95, 9) -> Cart(Direction.West, Turn.Left),
        Coordinate(66, 11) -> Cart(Direction.West, Turn.Left),
        Coordinate(92, 46) -> Cart(Direction.East, Turn.Left),
        Coordinate(61, 59) -> Cart(Direction.North, Turn.Left),
        Coordinate(4, 70) -> Cart(Direction.South, Turn.Left),
        Coordinate(14, 70) -> Cart(Direction.North, Turn.Straight),
        Coordinate(45, 72) -> Cart(Direction.South, Turn.Left),
        Coordinate(115, 72) -> Cart(Direction.South, Turn.Left),
        Coordinate(52, 90) -> Cart(Direction.North, Turn.Left),
        Coordinate(87, 98) -> Cart(Direction.North, Turn.Straight),
        Coordinate(77, 104) -> Cart(Direction.East, Turn.Left),
        Coordinate(53, 112) -> Cart(Direction.East, Turn.Straight),
        Coordinate(32, 123) -> Cart(Direction.East, Turn.Left),
        Coordinate(67, 126) -> Cart(Direction.North, Turn.Left),
        Coordinate(12, 128) -> Cart(Direction.North, Turn.Straight),
        Coordinate(114, 135) -> Cart(Direction.East, Turn.Left),
        Coordinate(96, 138) -> Cart(Direction.East, Turn.Left),
      )

//      val tick2 = cartSimulator.tick()
    }
  }
}
