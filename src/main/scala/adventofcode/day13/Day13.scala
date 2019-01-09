package adventofcode.day13

import scala.io.Source

object Day13 extends App {
  val initialState = new TracksParser().parse(Source.fromFile("src/main/resources/day13/input.txt").getLines().toSeq)
  val cartSimulator = new CartSimulator()
  println(s"Part One: First Collision at ${cartSimulator.findCollision(initialState._1, initialState._2)}")
}
