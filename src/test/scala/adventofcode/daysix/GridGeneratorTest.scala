package adventofcode.daysix

import org.scalatest.{FlatSpec, Matchers}

class GridGeneratorTest extends FlatSpec with Matchers {
  //    0123456789
  //  0 aaaaa.cccc
  //  1 aAaaa.cccc
  //  2 aaaddecccc
  //  3 aadddeccCc
  //  4 ..dDdeeccc
  //  5 bb.deEeecc
  //  6 bBb.eeee..
  //  7 bbb.eeefff
  //  8 bbb.eeffff
  //  9 bbb.ffffFf

  "grid generation" should "calculate coordinate closest to other squares" in {
    val A = Coordinate(1, 1)
    val B = Coordinate(1, 6)
    val C = Coordinate(8, 3)
    val D = Coordinate(3, 4)
    val E = Coordinate(5, 5)
    val F = Coordinate(8, 9)

    new GridGenerator().generateGrid(Seq(A, B, C, D, E, F)) shouldBe Map(
      // Do not include x = 0 since min x is 1
      // Do not include y = 0 since min y is 1
      Coordinate(1, 2) -> A,
      Coordinate(1, 3) -> A,
      // (1,4) is equally close to A and D
      Coordinate(1, 5) -> B,
      Coordinate(1, 7) -> B,
      Coordinate(1, 8) -> B,
      Coordinate(1, 9) -> B,

      Coordinate(2, 1) -> A,
      Coordinate(2, 2) -> A,
      Coordinate(2, 3) -> D,
      Coordinate(2, 4) -> D,
      // (2,5) is equally close to B and D
      Coordinate(2, 6) -> B,
      Coordinate(2, 7) -> B,
      Coordinate(2, 8) -> B,
      Coordinate(2, 9) -> B,

      Coordinate(3, 1) -> A,
      Coordinate(3, 2) -> D,
      Coordinate(3, 3) -> D,
      Coordinate(3, 5) -> D,
      // (3, 6) is equally close to B and D
      // (3, 7) is equally close to B and D
      // (3, 8) is equally close to B and D
      // (3, 9) is equally close to B and D

      Coordinate(4, 1) -> A,
      Coordinate(4, 2) -> D,
      Coordinate(4, 3) -> D,
      Coordinate(4, 4) -> D,
      Coordinate(4, 5) -> E,
      Coordinate(4, 6) -> E,
      Coordinate(4, 7) -> E,
      Coordinate(4, 8) -> E,
      Coordinate(4, 9) -> F,

      // (5, 1) is equally close to A and E
      Coordinate(5, 2) -> E,
      Coordinate(5, 3) -> E,
      Coordinate(5, 4) -> E,
      Coordinate(5, 6) -> E,
      Coordinate(5, 7) -> E,
      Coordinate(5, 8) -> E,
      Coordinate(5, 9) -> F,

      Coordinate(6, 1) -> C,
      Coordinate(6, 2) -> C,
      Coordinate(6, 3) -> C,
      Coordinate(6, 4) -> E,
      Coordinate(6, 5) -> E,
      Coordinate(6, 6) -> E,
      Coordinate(6, 7) -> E,
      Coordinate(6, 8) -> F,
      Coordinate(6, 9) -> F,

      Coordinate(7, 1) -> C,
      Coordinate(7, 2) -> C,
      Coordinate(7, 3) -> C,
      Coordinate(7, 4) -> C,
      Coordinate(7, 5) -> E,
      Coordinate(7, 6) -> E,
      Coordinate(7, 7) -> F,
      Coordinate(7, 8) -> F,
      Coordinate(7, 9) -> F,

      Coordinate(8, 1) -> C,
      Coordinate(8, 2) -> C,
     //  Coordinate(8, 3) = C,
      Coordinate(8, 4) -> C,
      Coordinate(8, 5) -> C,
      // Coordinate(8, 6) -> C and F,
      Coordinate(8, 7) -> F,
      Coordinate(8, 8) -> F
      // Coordinate(8, 9) = F,

      // Skipping x = 9 since max x is 8
    )
  }
}
