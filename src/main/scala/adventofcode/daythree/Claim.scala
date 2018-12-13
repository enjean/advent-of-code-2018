package adventofcode.daythree

case class Square(x: Int, y: Int)

case class Claim(id: String, x: Int, y: Int, width: Int, height: Int) {
  def getSquares: Seq[Square] = {
    (x until x + width)
      .flatMap(xVal => (y until y + height).map(Square(xVal, _)))
  }
}

object Claim {
  def apply(row: String): Claim = {
    //#123 @ 3,2: 5x4
    val atSplit = row.split(" @ ")
    val colonSplit = atSplit.last.split(": ")
    val xySplit = colonSplit.head.split(",")
    val hwSplit = colonSplit.last.split("x")
    Claim(atSplit.head, xySplit.head.toInt, xySplit.last.toInt, hwSplit.head.toInt, hwSplit.last.toInt)
  }
}
