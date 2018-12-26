package adventofcode.daysix

import org.scalatest.{FlatSpec, Matchers}

class GridAnalyzerTest extends FlatSpec with Matchers {

  private val gridAnalyzer = new GridAnalyzer()

  val A = Coordinate(1, 1)
  val B = Coordinate(1, 6)
  val C = Coordinate(8, 3)
  val D = Coordinate(3, 4)
  val E = Coordinate(5, 5)
  val F = Coordinate(8, 9)
  val coords = Seq(A, B, C, D, E, F)

  "find largest non infinite area" must "find correct" in {
    gridAnalyzer.findLargestNonInfiniteArea(coords) shouldBe 17
  }

  "area is infinite" must  "exclude infinite areas" in {
    val grid = new GridGenerator().generateGrid(coords)
    gridAnalyzer.areaIsInfinite(grid, A) shouldBe true
    gridAnalyzer.areaIsInfinite(grid, B) shouldBe true
    gridAnalyzer.areaIsInfinite(grid, C) shouldBe true
    gridAnalyzer.areaIsInfinite(grid, D) shouldBe false
    gridAnalyzer.areaIsInfinite(grid, E) shouldBe false
    gridAnalyzer.areaIsInfinite(grid, F) shouldBe true
  }
}
