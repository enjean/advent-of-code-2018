package adventofcode.daysix

case class Coordinate(x: Int, y: Int) {
  def manhattanDistance(to: Coordinate): Int = {
    (x - to.x).abs + (y - to.y).abs
  }

  def closestTo(to: Seq[Coordinate]) : Seq[Coordinate] = {
    val distanceToCoords = to.map( t=> (t, manhattanDistance(t)))
    val minDistance = distanceToCoords.map(_._2).min
    distanceToCoords.filter(_._2 == minDistance).map(_._1)
  }

  def totalDistance(to: Seq[Coordinate]): Int = {
    to.map(manhattanDistance).sum
  }
}
