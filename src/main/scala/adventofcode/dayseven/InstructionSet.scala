package adventofcode.dayseven

case class InstructionSetState(readySteps: Seq[Char], instructionsLeft: InstructionSet)

case class InstructionSet(instructions: Seq[Instruction]) {
  def stepIsReady(step: Char) : Boolean = {
    !instructions.map(_.childStep).contains(step)
  }

  def findReadySteps : Seq[Char] = {
    instructions.map(_.parentStep)
      .distinct
      .filter(stepIsReady)
  }


  def executeStep(step: Char) : InstructionSetState = {
    val instructionsToExecute = instructions
          .filter(_.parentStep == step)
    val instructionsLeft = InstructionSet(instructions.diff(instructionsToExecute))
    val readySteps = instructionsToExecute.foldLeft(Seq[Char]()) {
      (acc, instruction : Instruction) =>
        val step = instruction.childStep
        if (instructionsLeft.stepIsReady(step)) {
          acc :+ step
        }
        else {
          acc
        }
    }
    InstructionSetState(readySteps, instructionsLeft)
  }
}


