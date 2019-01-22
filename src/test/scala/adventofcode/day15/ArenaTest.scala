package adventofcode.day15

import org.scalatest.{Matchers, WordSpec}

class ArenaTest extends WordSpec with Matchers {

  private val arenaParser = new ArenaParser()

  //  Targets:      In range:     Reachable:    Nearest:      Chosen:
  //  #######       #######       #######       #######       #######
  //  #E..G.#       #E.?G?#       #E.@G.#       #E.!G.#       #E.+G.#
  //  #...#.#  -->  #.?.#?#  -->  #.@.#.#  -->  #.!.#.#  -->  #...#.#
  //  #.G.#G#       #?G?#G#       #@G@#G#       #!G.#G#       #.G.#G#
  //  #######       #######       #######       #######       #######
  //
  //  "findSquaresInRangeOf" should {
  //    "find open squares adjacent to units of type" in {
  //      val arenaParse = new ArenaParser().parseArena(List(
  //        "#######",
  //        "#E..G.#",
  //        "#...#.#",
  //        "#.G.#G#",
  //        "#######"
  //      ))
  //      val arena = Arena(arenaParse._1, arenaParse._2)
  //
  //      arena.findSquaresInRangeOf(UnitType.Goblin) should contain theSameElementsAs Seq(
  //        Coordinate(3, 1),
  //        Coordinate(5, 1),
  //        Coordinate(2, 2),
  //        Coordinate(5, 2),
  //        Coordinate(1, 3),
  //        Coordinate(3, 3)
  //      )
  //    }
  //  }

  "nearestInRangeSquareSearch" should {
    "return all in range squares that are closest" in {
      //      Nearest
      //      #######
      //      #E.!G.#
      //      #.!.#.#
      //      #!G.#G#
      //      #######
      arenaParser.parseArena(List(
        "#######",
        "#E..G.#",
        "#...#.#",
        "#.G.#G#",
        "#######"
      )).nearestInRangeSquareSearch(
        UnitType.Goblin,
        Coordinate(1, 1)
      ) should contain theSameElementsAs Seq(
        Coordinate(3, 1),
        Coordinate(2, 2),
        Coordinate(1, 3)
      )
    }

    "when no reachable in range squares, return Nil" in {
      arenaParser.parseArena(List(
        "#######",
        "#E..#.#",
        "#...#.#",
        "#...#G#",
        "#######"
      )).nearestInRangeSquareSearch(
        UnitType.Goblin,
        Coordinate(1, 1)
      ) shouldBe Nil
    }

    "when no available in range squares, return Nil" in {
      arenaParser.parseArena(List(
        "#######",
        "#E....#",
        "#....E#",
        "#...EG#",
        "#######"
      )) nearestInRangeSquareSearch(
        UnitType.Goblin,
        Coordinate(1, 1)
      ) shouldBe Nil
    }
  }


  "findClosestInRangeSquare" should {
    "return in range square closest to start" in {
      arenaParser.parseArena(List(
        "#######",
        "#E..G.#",
        "#...#.#",
        "#.G.#G#",
        "#######"
      )).findClosestInRangeSquare(Coordinate(1, 1), UnitType.Goblin) shouldBe Some(Coordinate(3, 1))
    }

    "when no reachable in range squares, return Nil" in {
      arenaParser.parseArena(List(
        "#######",
        "#E..#.#",
        "#...#.#",
        "#...#G#",
        "#######"
      )).findClosestInRangeSquare(Coordinate(1, 1), UnitType.Goblin) shouldBe None
    }

    "when no available in range squares, return Nil" in {
      arenaParser.parseArena(List(
        "#######",
        "#E....#",
        "#....E#",
        "#...EG#",
        "#######"
      )).findClosestInRangeSquare(Coordinate(1, 1), UnitType.Goblin) shouldBe None
    }
  }

//  "distanceTo" should {
//    val arena = arenaParser.parseArena(List(
//      "#######",
//      "#.E...#",
//      "#.....#",
//      "#...G.#",
//      "#######"
//    ))
//
//    "return shortest distance to target from 1,1" in {
//      arena.distanceTo(Coordinate(1, 1), Coordinate(4, 2)) shouldBe Some(4)
//    }
//
//    "return shortest distance to target from 3,1" in {
//      arena.distanceTo(Coordinate(3, 1), Coordinate(4, 2)) shouldBe Some(2)
//    }
//
//    "return shortest distance to target from 2,2" in {
//      arena.distanceTo(Coordinate(2, 2), Coordinate(4, 2)) shouldBe Some(2)
//    }
//
//    "handle dead ends" in {
//      arenaParser.parseArena(List(
//        "#######",
//        "#.E...#",
//        "#.#...#",
//        "#.#.G.#",
//        "#######"
//      )).distanceTo(Coordinate(1, 1), Coordinate(4, 2)) shouldBe None
//    }
//  }

