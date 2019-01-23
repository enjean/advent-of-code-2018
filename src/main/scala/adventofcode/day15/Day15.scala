package adventofcode.day15

import scala.io.Source

object Day15 extends App {
  val arena = new ArenaParser().parseArena(Source.fromFile("src/main/resources/day15/input.txt").getLines().toList)
  private val simulator = new FightSimulator()
  val battleResult = simulator.battle(arena)
  println(s"Part One: Combat ended after ${battleResult._2} rounds with ${battleResult._1.score} hit points left = ${battleResult._1.score * battleResult._2}")

  val elfWinResult = simulator.findWinningScenarioForElves(arena)
  println(s"Part Two: Combat ended after ${elfWinResult._1._2} rounds with ${elfWinResult._1._1.score} hit points left = ${elfWinResult._1._1.score * elfWinResult._1._2}")
}
