package adventofcode.dayseven

class InstructionOrderer {
  def getStepOrder(instructions : Seq[Instruction]) : String = {
    makeStep(instructions, findReadySteps(instructions).sorted, "")
  }

  private def makeStep(remainingInstructions: Seq[Instruction], readySteps: Seq[Char], previousSteps: String) : String = {
    val stepToApply = readySteps.head
    println(s"Remaining = $remainingInstructions Ready = $readySteps So Far = $readySteps. Applying $stepToApply")
    val instructionsToExecute = remainingInstructions
      .filter(_.parentStep == stepToApply)

    val instructionsLeft = remainingInstructions.diff(instructionsToExecute)

    val newReadySteps = instructionsToExecute.foldLeft(readySteps.tail) {
      (acc, instruction : Instruction) =>
        val step = instruction.childStep
        if (stepIsReady(step, instructionsLeft)) {
          acc :+ step
        }
        else {
          acc
        }
    }

    if (instructionsLeft.isEmpty && newReadySteps.isEmpty) previousSteps + stepToApply
    else makeStep(instructionsLeft, newReadySteps.sorted, previousSteps + stepToApply)
  }

  private def findReadySteps(instructions: Seq[Instruction]) : Seq[Char] = {
    instructions.map(_.parentStep)
      .distinct
      .filter(stepIsReady(_, instructions))
  }

  private def stepIsReady(step: Char, instructions: Seq[Instruction]) : Boolean = {
    !instructions.map(_.childStep).contains(step)
  }
}
