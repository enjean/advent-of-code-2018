package adventofcode.day16

import org.scalatest.{FlatSpec, Matchers}

class OperationSetTest extends FlatSpec with Matchers {

  "addr" should "stores into register C the result of adding register A and register B" in {
    OperationSet.addr(Vector(2, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(2, 3, 0, 5)
  }

  "addi" should "stores into register C the result of adding register A and value B" in {
    OperationSet.addi(Vector(2, 1, 0, 4), InstructionArgs(0, 7, 3)) shouldBe Vector(2, 1, 0, 9)
  }

  "mulr" should "stores into register C the result of multiplying register A and register B" in {
    OperationSet.mulr(Vector(2, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(2, 3, 0, 6)
  }

  "muli" should "stores into register C the result of multiplying register A and value B" in {
    OperationSet.muli(Vector(2, 1, 0, 4), InstructionArgs(0, 7, 3)) shouldBe Vector(2, 1, 0, 14)
  }

  "banr" should "stores into register C the result of the bitwise AND of register A and register B" in {
    OperationSet.banr(Vector(2, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(2, 3, 0, 2)
  }

  "bani" should "stores into register C the result of the bitwise AND of register A and value B" in {
    OperationSet.bani(Vector(9, 1, 0, 4), InstructionArgs(0, 7, 3)) shouldBe Vector(9, 1, 0, 1)
  }

  "borr" should "stores into register C the result of the bitwise OR of register A and register B" in {
    OperationSet.borr(Vector(2, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(2, 3, 0, 3)
  }

  "bori" should "stores into register C the result of the bitwise OR of register A and value B" in {
    OperationSet.bori(Vector(9, 1, 0, 4), InstructionArgs(0, 7, 3)) shouldBe Vector(9, 1, 0, 15)
  }

  "setr" should "copies the contents of register A into register C" in {
    OperationSet.setr(Vector(2, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(2, 3, 0, 2)
  }

  "seti" should "stores value A into register C" in {
    OperationSet.seti(Vector(9, 1, 0, 4), InstructionArgs(0, 7, 3)) shouldBe Vector(9, 1, 0, 0)
  }

  "gtir" should "sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0." in {
    OperationSet.gtir(Vector(9, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(9, 3, 0, 0)
  }

  "gtri" should "sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0." in {
    OperationSet.gtri(Vector(2, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(2, 3, 0, 1)
  }

  "gtrr" should "sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0." in {
    OperationSet.gtrr(Vector(2, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(2, 3, 0, 0)
    OperationSet.gtrr(Vector(3, 2, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(3, 2, 0, 1)
  }

  "eqir"should "sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0." in {
    OperationSet.eqir(Vector(9, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(9, 3, 0, 0)
    OperationSet.eqir(Vector(9, 3, 0, 4), InstructionArgs(3, 1, 3)) shouldBe Vector(9, 3, 0, 1)
  }

  "eqri"should "sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0." in {
    OperationSet.eqri(Vector(9, 3, 0, 4), InstructionArgs(0, 1, 3)) shouldBe Vector(9, 3, 0, 0)
    OperationSet.eqri(Vector(9, 3, 0, 4), InstructionArgs(0, 9, 3)) shouldBe Vector(9, 3, 0, 1)
  }

  "eqrr"should "sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0." in {
    OperationSet.eqrr(Vector(9, 3, 0, 4), InstructionArgs(0, 2, 3)) shouldBe Vector(9, 3, 0, 0)
    OperationSet.eqrr(Vector(0, 3, 0, 4), InstructionArgs(0, 2, 3)) shouldBe Vector(0, 3, 0, 1)
  }


}
