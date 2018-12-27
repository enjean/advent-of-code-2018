package adventofcode.dayseven

import org.scalatest.{FlatSpec, Matchers}

class InstructionSetTest extends FlatSpec with Matchers {

  "stepIsReady" should "return whether step can be executed" in {
    //    Only C is available, and so it is done first.
    //    Next, both A and F are available. A is first alphabetically, so it is done next.
    //      Then, even though F was available earlier, steps B and D are now also available, and B is the first alphabetically of the three.
    //      After that, only D and F are available. E is not available because only some of its prerequisites are complete. Therefore, D is completed next.
    //    F is the only choice, so it is done next.
    //      Finally, E is completed.

    val step1Set = InstructionSet(Seq(
      Instruction('A', 'B'),
      Instruction('A', 'D'),
      Instruction('B', 'E'),
      Instruction('D', 'E'),
      Instruction('F', 'E')
    )
    )
    step1Set.stepIsReady('A') shouldBe true
    step1Set.stepIsReady('F') shouldBe true

    val step2Set = InstructionSet(Seq(
      Instruction('B', 'E'),
      Instruction('D', 'E')
    )
    )
    step2Set.stepIsReady('B') shouldBe true
    step2Set.stepIsReady('D') shouldBe true
    step2Set.stepIsReady('E') shouldBe false

    InstructionSet(Seq(
      Instruction('D', 'E')
    )
    ).stepIsReady('E') shouldBe false

    InstructionSet(Seq()).stepIsReady('E') shouldBe true
  }

  "findReadySteps" should "find steps that can be executed" in {
    InstructionSet(Seq(
      Instruction('C', 'A'),
      Instruction('C', 'F'),
      Instruction('A', 'B'),
      Instruction('A', 'D'),
      Instruction('B', 'E'),
      Instruction('D', 'E'),
      Instruction('F', 'E')
    )
    ).findReadySteps shouldBe Seq('C')
  }

  "execute step" should "return ready steps and instructions still to complete" in {
    val set1 = InstructionSet(Seq(
      Instruction('C', 'A'),
      Instruction('C', 'F'),
      Instruction('A', 'B'),
      Instruction('A', 'D'),
      Instruction('B', 'E'),
      Instruction('D', 'E'),
      Instruction('F', 'E')
    ))

    val result1 = set1.executeStep('C')
    result1.readySteps should contain theSameElementsAs Seq('A', 'F')
    result1.instructionsLeft.instructions should contain theSameElementsAs Seq(
      Instruction('A', 'B'),
      Instruction('A', 'D'),
      Instruction('B', 'E'),
      Instruction('D', 'E'),
      Instruction('F', 'E'))

    val result2 = result1.instructionsLeft.executeStep('A')
    result2.readySteps should contain theSameElementsAs Seq('B', 'D')
    result2.instructionsLeft.instructions should contain theSameElementsAs Seq(
      Instruction('B', 'E'),
      Instruction('D', 'E'),
      Instruction('F', 'E'))

    InstructionSet(Seq(Instruction('F', 'E'))).executeStep('F') shouldBe (Seq('E'), InstructionSet(Seq()))
  }
}
