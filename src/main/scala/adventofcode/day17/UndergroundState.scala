package adventofcode.day17

case class UndergroundState(waterAtRest: Set[Coordinate], waterHasMovedThrough: Set[Coordinate]) {

  def addMovedThrough(coordinate: Coordinate): UndergroundState = UndergroundState(waterAtRest, waterHasMovedThrough + coordinate)

  def setStanding(coordinate: Coordinate): UndergroundState =
    UndergroundState(
      waterAtRest + coordinate,
      waterHasMovedThrough - coordinate
    )

  def seen(coordinate: Coordinate) : Boolean = waterAtRest.contains(coordinate) || waterHasMovedThrough.contains(coordinate)
}
