package adventofcode.dayeight

import org.scalatest.{FlatSpec, Matchers}

class NodeTest extends FlatSpec with Matchers {
  "metadata sum" should "be sum of node metadata entries" in {
    Node(
      Seq(
        Node(Nil, Seq(10, 11, 12)),
        Node(
          Seq(
            Node(Nil, Seq(99))
          ),
          Seq(2)
        )
      ),
      Seq(1, 1, 2)
    ).metadataSum shouldBe 138
  }
}
