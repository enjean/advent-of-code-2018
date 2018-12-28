package adventofcode.dayeight

import scala.io.Source

object DayEight extends App{
  val input = Source.fromFile("src/main/resources/dayeight/input.txt")
    .mkString
    .split(" ")
    .map(_.toInt)
    .toList

  val root = new TreeParser().parseTree(input)
  println(s"Part One: ${root.metadataSum}")
  println(s"Part Two: ${root.value}")
}
