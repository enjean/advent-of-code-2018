package adventofcode.daynine

import org.scalatest.{FlatSpec, Matchers}

class GamePlayerTest extends FlatSpec with Matchers {
  private val gamePlayer = new GamePlayer()

  "9 players, 25 marbles" should "be 32" in {
    val scores = gamePlayer.playGame(9, 25)
    scores.values.max shouldBe 32
  }

  "10 players; last marble is worth 1618 points" should "have high score is 8317" in {
    val scores = gamePlayer.playGame(10, 1618)
    scores.values.max shouldBe 8317
  }

  "13 players; last marble is worth 7999 points" should "have high score is 146373" in {
    val scores = gamePlayer.playGame(13, 7999)
    scores.values.max shouldBe 146373
  }

  "17 players; last marble is worth 1104 points" should "have high score is 2764" in {
    val scores = gamePlayer.playGame(17, 1104)
    scores.values.max shouldBe 2764
  }

  "21 players; last marble is worth 6111 points" should "have high score is 54718" in {
    val scores = gamePlayer.playGame(21, 6111)
    scores.values.max shouldBe 54718
  }

  "30 players; last marble is worth 5807 points" should "have high score is 37305" in {
    val scores = gamePlayer.playGame(30, 5807)
    scores.values.max shouldBe 37305
  }
}
