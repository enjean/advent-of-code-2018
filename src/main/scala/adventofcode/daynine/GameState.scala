package adventofcode.daynine

case class GameState(marbles: List[Int], currentMarbleIndex : Int) {
  def addMarble(marbleValue: Int) : GameState = {
    val nextIndex = (currentMarbleIndex + 1) % marbles.size + 1

    val (front, back) = marbles.splitAt(nextIndex)

    GameState(front ++ List(marbleValue) ++ back, nextIndex)
  }

  def scoringMove : (GameState, Int) = {
    val indexToRemove = ((currentMarbleIndex - 7) + marbles.size) % marbles.size
    val valueRemoved = marbles(indexToRemove)
    val (front, back) = marbles.splitAt(indexToRemove)
    (GameState(front ++ back.tail, indexToRemove), valueRemoved)
  }
}
