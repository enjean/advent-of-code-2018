package adventofcode.dayten

case class SkyGrid(points: Seq[Point]) {

  def isShowingWords : Boolean = {
    false
  }

  def advance : SkyGrid = {
    SkyGrid(points.map(_.advance))
  }

  def prettyPrint : String = {
    val positions = points.map(_.position)
    val xVals = positions.map(_.x)
    val yVals = positions.map(_.y)

    //for (i <- 1 to 2; j <- 1 to 2)
    (yVals.min to yVals.max).foldLeft(Seq[String]()) {
      (rows, y) =>
        rows :+ (xVals.min to xVals.max).foldLeft("") {
          (row, x) =>
            val charToPrint = if (positions.contains(Position(x, y))) "#" else "."
            row + charToPrint
        }
    }.mkString("\n")
  }
}
