package adventofcode.dayeight

class TreeParser {
  case class ParseState(remainingInput: Seq[Int], children: Seq[Node])
  case class ParseResult(node: Node, remainingInput: Seq[Int])

  def parseTree(input: Seq[Int]) : Node = {
    parseNode(input).node
  }

  private def parseNode(input: Seq[Int]): ParseResult = {
    input match {
      case numChildren :: numMetadata :: rest =>
        val childrenParseResult = (1 to numChildren).foldLeft(ParseState(rest, Seq())) {
          (parseState, _) =>
            val childParseResult = parseNode(parseState.remainingInput)
            ParseState(childParseResult.remainingInput, parseState.children :+ childParseResult.node)
        }
        val metadata = childrenParseResult.remainingInput.take(numMetadata)
        ParseResult(Node(childrenParseResult.children, metadata), childrenParseResult.remainingInput.drop(numMetadata))
    }
  }

}
