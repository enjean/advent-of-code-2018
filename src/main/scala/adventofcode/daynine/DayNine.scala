package adventofcode.daynine

object DayNine extends App {

  val scores = new GamePlayer().playGame(418, 70769)
  println(s"Part One: Max score = ${scores.values.max}")
}
