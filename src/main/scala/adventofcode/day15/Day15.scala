package adventofcode.day15

import scala.io.Source

object Day15 extends App {
  val arena = new ArenaParser().parseArena(Source.fromFile("src/main/resources/day15/input.txt").getLines().toList)
  def battleResult = new FightSimulator().battle(arena)
  println(s"Part One: Combat ended after ${battleResult._2} rounds with ${battleResult._1.score} hit points left = ${battleResult._1.score * battleResult._2}")
}
