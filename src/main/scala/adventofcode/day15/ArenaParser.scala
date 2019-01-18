package adventofcode.day15

class ArenaParser {

  def parseArena(initialState: List[String]) : Arena = {
    val result = initialState.indices.foldLeft(Map[Coordinate, FightingUnit](), Map[Coordinate, Char]()) {
      (acc, y) =>
        val line = initialState(y)
        val stateForLine = line.indices.foldLeft(Seq[(Coordinate, FightingUnit)](), Map[Coordinate, Char]()) {
          (lineAcc, x) =>
            val coordinate = Coordinate(x, y)
            line(x) match {
              case c@ ('.'|'#') => (lineAcc._1, lineAcc._2 + (coordinate -> c))
              case 'G' => (lineAcc._1 :+ (coordinate -> FightingUnit(UnitType.Goblin)), lineAcc._2 + (Coordinate(x, y) -> '.'))
              case 'E' => (lineAcc._1 :+ (coordinate -> FightingUnit(UnitType.Elf)), lineAcc._2 + (Coordinate(x, y) -> '.'))
            }
        }
        (acc._1 ++ stateForLine._1, acc._2 ++ stateForLine._2)
    }
    Arena(result._1, result._2)
  }
}
