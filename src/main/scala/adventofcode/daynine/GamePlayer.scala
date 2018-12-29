package adventofcode.daynine

class GamePlayer {

  def playGame(numPlayers: Int, lastMarble: Int) : Map[Int, Int] = {
    val initialGameState = GameState(List(0), 0)
    val game = (1 to lastMarble).foldLeft((initialGameState, Map[Int, Int]())) {
      case(acc, marble) =>
        val player = marble % numPlayers
        if (marble % 23 == 0) {
          val result = acc._1.scoringMove
          (result._1, acc._2 + (player -> (acc._2.getOrElse(player, 0) + result._2 + marble)))
        }
        else {
          (acc._1.addMarble(marble), acc._2)
        }
    }
    game._2
  }
}
