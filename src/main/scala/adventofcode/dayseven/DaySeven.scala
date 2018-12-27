package adventofcode.dayseven

import scala.io.Source

object DaySeven extends App {

  //Step P must be finished before step Z can begin.
  private val instructionRegex = """Step ([A-Z]) must be finished before step ([A-Z]) can begin.""".r

  val instructions = InstructionSet(Source.fromFile("src/main/resources/dayseven/input.txt")
    .getLines()
    .map {
      case instructionRegex(parent, child) => Instruction(parent.charAt(0), child.charAt(0))
    }
    .toSeq)

  val sequence = new InstructionOrderer().getStepOrder(instructions)
  println(s"Part One: $sequence")

  val time = new InstructionExecutor().executeInstructions(instructions, 5, 60)
  println(s"Part Two: $time")
}
