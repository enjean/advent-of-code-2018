package adventofcode.daynine.parttwo

class PlayedMarble(val value: Long) {
  var counterclockwise : PlayedMarble = _
  var clockwise: PlayedMarble = _
}

object PlayedMarble {
  def apply(value: Long): PlayedMarble = {
    val marble = new PlayedMarble(value)
    marble.counterclockwise = marble
    marble.clockwise = marble
    marble
  }

  def apply(value: Long, counterclockwise: PlayedMarble, clockwise: PlayedMarble): PlayedMarble = {
    val marble = new PlayedMarble(value)
    marble.counterclockwise = counterclockwise
    marble.clockwise = clockwise
    marble
  }
}