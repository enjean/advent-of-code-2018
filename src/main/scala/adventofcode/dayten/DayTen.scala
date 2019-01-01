package adventofcode.dayten

import scala.io.Source

object DayTen extends App{
//  position=< 9,  1> velocity=< 0,  2>
  val pointRegex = """position=<([ -]?\d+), ([ -]?\d+)> velocity=<([ -]?\d+), ([ -]?\d+)>""".r

  val points = Source.fromFile("src/main/resources/dayten/input.txt")
    .getLines()
    .map {
      case pointRegex(x, y, vx, vy) => Point(Position(x.trim.toInt, y.trim.toInt), Velocity(vx.trim.toInt, vy.trim.toInt))
    }
    .toSeq

  val gridWithWordResult = new WordFinder().findGridShowingWord(SkyGrid(points))
  println(s"At second ${gridWithWordResult._2}")
  println(gridWithWordResult._1.prettyPrint)
}
