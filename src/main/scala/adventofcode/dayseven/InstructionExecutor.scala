package adventofcode.dayseven

class InstructionExecutor {

  case class Task(step: Char, secondsRemaining: Int)

  def executeInstructions(instructions: InstructionSet, numWorkers: Int, baseStepTime: Int) : Int = {
    val readySteps = instructions.findReadySteps
    val stepsToExecute = readySteps.take(numWorkers)
    val tasks = stepsToExecute
        .map(step => Task(step, baseStepTime + (step.toInt - 'A'.toInt + 1)))
    executeSecond(instructions, tasks, readySteps.diff(stepsToExecute.sorted), numWorkers, baseStepTime, 0)
  }

  private def executeSecond(remainingInstructions: InstructionSet, ongoingTasks: Seq[Task], readySteps: Seq[Char], taskLimit: Int, baseStepTime: Int, secondsSoFar: Int) : Int = {
    println(s"$secondsSoFar    $ongoingTasks")
    val newTasks = ongoingTasks.map(t => t.copy(secondsRemaining = t.secondsRemaining - 1))

    val partitionedTasks = newTasks.partition(_.secondsRemaining == 0)
    val finishedTasks = partitionedTasks._1
    val finishedSteps = finishedTasks.map(_.step)

    val continuingTasks = partitionedTasks._2

    val newState = finishedSteps.foldLeft(InstructionSetState(readySteps, remainingInstructions)) {
      (insSetState, step) =>
        val executionResults = insSetState.instructionsLeft.executeStep(step)
        InstructionSetState(insSetState.readySteps ++ executionResults.readySteps, executionResults.instructionsLeft)
    }
    val stepsToNowExecute = newState.readySteps.sorted.take(taskLimit - continuingTasks.size)

    val allNewTasks = continuingTasks ++ stepsToNowExecute.map(step => Task(step, baseStepTime + (step.toInt - 'A'.toInt + 1)))

    if (remainingInstructions.instructions.isEmpty && allNewTasks.isEmpty) secondsSoFar + 1
    else executeSecond(newState.instructionsLeft, allNewTasks, newState.readySteps.diff(stepsToNowExecute), taskLimit, baseStepTime, secondsSoFar + 1)
  }
}
