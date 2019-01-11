package adventofcode.day13

import org.scalatest.{Matchers, WordSpec}

class CartTest extends WordSpec with Matchers {
  "moveTo" must {
    "stay straight on WE when heading E or W" in {
      val track = '-'
      Cart(Direction.West, Turn.Straight).moveTo(track) shouldBe Cart(Direction.West, Turn.Straight)
      Cart(Direction.East, Turn.Straight).moveTo(track) shouldBe Cart(Direction.East, Turn.Straight)
    }

    "stay straight on NS when heading N or S" in {
      val track = '|'
      Cart(Direction.North, Turn.Straight).moveTo(track) shouldBe Cart(Direction.North, Turn.Straight)
      Cart(Direction.South, Turn.Straight).moveTo(track) shouldBe Cart(Direction.South, Turn.Straight)
    }

    "for /" must {
      "turn right when heading north" in {
        Cart(Direction.North, Turn.Straight).moveTo('/') shouldBe Cart(Direction.East, Turn.Straight)
      }

      "turn left when heading west" in {
        Cart(Direction.West, Turn.Straight).moveTo('/') shouldBe Cart(Direction.South, Turn.Straight)
      }

      "turn right when heading south" in {
        Cart(Direction.South, Turn.Straight).moveTo('/') shouldBe Cart(Direction.West, Turn.Straight)
      }

      "turn left when heading east" in {
        Cart(Direction.East, Turn.Straight).moveTo('/') shouldBe Cart(Direction.North, Turn.Straight)
      }
    }

    "for backslash" must {
      "turn left when heading north" in {
        Cart(Direction.North, Turn.Straight).moveTo('\\') shouldBe Cart(Direction.West, Turn.Straight)
      }

      "turn right when heading west" in {
        Cart(Direction.West, Turn.Straight).moveTo('\\') shouldBe Cart(Direction.North, Turn.Straight)
      }

      "turn left when heading south" in {
        Cart(Direction.South, Turn.Straight).moveTo('\\') shouldBe Cart(Direction.East, Turn.Straight)
      }

      "turn right when heading east" in {
        Cart(Direction.East, Turn.Straight).moveTo('\\') shouldBe Cart(Direction.South, Turn.Straight)
      }
    }

    "for +" must {
      "heading west" must {
        "turn south when nextTurn left" in {
          Cart(Direction.West, Turn.Left).moveTo('+') shouldBe Cart(Direction.South, Turn.Straight)
        }

        "stay heading west when nextTurn straight" in {
          Cart(Direction.West, Turn.Straight).moveTo('+') shouldBe Cart(Direction.West, Turn.Right)
        }

        "turn north when nextTurn right" in {
          Cart(Direction.West, Turn.Right).moveTo('+') shouldBe Cart(Direction.North, Turn.Left)
        }
      }

      "heading north" must {
        "turn west when nextTurn left" in {
          Cart(Direction.North, Turn.Left).moveTo('+') shouldBe Cart(Direction.West, Turn.Straight)
        }

        "stay heading north when nextTurn straight" in {
          Cart(Direction.North, Turn.Straight).moveTo('+') shouldBe Cart(Direction.North, Turn.Right)
        }

        "turn east when nextTurn right" in {
          Cart(Direction.North, Turn.Right).moveTo('+') shouldBe Cart(Direction.East, Turn.Left)
        }
      }

      "heading east" must {
        "turn north when nextTurn left" in {
          Cart(Direction.East, Turn.Left).moveTo('+') shouldBe Cart(Direction.North, Turn.Straight)
        }

        "stay heading east when nextTurn straight" in {
          Cart(Direction.East, Turn.Straight).moveTo('+') shouldBe Cart(Direction.East, Turn.Right)
        }

        "turn south when nextTurn right" in {
          Cart(Direction.East, Turn.Right).moveTo('+') shouldBe Cart(Direction.South, Turn.Left)
        }
      }

      "heading south" must {
        "turn east when nextTurn left" in {
          Cart(Direction.South, Turn.Left).moveTo('+') shouldBe Cart(Direction.East, Turn.Straight)
        }

        "stay heading south when nextTurn straight" in {
          Cart(Direction.South, Turn.Straight).moveTo('+') shouldBe Cart(Direction.South, Turn.Right)
        }

        "turn west when nextTurn right" in {
          Cart(Direction.South, Turn.Right).moveTo('+') shouldBe Cart(Direction.West, Turn.Left)
        }
      }
    }

  }
}
