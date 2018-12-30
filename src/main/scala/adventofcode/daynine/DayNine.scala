package adventofcode.daynine

object DayNine extends App {

//  val scores = new GamePlayer().playGame(418, 70769)
//  println(s"Part One: Max score = ${scores.values.max}")

  val scores2 = new parttwo.GamePlayer().playGame(418, 70769 * 100)
  println(s"Part Two: Max score = ${scores2.values.max}")
}