  "findNextStepTowards" should {
    "return next step along shortest path in reading order" in {
      //      In range:     Nearest:      Chosen:       Distance:     Step:
      //      #######       #######       #######       #######       #######
      //      #.E...#       #.E...#       #.E...#       #4E212#       #..E..#
      //      #...?.#  -->  #...!.#  -->  #...+.#  -->  #32101#  -->  #.....#
      //      #..?G?#       #..!G.#       #...G.#       #432G2#       #...G.#
      //      #######       #######       #######       #######       #######

      arenaParser.parseArena(List(
        "#######",
        "#.E...#",
        "#.....#",
        "#...G.#",
        "#######"
      )).findNextStepTowards(Coordinate(2, 1), Coordinate(4, 2)) shouldBe Coordinate(3, 1)
    }
  }

  "moveUnit" should {
    "when available in range square, take step along shortest path towards it" in {
      val arena = arenaParser.parseArena(List(
        "#######",
        "#.E...#",
        "#.....#",
        "#...G.#",
        "#######"
      ))

      arena.moveUnit(Coordinate(2, 1)) shouldBe (arena.copy(units = Map(
        Coordinate(3, 1) -> arena.units(Coordinate(2, 1)),
        Coordinate(4, 3) -> arena.units(Coordinate(4, 3)),
      )),
        Coordinate(3,1))
    }

    "when already in range, don't move" in {
      val arena = arenaParser.parseArena(List(
        "#######",
        "#.....#",
        "#..####",
        "#..EG##",
        "#######"
      ))

      arena.moveUnit(Coordinate(3, 3)) shouldBe (arena, Coordinate(3, 3))
    }

    "when no available in range squares, don't move" in {
      val arena = arenaParser.parseArena(List(
        "#######",
        "#.E...#",
        "#...E.#",
        "#..EGE#",
        "#######"
      ))

      arena.moveUnit(Coordinate(2, 1)) shouldBe (arena, Coordinate(2, 1))
    }
  }

  "fight" should {
    "attack adjacent unit with least hit points, kill if result less than zero" in {
      //            HP:            HP:
      //      G....  9       G....  9
      //      ..G..  4       ..G..  4
      //      ..EG.  2  -->  ..E..
      //      ..G..  2       ..G..  2
      //      ...G.  1       ...G.  1
      val arena = arenaParser.parseArena(List(
        "G....",
        "..G..",
        "..EG.",
        "..G..",
        "...G."
      )).copy(units = Map(
        Coordinate(0, 0) -> FightingUnit(UnitType.Goblin, 9),
        Coordinate(2, 1) -> FightingUnit(UnitType.Goblin, 4),
        Coordinate(2, 2) -> FightingUnit(UnitType.Elf),
        Coordinate(3, 2) -> FightingUnit(UnitType.Goblin, 2),
        Coordinate(2, 3) -> FightingUnit(UnitType.Goblin, 2),
        Coordinate(3, 4) -> FightingUnit(UnitType.Goblin, 1),
      ))

      arena.fight(Coordinate(2, 2)) shouldBe arena.copy(units = Map(
        Coordinate(0, 0) -> FightingUnit(UnitType.Goblin, 9),
        Coordinate(2, 1) -> FightingUnit(UnitType.Goblin, 4),
        Coordinate(2, 2) -> FightingUnit(UnitType.Elf),
        Coordinate(2, 3) -> FightingUnit(UnitType.Goblin, 2),
        Coordinate(3, 4) -> FightingUnit(UnitType.Goblin, 1),
      ))
    }

    "attack adjacent unit with least hit points, decrement by 3" in {
      //            HP:            HP:
      //      G....  9       G....  9
      //      ..G..  4       ..G..  4
      //      ..EG.  2  -->  ..E..
      //      ..G..  2       ..G..  2
      //      ...G.  1       ...G.  1
      val arena = arenaParser.parseArena(List(
        "G....",
        "..G..",
        "..EG.",
        "..G..",
        "...G."
      )).copy(units = Map(
        Coordinate(0, 0) -> FightingUnit(UnitType.Goblin, 9),
        Coordinate(2, 1) -> FightingUnit(UnitType.Goblin, 40),
        Coordinate(2, 2) -> FightingUnit(UnitType.Elf),
        Coordinate(3, 2) -> FightingUnit(UnitType.Goblin, 20),
        Coordinate(2, 3) -> FightingUnit(UnitType.Goblin, 20),
        Coordinate(3, 4) -> FightingUnit(UnitType.Goblin, 1),
      ))

      arena.fight(Coordinate(2, 2)) shouldBe arena.copy(units = Map(
        Coordinate(0, 0) -> FightingUnit(UnitType.Goblin, 9),
        Coordinate(2, 1) -> FightingUnit(UnitType.Goblin, 40),
        Coordinate(2, 2) -> FightingUnit(UnitType.Elf),
        Coordinate(3, 2) -> FightingUnit(UnitType.Goblin, 17),
        Coordinate(2, 3) -> FightingUnit(UnitType.Goblin, 20),
        Coordinate(3, 4) -> FightingUnit(UnitType.Goblin, 1),
      ))
    }

    "not do anything if no adjacent opponents" in {
      val arena = arenaParser.parseArena(List(
        "G....",
        "..G..",
        "..EG.",
        "..G..",
        "...G."
      ))
      arena.fight(Coordinate(0,0)) shouldBe arena
    }

    "not attack friends" in {
      val arena = arenaParser.parseArena(List(
        "G....",
        "..G..",
        "..GG.",
        "..G..",
        "...G."
      ))
      arena.fight(Coordinate(2,2)) shouldBe arena
    }
  }
}
