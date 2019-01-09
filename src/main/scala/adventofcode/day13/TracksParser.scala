package adventofcode.day13

class TracksParser {

  def parse(initalState: Seq[String]) : (Seq[(Coordinate, Cart)], Map[Coordinate, Char]) = {
    initalState.indices.foldLeft(Seq[(Coordinate, Cart)](), Map[Coordinate, Char]()) {
      (acc, y) =>
        val line = initalState(y)
        val stateForLine = line.indices.foldLeft(Seq[(Coordinate, Cart)](), Map[Coordinate, Char]()) {
          (lineAcc, x) =>
            val coordinate = Coordinate(x, y)
            line(x) match {
              case c@ ('-'|'|'|'/'|'\\'|'+') => (lineAcc._1, lineAcc._2 + (coordinate -> c))
              case '<' => (lineAcc._1 :+ (coordinate -> Cart(Direction.West)), lineAcc._2 + (Coordinate(x, y) -> '-'))
              case '>' => (lineAcc._1 :+ (coordinate -> Cart(Direction.East)), lineAcc._2 + (Coordinate(x, y) -> '-'))
              case '^' => (lineAcc._1 :+ (coordinate -> Cart(Direction.North)), lineAcc._2 + (Coordinate(x, y) -> '|'))
              case 'v' => (lineAcc._1 :+ (coordinate -> Cart(Direction.South)), lineAcc._2 + (Coordinate(x, y) -> '|'))
              case _ => lineAcc
            }
        }
        (acc._1 ++ stateForLine._1, acc._2 ++ stateForLine._2)
    }

  }
}
