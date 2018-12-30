package adventofcode.daynine.parttwo


class GamePlayer {

  def playGame(numPlayers: Long, lastMarble: Long) : Map[Long, Long] = {
    val firstMarble = PlayedMarble(0)
    val game = (1L to lastMarble).foldLeft((firstMarble, Map[Long, Long]())) {
      case(acc, marbleValue) =>
        val player = marbleValue % numPlayers
        if (marbleValue % 23 == 0) {
          val result = scoringMove(acc._1)
          val score = acc._2.getOrElse(player, 0L) + result._2 + marbleValue
          (result._1, acc._2 + (player -> score))
        }
        else {
          (addMarble(acc._1, marbleValue), acc._2)
        }
    }
    game._2
  }

  def addMarble(currentMarble: PlayedMarble, marbleValue: Long) : PlayedMarble = {
    val marbleToInsertAt = currentMarble.clockwise.clockwise

    val newMarble = PlayedMarble(marbleValue, marbleToInsertAt.counterclockwise, marbleToInsertAt)

    marbleToInsertAt.counterclockwise.clockwise = newMarble
    marbleToInsertAt.counterclockwise = newMarble

    newMarble
  }

  def scoringMove(currentMarble: PlayedMarble) : (PlayedMarble, Long) = {
    val marbleToRemove = Iterator.iterate(currentMarble)(_.counterclockwise).drop(7).next()

    marbleToRemove.counterclockwise.clockwise = marbleToRemove.clockwise
    marbleToRemove.clockwise.counterclockwise = marbleToRemove.counterclockwise
    (marbleToRemove.clockwise, marbleToRemove.value)
  }
}
