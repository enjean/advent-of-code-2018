package adventofcode.dayten

case class Position(x: Int, y: Int) {
  def isAdjacentTo(position: Position) : Boolean = {
    this != position && (x - position.x).abs <= 1 && (y - position.y).abs <= 1
  }
}
case class Velocity(x: Int, y: Int)

case class Point (position: Position, velocity: Velocity) {
  def advance : Point = {
    copy(position = Position(position.x + velocity.x, position.y + velocity.y))
  }
}
