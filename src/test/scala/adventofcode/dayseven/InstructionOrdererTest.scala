package adventofcode.dayseven

import org.scalatest.{FlatSpec, Matchers}

class InstructionOrdererTest extends FlatSpec with Matchers {
  "order" must "return steps combined as String" in {
    //    Step C must be finished before step A can begin.
    //    Step C must be finished before step F can begin.
    //    Step A must be finished before step B can begin.
    //    Step A must be finished before step D can begin.
    //    Step B must be finished before step E can begin.
    //    Step D must be finished before step E can begin.
    //    Step F must be finished before step E can begin.
    val instructions = Seq(
      Instruction('C', 'A'),
      Instruction('C', 'F'),
      Instruction('A', 'B'),
      Instruction('A', 'D'),
      Instruction('B', 'E'),
      Instruction('D', 'E'),
      Instruction('F', 'E')
    )

    new InstructionOrderer().getStepOrder(instructions) shouldBe "CABDFE"
  }

}
