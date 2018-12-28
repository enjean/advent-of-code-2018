package adventofcode.dayeight

import org.scalatest.{FlatSpec, Matchers}

class NodeTest extends FlatSpec with Matchers {
  private val D = Node(Nil, Seq(99))
  private val C = Node(
    Seq(D),
    Seq(2)
  )
  private val B = Node(Nil, Seq(10, 11, 12))
  private val A = Node(
    Seq(
      B,
      C
    ),
    Seq(1, 1, 2)
  )

  "metadata sum" should "be sum of node metadata entries" in {
    A.metadataSum shouldBe 138
  }

  "value of node with no children" should "be metadata sum" in {
    B.value shouldBe 33
    D.value shouldBe 99
  }

  "value of node with nonexistent reference" should "be 0" in {
    //Node C has one metadata entry, 2. Because node C has only one child node,
    // 2 references a child node which does not exist, and so the value of node C is 0.
    C.value shouldBe 0
  }

  "value of node with multiple children" should "be sum of referenced values" in {
    // Node A has three metadata entries: 1, 1, and 2. The 1 references node A's first child node, B,
    // and the 2 references node A's second child node, C. Because node B has a value of 33 and node C has a value of 0,
    // the value of node A is 33+33+0=66.
    A.value shouldBe 66
  }
}
