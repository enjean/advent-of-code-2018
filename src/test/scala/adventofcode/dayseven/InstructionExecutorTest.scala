package adventofcode.dayseven

import org.scalatest.{FlatSpec, Matchers}

class InstructionExecutorTest extends FlatSpec with Matchers {

  "example with two workers and no base" should "take 15 seconds" in {
    val instructions = InstructionSet(Seq(
      Instruction('C', 'A'),
      Instruction('C', 'F'),
      Instruction('A', 'B'),
      Instruction('A', 'D'),
      Instruction('B', 'E'),
      Instruction('D', 'E'),
      Instruction('F', 'E')
    ))

    new InstructionExecutor().executeInstructions(instructions, 2, 0) shouldBe 15
  }
}
