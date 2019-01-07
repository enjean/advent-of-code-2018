package adventofcode.day12

import scala.annotation.tailrec

class GrowthSimulator {

  final def runSimulation(state: Pots, growthPatterns: Set[String], generationLimit: Long): Long = {
    val result = simulationStep(state, growthPatterns, 0, generationLimit, 0, Seq())
    if (result._2 == generationLimit) {
      println("Using full result")
      result._1
    }
    else {
      println("Extrapolating result")
      result._1 + (generationLimit - result._2) * result._3
    }
  }

  @tailrec
  private def simulationStep(state: Pots, growthPatterns: Set[String], currentGeneration: Long, generationLimit: Long, previousSum: Long, previousDiffs: Seq[Long]) : (Long, Long, Long) = {
    val sum = state.potsWithPlants.sum
    val diff = sum - previousSum
    val diffs = previousDiffs :+ diff
    println(diffs)
    if ((currentGeneration > 5 && diffs.distinct.size == 1) || currentGeneration == generationLimit) {
      println(s"Cutting off calculation at generation $currentGeneration")
      (sum, currentGeneration, diffs.distinct.head)
    }
    else simulationStep(state.growGeneration(growthPatterns), growthPatterns, currentGeneration + 1, generationLimit, sum, diffs.takeRight(5))
  }
}
