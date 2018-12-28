package adventofcode.dayeight

import org.scalatest.{FlatSpec, Matchers}

class TreeParserTest extends FlatSpec with Matchers {
  private val treeParser = new TreeParser()

  "parse" should "be able to handle node with no children" in {
    treeParser.parseTree(Seq(0, 3, 10, 11, 1)) shouldBe Node(Nil, Seq(10, 11, 1))
  }

  "parse" should "be able to handle node with one child" in {
    treeParser.parseTree(Seq(1, 1, 0, 1, 99, 2)) shouldBe Node(Seq(Node(Nil, Seq(99))), Seq(2))
  }

  "parse" should "be able to handle tree with three levels" in {
//    A, which has 2 child nodes (B, C) and 3 metadata entries (1, 1, 2).
//      B, which has 0 child nodes and 3 metadata entries (10, 11, 12).
//      C, which has 1 child node (D) and 1 metadata entry (2).
//      D, which has 0 child nodes and 1 metadata entry (99).
    treeParser.parseTree(Seq(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2)) shouldBe Node(
      Seq(
        Node(Nil, Seq(10, 11, 12)),
        Node(
          Seq(
            Node(Nil, Seq(99))
          ),
          Seq(2)
        )
      ),
      Seq(1, 1, 2))
  }
}
