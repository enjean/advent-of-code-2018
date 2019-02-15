package adventofcode.day17


case class Coordinate(x: Int, y: Int) {
  def up: Coordinate = Coordinate(x, y - 1)


  def down: Coordinate = {
    Coordinate(x, y + 1)
  }

  def left: Coordinate = {
    Coordinate(x - 1, y)
  }

  def right: Coordinate = {
    Coordinate(x + 1, y)
  }
}
