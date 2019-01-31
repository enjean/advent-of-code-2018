package adventofcode.day16

class OperationAnalyzer {
  def possibleOperations(sample: Sample) : Seq[String] = {
    val results = OperationSet.operationMap.map { op =>
      op._1 -> op._2.apply(sample.before, sample.instruction.instructionArgs)
    }
    results.filter(_._2 == sample.after)
      .keys.toSeq
  }

  def operationMap(samples: Seq[Sample]) : Map[Int, String] = {
    val s = samples.map(sample => (sample.instruction.opcode, possibleOperations(sample)))
    val possibilities = s
      .groupBy(_._1)
    operationMapStep(possibilities.map(p => p._1 -> p._2.flatMap(_._2).toSet), Map())
  }

  private def operationMapStep(possibilities: Map[Int, Set[String]], found: Map[Int, String]) : Map[Int, String] = {
    if (possibilities.isEmpty) found
    else {
      val opToAssign = possibilities.find(_._2.size == 1).get
      val opCode = opToAssign._1
      val op = opToAssign._2.head
      val newPossibilities = (possibilities - opToAssign._1).map { p =>
        p._1 -> (p._2 - op)
      }
      operationMapStep(newPossibilities, found + (opCode -> op))
    }
  }
}
