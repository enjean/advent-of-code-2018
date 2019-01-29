package adventofcode.day16

class OperationAnalyzer {
  def possibleOperations(sample: Sample) : Set[String] = {
    val results = OperationSet.operationMap.map { op =>
      op._1 -> op._2.apply(sample.before, sample.instruction.instructionArgs)
    }
    results.filter(_._2 == sample.after)
      .keys.toSet
  }
}
