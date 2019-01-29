package adventofcode.day16

import scala.io.Source

object Day16 extends App {
//  Before: [0, 1, 2, 1]
//  14 1 3 3
//  After:  [0, 1, 2, 1]
  val beforeRegex = """Before: \[(\d+), (\d+), (\d+), (\d+)\]""".r
  val instructionRegex = """(\d+) (\d+) (\d+) (\d+)""".r
  val afterRegex = """After:  \[(\d+), (\d+), (\d+), (\d+)\]""".r

  val samples = Source.fromFile("src/main/resources/day16/Part1Input.txt").getLines()
    .foldLeft((Seq[Sample](), None: Option[Vector[Int]], None: Option[Instruction])) {
      (acc, line) =>
        line match {
          case beforeRegex(reg0, reg1, reg2, reg3) => (acc._1, Some(Vector(reg0.toInt, reg1.toInt, reg2.toInt, reg3.toInt)), None)
          case instructionRegex(opcode, a, b, c) => (acc._1, acc._2, Some(Instruction(opcode.toInt, InstructionArgs(a.toInt, b.toInt, c.toInt))))
          case afterRegex(reg0, reg1, reg2, reg3) => (acc._1 :+ Sample(acc._2.get, acc._3.get, Vector(reg0.toInt, reg1.toInt, reg2.toInt, reg3.toInt)), None, None)
          case _ => acc
        }
    }._1

  val operationAnalyzer = new OperationAnalyzer()

  val count = samples.count(sample => operationAnalyzer.possibleOperations(sample).size >= 3)
  println(s"Part One: $count")
}
