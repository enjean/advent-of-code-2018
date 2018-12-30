package adventofcode.daynine.parttwo

import org.scalatest.{FlatSpec, Matchers}

class GamePlayerTest extends FlatSpec with Matchers {

  private val gamePlayer = new GamePlayer()
  "insert marble" should "add new marble and update clockwise appropriately" in {
    val marble0 = PlayedMarble(0)

    val marble1 = gamePlayer.addMarble(marble0, 1)
    marble1.value shouldBe 1
    marble1.counterclockwise shouldBe marble0
    marble1.clockwise shouldBe marble0

    val marble2 = gamePlayer.addMarble(marble1, 2)
    marble2.value shouldBe 2
    marble2.counterclockwise shouldBe marble0
    marble2.clockwise shouldBe marble1
    marble0.clockwise shouldBe marble2
    marble1.counterclockwise shouldBe marble2
    marble1.clockwise shouldBe marble0

    val marble3 = gamePlayer.addMarble(marble2, 3)
    marble3.value shouldBe 3
    marble3.counterclockwise shouldBe marble1
    marble3.clockwise shouldBe marble0
    marble1.clockwise shouldBe marble3
    marble0.counterclockwise shouldBe marble3

    val marble4 = gamePlayer.addMarble(marble3, 4)
    marble4.value shouldBe 4
    marble4.counterclockwise shouldBe marble0
    marble4.clockwise shouldBe marble2
    marble0.clockwise shouldBe marble4
    marble2.counterclockwise shouldBe marble4
  }

  "scoring move" should "remove marble 7 spaces counterclockwise and return new state and score" in {
    val marble0 = PlayedMarble(0)
    val marble16 = PlayedMarble(16)
    val marble8 = PlayedMarble(8)
    val marble17 = PlayedMarble(17)
    val marble4 = PlayedMarble(4)
    val marble18 = PlayedMarble(18)
    val marble9 = PlayedMarble(9)
    val marble19 = PlayedMarble(19)
    val marble2 = PlayedMarble(2)
    val marble20 = PlayedMarble(20)
    val marble10 = PlayedMarble(10)
    val marble21 = PlayedMarble(21)
    val marble5 = PlayedMarble(5)
    val marble22 = PlayedMarble(22)
    val marble11 = PlayedMarble(11)
    val marble1 = PlayedMarble(1)
    val marble12 = PlayedMarble(12)
    val marble6 = PlayedMarble(6)
    val marble13 = PlayedMarble(13)
    val marble3 = PlayedMarble(3)
    val marble14 = PlayedMarble(14)
    val marble7 = PlayedMarble(7)
    val marble15 = PlayedMarble(15)
    marble0.counterclockwise = marble15
    marble0.clockwise = marble16
    marble16.counterclockwise = marble0
    marble16.clockwise = marble8
    marble8.counterclockwise = marble16
    marble8.clockwise = marble17
    marble17.counterclockwise = marble8
    marble17.clockwise = marble4
    marble4.counterclockwise = marble17
    marble4.clockwise = marble18
    marble18.counterclockwise = marble4
    marble18.clockwise = marble9
    marble9.counterclockwise = marble18
    marble9.clockwise = marble19
    marble19.counterclockwise = marble9
    marble19.clockwise = marble2
    marble2.counterclockwise = marble19
    marble2.clockwise = marble20
    marble20.counterclockwise = marble2
    marble20.clockwise = marble10
    marble10.counterclockwise = marble20
    marble10.clockwise = marble21
    marble21.counterclockwise = marble10
    marble21.clockwise = marble5
    marble5.counterclockwise = marble21
    marble5.clockwise = marble22
    marble22.counterclockwise = marble5
    marble22.clockwise = marble11
    marble11.counterclockwise = marble22
    marble11.clockwise = marble1
    marble1.counterclockwise = marble11
    marble1.clockwise = marble12
    marble12.counterclockwise = marble1
    marble12.clockwise = marble6
    marble6.counterclockwise = marble12
    marble6.clockwise = marble13
    marble13.counterclockwise = marble6
    marble13.clockwise = marble3
    marble3.counterclockwise = marble13
    marble3.clockwise = marble14
    marble14.counterclockwise = marble3
    marble14.clockwise = marble7
    marble7.counterclockwise = marble14
    marble7.clockwise = marble15
    marble15.counterclockwise = marble7
    marble15.clockwise = marble0

    val result = gamePlayer.scoringMove(marble22)
    result._2 shouldBe 9
    val newCurrent = result._1
    newCurrent shouldBe marble19
    marble18.clockwise shouldBe marble19
    marble19.counterclockwise shouldBe marble18
    marble19.clockwise shouldBe marble2
    marble2.counterclockwise shouldBe marble19
  }

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
