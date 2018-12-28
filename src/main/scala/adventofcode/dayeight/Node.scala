package adventofcode.dayeight

case class Node(children: Seq[Node], metadataEntries: Seq[Int]) {
  def metadataSum: Int =  {
    metadataEntries.sum + children.map(_.metadataSum).sum
  }
}
