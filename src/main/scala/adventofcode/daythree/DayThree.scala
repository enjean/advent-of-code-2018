package adventofcode.daythree

import scala.io.Source

object DayThree extends App {
  val claims = Source.fromFile("src/main/resources/daythree/input.txt")
    .getLines()
    .toList
    .map(Claim.apply)


  println(new OverlapCalculator().overlapSquares(claims).size)
  println(new UniqueClaimFinder().uniqueClaim(claims).id)
}
