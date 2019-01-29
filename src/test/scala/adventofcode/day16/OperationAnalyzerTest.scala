package adventofcode.day16

import org.scalatest.{FlatSpec, Matchers}

class OperationAnalyzerTest extends FlatSpec with Matchers {
  "possibleOperations" should "report which operations a sample could be" in {
    val sample = Sample(
      Vector(3, 2, 1, 1),
      Instruction(9, InstructionArgs(2, 1, 2)),
      Vector(3, 2, 2, 1)
    )

    new OperationAnalyzer().possibleOperations(sample) should contain theSameElementsAs Set("mulr", "addi", "seti")
  }
}
