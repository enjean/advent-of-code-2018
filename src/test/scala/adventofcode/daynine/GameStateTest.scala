package adventofcode.daynine

import org.scalatest.{FlatSpec, Matchers}

class GameStateTest extends FlatSpec with Matchers {
  "add marble" should "add in appropriate location and update current marble" in {
    val initialState = GameState(List(0), 0)

    val state1 = initialState.addMarble(1)
    state1 shouldBe GameState(List(0, 1), 1)

    val state2 = state1.addMarble(2)
    state2 shouldBe GameState(List(0, 2, 1), 1)

    val state3 = state2.addMarble(3)
    state3 shouldBe GameState(List(0, 2, 1, 3), 3)

    val state4 = state3.addMarble(4)
    state4 shouldBe GameState(List(0, 4, 2, 1, 3), 1)

    val state5 = state4.addMarble(5)
    state5 shouldBe GameState(List(0, 4, 2, 5, 1, 3), 3)

    val state6 = state5.addMarble(6)
    state6 shouldBe GameState(List(0, 4, 2, 5, 1, 6, 3), 5)

    val state7 = state6.addMarble(7)
    state7 shouldBe GameState(List(0, 4, 2, 5, 1, 6, 3, 7), 7)

    val state8 = state7.addMarble(8)
    state8 shouldBe GameState(List(0, 8, 4, 2, 5, 1, 6, 3, 7), 1)
  }

  "scoring move" should "remove marble 7 spaces counterclockwise and return new state and score" in {
    val state = GameState(List(0, 16, 8, 17, 4, 18, 9, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15), 13)
    state.scoringMove shouldBe(GameState(List(0, 16, 8, 17, 4, 18, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15), 6), 9)
  }

  "scoring move when current index less than 7" should "loop to back" in {
    val state = GameState(List(0, 16, 8, 17, 4, 18, 9, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15), 2)
    state.scoringMove shouldBe(GameState(List(0, 16, 8, 17, 4, 18, 9, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 3, 14, 7, 15), 18), 13)
  }
}
