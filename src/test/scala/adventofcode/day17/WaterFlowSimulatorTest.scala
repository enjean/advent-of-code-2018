package adventofcode.day17

import org.scalatest.{Matchers, WordSpec}

class WaterFlowSimulatorTest extends WordSpec with Matchers {
  //    44444455555555
  //    99999900000000
  //    45678901234567
  //  0 ......+.......
  //  1 ............#.
  //  2 .#..#.......#.
  //  3 .#..#..#......
  //  4 .#..#..#......
  //  5 .#.....#......
  //  6 .#.....#......
  //  7 .#######......
  //  8 ..............
  //  9 ..............
  // 10 ....#.....#...
  // 11 ....#.....#...
  // 12 ....#.....#...
  // 13 ....#######...
  private val exampleScanModel = new ScanParser().parseScan(List(
    "x=495, y=2..7",
    "y=7, x=495..501",
    "x=501, y=3..7",
    "x=498, y=2..4",
    "x=506, y=1..2",
    "x=498, y=10..13",
    "x=504, y=10..13",
    "y=13, x=498..504"
  ))
  private val waterFlowSimulator = new WaterFlowSimulator(exampleScanModel)

  //  "getNextSquare" should {
  //    "move downward if no blocker" in {
  //      waterFlowSimulator.getNextSquare(Coordinate(500, 0), UndergroundState(Set(), Set())) shouldBe Some(Coordinate(500, 1))
  //      waterFlowSimulator.getNextSquare(Coordinate(502, 2), UndergroundState(Set(), Set())) shouldBe Some(Coordinate(502, 3))
  //    }
  //  }

