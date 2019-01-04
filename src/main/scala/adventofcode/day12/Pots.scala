package adventofcode.day12

case class Pots(potsWithPlants: Set[Int]) {

  def currentState(pot: Int): String = {
    (pot - 2 to pot + 2).map(p => if (potsWithPlants.contains(p)) '#' else '.').mkString
  }

  def willHavePlantInNextGen(pot: Int, growthPatterns: Set[String]): Boolean = {
    growthPatterns.contains(currentState(pot))
  }

  def growGeneration(growthPatterns: Set[String]): Pots = {
    Pots((potsWithPlants.min - 2 to potsWithPlants.max + 2).foldLeft(Set[Int]()) {
      (newPotsWithPlants, pot) =>
        if (willHavePlantInNextGen(pot, growthPatterns)) newPotsWithPlants + pot
        else newPotsWithPlants
    })
  }

}

object Pots {
  def apply(initialState: String): Pots = {
    val indicesOfPlants: Seq[Int] = initialState.zipWithIndex
      .filter(_._1 == '#')
      .map(_._2)
    Pots(indicesOfPlants.toSet)
  }

}
