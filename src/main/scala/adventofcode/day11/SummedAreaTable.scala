package adventofcode.day11

class SummedAreaTable(powerLevelSums: Map[(Int, Int), Int]) {

  def powerOfSquareRegion(x: Int, y: Int, sideLength: Int): Int = {
//    Once the summed-area table has been computed, evaluating the sum of intensities over any rectangular area requires
    // exactly four array references regardless of the area size. That is, the notation in the figure at right,
    // having A=(x0, y0), B=(x1, y0), C=(x0, y1) and D=(x1, y1), the sum of i(x,y) over the rectangle spanned by A, B,C and D is:
//
//    {\displaystyle \sum _{\begin{smallmatrix}x_{0}<x\leq x_{1}\\y_{0}<y\leq y_{1}\end{smallmatrix}}i(x,y)=I(D)+I(A)-I(B)-I(C)}
    // \sum _{\begin{smallmatrix}x_{0}<x\leq x_{1}\\y_{0}<y\leq y_{1}\end{smallmatrix}}i(x,y)=I(D)+I(A)-I(B)-I(C)
    val a = powerLevelSums.getOrElse((x - 1, y -1), 0)
    val b = powerLevelSums.getOrElse((x + sideLength - 1, y - 1), 0)
    val c = powerLevelSums.getOrElse((x -1, y + sideLength - 1), 0)
    val d = powerLevelSums(x + sideLength - 1, y + sideLength - 1)

    d + a - b - c
  }

  def highestPowerRegionAtCoord(x: Int, y: Int) : (Int, Int) = {
    val maxSquareSize = Math.min(301 - x, 301 - y)
    val powersPerSideLength = (1 to maxSquareSize).map { squareSize =>
      squareSize -> powerOfSquareRegion(x, y, squareSize)
    }
    powersPerSideLength.maxBy(_._2)
  }
}

object SummedAreaTable {
  def apply(serialNumber: Int): SummedAreaTable = {
    val allCoordinates = for (
      x <- 1 to 300;
      y <- 1 to 300
    ) yield {
      (x, y)
    }
    val powerLevelSumTable = allCoordinates.foldLeft(Map[(Int, Int), Int]()) {
      (powerLevelSums, coordinate) =>
        // I(x,y)=i(x,y)+I(x,y-1)+I(x-1,y)-I(x-1,y-1)
        val powerLevel = FuelCell(coordinate._1, coordinate._2).powerLevel(serialNumber)
        val top = powerLevelSums.getOrElse((coordinate._1, coordinate._2 - 1), 0)
        val left = powerLevelSums.getOrElse((coordinate._1 - 1, coordinate._2), 0)
        val topLeft = powerLevelSums.getOrElse((coordinate._1 - 1, coordinate._2-1), 0)
        val sumAtCoord = powerLevel + top + left - topLeft
        powerLevelSums + ((coordinate._1, coordinate._2) -> sumAtCoord)
    }
    new SummedAreaTable(powerLevelSumTable)
  }
}