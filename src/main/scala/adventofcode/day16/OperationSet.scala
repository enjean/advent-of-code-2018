package adventofcode.day16

object OperationSet {

  def addr(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, registers(instructionArgs.a) + registers(instructionArgs.b))

  def addi(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, registers(instructionArgs.a) + instructionArgs.b)

  def mulr(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, registers(instructionArgs.a) * registers(instructionArgs.b))

  def muli(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, registers(instructionArgs.a) * instructionArgs.b)

  def banr(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, registers(instructionArgs.a) & registers(instructionArgs.b))

  def bani(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, registers(instructionArgs.a) & instructionArgs.b)

  def borr(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, registers(instructionArgs.a) | registers(instructionArgs.b))

  def bori(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, registers(instructionArgs.a) | instructionArgs.b)

  def setr(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, registers(instructionArgs.a))

  def seti(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, instructionArgs.a)

  def gtir(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, if (instructionArgs.a > registers(instructionArgs.b)) 1 else 0)

  def gtri(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, if (registers(instructionArgs.a) > instructionArgs.b) 1 else 0)

  def gtrr(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, if (registers(instructionArgs.a) > registers(instructionArgs.b)) 1 else 0)

  def eqir(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, if (instructionArgs.a == registers(instructionArgs.b)) 1 else 0)

  def eqri(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, if (registers(instructionArgs.a) == instructionArgs.b) 1 else 0)

  def eqrr(registers: Vector[Int], instructionArgs: InstructionArgs): Vector[Int] =
    registers.updated(instructionArgs.c, if (registers(instructionArgs.a) == registers(instructionArgs.b)) 1 else 0)

  val operationMap: Map[String, (Vector[Int], InstructionArgs) => Vector[Int]] = Map(
    "addr" -> addr,
    "addi" -> addi,
    "mulr" -> mulr,
    "muli" -> muli,
    "banr" -> banr,
    "bani" -> bani,
    "borr" -> borr,
    "bori" -> bori,
    "setr" -> setr,
    "seti" -> seti,
    "gtir" -> gtir,
    "gtri" -> gtri,
    "gtrr" -> gtrr,
    "eqir" -> eqir,
    "eqri" -> eqri,
    "eqrr" -> eqrr

  )

}
