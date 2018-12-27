package adventofcode.dayseven

class InstructionOrderer {
  def getStepOrder(instructions : InstructionSet) : String = {
    makeStep(instructions, instructions.findReadySteps.sorted, "")
  }

  private def makeStep(remainingInstructions: InstructionSet, readySteps: Seq[Char], previousSteps: String) : String = {
    val stepToApply = readySteps.head
    println(s"Remaining = $remainingInstructions Ready = $readySteps So Far = $readySteps. Applying $stepToApply")

    val executionResults = remainingInstructions.executeStep(stepToApply)

    val newReadySteps = readySteps.tail ++ executionResults.readySteps

    val instructionsLeft = executionResults.instructionsLeft
    if (instructionsLeft.instructions.isEmpty && newReadySteps.isEmpty) previousSteps + stepToApply
    else makeStep(instructionsLeft, newReadySteps.sorted, previousSteps + stepToApply)
  }

}
