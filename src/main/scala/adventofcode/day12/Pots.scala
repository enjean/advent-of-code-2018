package adventofcode.day12

case class Pots(potsWithPlants: Set[Long]) {

  def currentState(pot: Long): String = {
    (pot - 2 to pot + 2).map(p => if (potsWithPlants.contains(p)) '#' else '.').mkString
  }

  def willHavePlantInNextGen(pot: Long, growthPatterns: Set[String]): Boolean = {
    growthPatterns.contains(currentState(pot))
  }

  def growGeneration(growthPatterns: Set[String]): Pots = {
    Pots((potsWithPlants.min - 2L to potsWithPlants.max + 2L).foldLeft(Set[Long]()) {
      (newPotsWithPlants, pot) =>
        if (willHavePlantInNextGen(pot, growthPatterns)) newPotsWithPlants + pot
        else newPotsWithPlants
    })
  }

}

object Pots {
  def apply(initialState: String): Pots = {
    val indicesOfPlants: Seq[Long] = initialState.zipWithIndex
      .filter(_._1 == '#')
      .map(_._2.toLong)
    Pots(indicesOfPlants.toSet)
  }

}