  "flowWaterFrom" should {
    //    "return row as filled if blocked on both sides" in {
    //      waterFlowSimulator.flowWaterFrom(Coordinate(500, 6)) shouldBe UndergroundState(
    //        Set(
    //          Coordinate(496, 6),
    //          Coordinate(497, 6),
    //          Coordinate(498, 6),
    //          Coordinate(499, 6),
    //          Coordinate(500, 6)
    //        ),
    //        Set())
    //
    //      waterFlowSimulator.flowWaterFrom(Coordinate(502, 12)) shouldBe UndergroundState(
    //        Set(
    //          Coordinate(499, 12),
    //          Coordinate(500, 12),
    //          Coordinate(501, 12),
    //          Coordinate(502, 12),
    //          Coordinate(503, 12)
    //        ),
    //        Set())
    //    }
    //
    //    "return row as filled if blocked on both sides and row below is filled" in {
    //      waterFlowSimulator.flowWaterFrom(Coordinate(500, 5)) shouldBe UndergroundState(
    //        Set(
    //          Coordinate(496, 6),
    //          Coordinate(497, 6),
    //          Coordinate(498, 6),
    //          Coordinate(499, 6),
    //          Coordinate(500, 6),
    //          Coordinate(496, 5),
    //          Coordinate(497, 5),
    //          Coordinate(498, 5),
    //          Coordinate(499, 5),
    //          Coordinate(500, 5)
    //        ),
    //        Set())
    //
    //      waterFlowSimulator.flowWaterFrom(Coordinate(502, 11)) shouldBe UndergroundState(
    //        Set(
    //          Coordinate(499, 12),
    //          Coordinate(500, 12),
    //          Coordinate(501, 12),
    //          Coordinate(502, 12),
    //          Coordinate(503, 12),
    //          Coordinate(499, 11),
    //          Coordinate(500, 11),
    //          Coordinate(501, 11),
    //          Coordinate(502, 11),
    //          Coordinate(503, 11)
    //        ),
    //        Set())
    //    }

    "stop running and return passthrough if beyond max Y" in {
      waterFlowSimulator.flowWaterFrom(Coordinate(505, 11)) shouldBe UndergroundState(
        Set(),
        Set(
          Coordinate(505, 11),
          Coordinate(505, 12),
          Coordinate(505, 13)
        ))
    }

    "spillover if not blocked on either side" in {
      val result1 = waterFlowSimulator.flowWaterFrom(Coordinate(502, 9))
      exampleScanModel.prettyPrint(result1)
      result1 shouldBe UndergroundState(
        Set(
          Coordinate(499, 12),
          Coordinate(500, 12),
          Coordinate(501, 12),
          Coordinate(502, 12),
          Coordinate(503, 12),
          Coordinate(499, 11),
          Coordinate(500, 11),
          Coordinate(501, 11),
          Coordinate(502, 11),
          Coordinate(503, 11),
          Coordinate(499, 10),
          Coordinate(500, 10),
          Coordinate(501, 10),
          Coordinate(502, 10),
          Coordinate(503, 10)
        ),
        Set(
          Coordinate(497, 10),
          Coordinate(497, 11),
          Coordinate(497, 12),
          Coordinate(497, 13),
          Coordinate(505, 10),
          Coordinate(505, 11),
          Coordinate(505, 12),
          Coordinate(505, 13),
          Coordinate(497, 9),
          Coordinate(498, 9),
          Coordinate(499, 9),
          Coordinate(500, 9),
          Coordinate(501, 9),
          Coordinate(502, 9),
          Coordinate(503, 9),
          Coordinate(504, 9),
          Coordinate(505, 9)
        ))
    }

    "spillover one blocked side" in {
      waterFlowSimulator.flowWaterFrom(Coordinate(500, 2)) shouldBe UndergroundState(
        Set(
          Coordinate(499, 3),
          Coordinate(500, 3),
          Coordinate(499, 4),
          Coordinate(500, 4),
          Coordinate(496, 5),
          Coordinate(497, 5),
          Coordinate(498, 5),
          Coordinate(499, 5),
          Coordinate(500, 5),
          Coordinate(496, 6),
          Coordinate(497, 6),
          Coordinate(498, 6),
          Coordinate(499, 6),
          Coordinate(500, 6),
          Coordinate(499, 10),
          Coordinate(500, 10),
          Coordinate(501, 10),
          Coordinate(502, 10),
          Coordinate(503, 10),
          Coordinate(499, 11),
          Coordinate(500, 11),
          Coordinate(501, 11),
          Coordinate(502, 11),
          Coordinate(503, 11),
          Coordinate(499, 12),
          Coordinate(500, 12),
          Coordinate(501, 12),
          Coordinate(502, 12),
          Coordinate(503, 12)
        ),
        Set(
          Coordinate(500, 2),
          Coordinate(499, 2),
          Coordinate(501, 2),
          Coordinate(502, 2),
          Coordinate(502, 3),
          Coordinate(502, 4),
          Coordinate(502, 5),
          Coordinate(502, 6),
          Coordinate(502, 7),
          Coordinate(502, 8),
          Coordinate(497, 9),
          Coordinate(498, 9),
          Coordinate(499, 9),
          Coordinate(500, 9),
          Coordinate(501, 9),
          Coordinate(502, 9),
          Coordinate(503, 9),
          Coordinate(504, 9),
          Coordinate(505, 9),
          Coordinate(497, 10),
          Coordinate(497, 11),
          Coordinate(497, 12),
          Coordinate(497, 13),
          Coordinate(505, 10),
          Coordinate(505, 11),
          Coordinate(505, 12),
          Coordinate(505, 13)
        ))
    }

    "cup inside cup" in {
      //    44444455555555
      //    99999900000000
      //    45678901234567
      //  0 ......+.......
      //  1 ..............
      //  2 .........#....
      //  3 .........#....
      //  4 #........#....
      //  5 #..#.#...#....
      //  6 #..###...#....
      //  7 #........#....
      //  8 ##########....
      //  9 ..............
      val scanModel = new ScanParser().parseScan(List(
        "x=494, y=4..8",
        "y=8, x=494..503",
        "x=503, y=2..8",
        "x=497, y=5..6",
        "y=6, x=497..499",
        "x=499, y=5..6",
      ))
      val state = new WaterFlowSimulator(scanModel).flowWaterFrom(Coordinate(500, 1))
      scanModel.prettyPrint(state)
      //    444444455555555
      //    999999900000000
      //    345678901234567
      //  0 .......+.......
      //  1 .......|.......
      //  2 .......|..#....
      //  3 ||||||||||#....
      //  4 |#~~~~~~~~#....
      //  5 |#~~#~#~~~#....
      //  6 |#~~###~~~#....
      //  7 |#~~~~~~~~#....
      //  8 |##########....
      //  9 ...............
      state shouldBe UndergroundState(
        Set(
          Coordinate(495, 4), Coordinate(496, 4), Coordinate(497, 4), Coordinate(498, 4), Coordinate(499, 4), Coordinate(500, 4), Coordinate(501, 4), Coordinate(502, 4),
          Coordinate(495, 5), Coordinate(496, 5), Coordinate(498, 5), Coordinate(500, 5), Coordinate(501, 5), Coordinate(502, 5),
          Coordinate(495, 6), Coordinate(496, 6), Coordinate(500, 6), Coordinate(501, 6), Coordinate(502, 6),
          Coordinate(495, 7), Coordinate(496, 7), Coordinate(497, 7), Coordinate(498, 7), Coordinate(499, 7), Coordinate(500, 7), Coordinate(501, 7), Coordinate(502, 7)
        ),
        Set(
          Coordinate(500, 1),
          Coordinate(500, 2),
          Coordinate(493, 3), Coordinate(494, 3), Coordinate(495, 3), Coordinate(496, 3), Coordinate(497, 3), Coordinate(498, 3), Coordinate(499, 3), Coordinate(500, 3), Coordinate(501, 3), Coordinate(502, 3),
          Coordinate(493, 4),
          Coordinate(493, 5),
          Coordinate(493, 6),
          Coordinate(493, 7),
          Coordinate(493, 8)
        )
      )
    }
    "big cup inside cup" in {

      //        444444555555555555555
      //        999999000000000011111
      //        456789012345678901234
      //      0 ............+........
      //      1 .....................
      //      2 #...................#
      //      3 #...................#
      //      4 #...................#
      //      5 #...................#
      //      6 #...................#
      //      7 #...........#.#.....#
      //      8 #...........#.#.....#
      //      9 #...........#.#.....#
      //     10 #...........###.....#
      //     11 #...................#
      //     12 #...................#
      //     13 #...................#
      //     14 #...................#
      //     15 #...................#
      //     16 #####################
      val scanModel = new ScanParser().parseScan(List(
        "x=494, y=2..16",
        "y=16, x=494..514",
        "x=514, y=2..16",
        "x=506, y=7..10",
        "y=10, x=506..508",
        "x=508, y=7..10",
      ))
      val state = new WaterFlowSimulator(scanModel).flowWaterFrom(Coordinate(506, 0))
    }

  }

}
