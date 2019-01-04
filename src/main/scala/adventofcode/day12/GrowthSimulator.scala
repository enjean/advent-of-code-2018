package adventofcode.day12

class GrowthSimulator {

  def runSimulation(initialState: String, growthPatterns: Set[String]): Int = {
    val finalState = Iterator.iterate(Pots(initialState))(currentState => {
      val nextState = currentState.growGeneration(growthPatterns)
//      val prettyPrint = (-3 to 34).map(index => if (nextState.potsWithPlants.contains(index)) '#' else '.').mkString(" ")
//      println(prettyPrint)
      nextState
    }
    )
      .drop(20).next()
    finalState.potsWithPlants.sum
  }
}
