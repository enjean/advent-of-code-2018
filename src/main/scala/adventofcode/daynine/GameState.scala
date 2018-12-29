package adventofcode.daynine

case class GameState(marbles: List[Int], currentMarbleIndex : Int) {
  def addMarble(marbleValue: Int) : GameState = {
    val nextIndex = (currentMarbleIndex + 1) % marbles.size + 1

    val (front, back) = marbles.splitAt(nextIndex)

    GameState(front ++ List(marbleValue) ++ back, nextIndex)
  }

  def scoringMove : (GameState, Int) = {
    val indexToRemove =
      if (currentMarbleIndex > 7 ) currentMarbleIndex - 7
      else marbles.size - (7 - currentMarbleIndex)
    val valueRemoved = marbles(indexToRemove)
    val (front, back) = marbles.splitAt(indexToRemove)
    (GameState(front ++ back.tail, indexToRemove), valueRemoved)
  }
}
