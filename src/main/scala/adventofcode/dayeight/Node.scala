package adventofcode.dayeight

case class Node(children: Seq[Node], metadataEntries: Seq[Int]) {
  def metadataSum: Int =  {
    metadataEntries.sum + children.map(_.metadataSum).sum
  }

  def value: Int = {
    children match {
      case Nil => metadataEntries.sum
      case _ =>
        metadataEntries.foldLeft(0) {
          (valueSoFar, index) =>
            if (index > children.size) valueSoFar
            else valueSoFar + children(index - 1).value
        }
    }
  }
}
