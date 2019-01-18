package adventofcode.day15

case class Coordinate(x: Int, y: Int) {
  def adjacent: Seq[Coordinate] = {
    Seq(
      Coordinate(x - 1, y),
      Coordinate(x + 1, y),
      Coordinate(x, y - 1),
      Coordinate(x, y + 1)
    )
  }
}
