package adventofcode.day15

import org.scalatest.{Matchers, WordSpec}

class FightSimulatorTest extends WordSpec with Matchers {
  private val arenaParser = new ArenaParser()
  private val fightSimulator = new FightSimulator()

  "takeTurn" should {
    "move players in turn" in {
      //      Initially:
      //      #########
      //      #G..G..G#
      //      #.......#
      //      #.......#
      //      #G..E..G#
      //      #.......#
      //      #.......#
      //      #G..G..G#
      //      #########
      //

      val initialArena = arenaParser.parseArena(List(
        "#########",
        "#G..G..G#",
        "#.......#",
        "#.......#",
        "#G..E..G#",
        "#.......#",
        "#.......#",
        "#G..G..G#",
        "#########"
      ))

      //      After 1 round:
      //      #########
      //      #.G...G.#
      //      #...G...#
      //      #...E..G#
      //      #.G.....#
      //      #.......#
      //      #G..G..G#
      //      #.......#
      //      #########
      val after1Round = fightSimulator.performRound(initialArena)
      after1Round shouldBe(initialArena.copy(
        units = Map(
          Coordinate(2, 1) -> FightingUnit(UnitType.Goblin),
          Coordinate(4, 2) -> FightingUnit(UnitType.Goblin, 197),
          Coordinate(6, 1) -> FightingUnit(UnitType.Goblin),
          Coordinate(2, 4) -> FightingUnit(UnitType.Goblin),
          Coordinate(4, 3) -> FightingUnit(UnitType.Elf),
          Coordinate(7, 3) -> FightingUnit(UnitType.Goblin),
          Coordinate(1, 6) -> FightingUnit(UnitType.Goblin),
          Coordinate(4, 6) -> FightingUnit(UnitType.Goblin),
          Coordinate(7, 6) -> FightingUnit(UnitType.Goblin)
        )
      ), false)

      //      After 2 rounds:
      //      #########
      //      #..G.G..#
      //      #...G...#
      //      #.G.E.G.#
      //      #.......#
      //      #G..G..G#
      //      #.......#
      //      #.......#
      //      #########
      val after2Rounds = fightSimulator.performRound(after1Round._1)
      after2Rounds shouldBe(after1Round._1.copy(
        units = Map(
          Coordinate(3, 1) -> FightingUnit(UnitType.Goblin),
          Coordinate(4, 2) -> FightingUnit(UnitType.Goblin, 194),
          Coordinate(5, 1) -> FightingUnit(UnitType.Goblin),
          Coordinate(2, 3) -> FightingUnit(UnitType.Goblin),
          Coordinate(4, 3) -> FightingUnit(UnitType.Elf, 197),
          Coordinate(6, 3) -> FightingUnit(UnitType.Goblin),
          Coordinate(1, 5) -> FightingUnit(UnitType.Goblin),
          Coordinate(4, 5) -> FightingUnit(UnitType.Goblin),
          Coordinate(7, 5) -> FightingUnit(UnitType.Goblin),
        )
      ), false)

      //      After 3 rounds:
      //      #########
      //      #.......#
      //      #..GGG..#
      //      #..GEG..#
      //      #G..G...#
      //      #......G#
      //      #.......#
      //      #.......#
      //      #########
      val after3Rounds = fightSimulator.performRound(after2Rounds._1)
      after3Rounds shouldBe(after2Rounds._1.copy(
        units = Map(
          Coordinate(3, 2) -> FightingUnit(UnitType.Goblin),
          Coordinate(4, 2) -> FightingUnit(UnitType.Goblin, 191),
          Coordinate(5, 2) -> FightingUnit(UnitType.Goblin),
          Coordinate(3, 3) -> FightingUnit(UnitType.Goblin),
          Coordinate(4, 3) -> FightingUnit(UnitType.Elf, 197 - 12),
          Coordinate(5, 3) -> FightingUnit(UnitType.Goblin),
          Coordinate(1, 4) -> FightingUnit(UnitType.Goblin),
          Coordinate(4, 4) -> FightingUnit(UnitType.Goblin),
          Coordinate(7, 5) -> FightingUnit(UnitType.Goblin),
        )
      ), false)

      fightSimulator.performRound(after3Rounds._1)._1.units.keys should contain theSameElementsAs after3Rounds._1.units.keys
    }

    "move and fight" in {
      //  Initially:
      //  #######
      //  #.G...#   G(200)
      //  #...EG#   E(200), G(200)
      //  #.#.#G#   G(200)
      //  #..G#E#   G(200), E(200)
      //  #.....#
      //  #######

      val initialArea = arenaParser.parseArena(List(
        "#######",
        "#.G...#",
        "#...EG#",
        "#.#.#G#",
        "#..G#E#",
        "#.....#",
        "#######"
      ))

      //  After 1 round:
      //  #######
      //  #..G..#   G(200)
      //  #...EG#   E(197), G(197)
      //  #.#G#G#   G(200), G(197)
      //  #...#E#   E(197)
      //  #.....#
      //  #######
      val after1Round = fightSimulator.performRound(initialArea)
      after1Round._1.units shouldBe Map(
        Coordinate(3, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(4, 2) -> FightingUnit(UnitType.Elf, 197),
        Coordinate(5, 2) -> FightingUnit(UnitType.Goblin, 197),
        Coordinate(3, 3) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 197),
        Coordinate(5, 4) -> FightingUnit(UnitType.Elf, 197)
      )

      //  After 2 rounds:
      //  #######
      //  #...G.#   G(200)
      //  #..GEG#   G(200), E(188), G(194)
      //  #.#.#G#   G(194)
      //  #...#E#   E(194)
      //  #.....#
      //  #######

      val after2Rounds = fightSimulator.performRound(after1Round._1)
      after2Rounds._1.units shouldBe Map(
        Coordinate(4, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(3, 2) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(4, 2) -> FightingUnit(UnitType.Elf, 188),
        Coordinate(5, 2) -> FightingUnit(UnitType.Goblin, 194),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 194),
        Coordinate(5, 4) -> FightingUnit(UnitType.Elf, 194)
      )

      val after23Rounds = Iterator.iterate(after2Rounds)(prevResult => fightSimulator.performRound(prevResult._1)).drop(21).next()

      //      After 23 rounds:
      //      #######
      //      #...G.#   G(200)
      //      #..G.G#   G(200), G(131)
      //      #.#.#G#   G(131)
      //      #...#E#   E(131)
      //      #.....#
      //      #######
      after23Rounds._1.units shouldBe Map(
        Coordinate(4, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(3, 2) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(5, 2) -> FightingUnit(UnitType.Goblin, 131),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 131),
        Coordinate(5, 4) -> FightingUnit(UnitType.Elf, 131)
      )
      //
      //      After 24 rounds:
      //      #######
      //      #..G..#   G(200)
      //      #...G.#   G(131)
      //      #.#G#G#   G(200), G(128)
      //      #...#E#   E(128)
      //      #.....#
      //      #######
      val after24Rounds = fightSimulator.performRound(after23Rounds._1)
      after24Rounds._1.units shouldBe Map(
        Coordinate(3, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(4, 2) -> FightingUnit(UnitType.Goblin, 131),
        Coordinate(3, 3) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 128),
        Coordinate(5, 4) -> FightingUnit(UnitType.Elf, 128)
      )
      //
      //      After 25 rounds:
      //      #######
      //      #.G...#   G(200)
      //      #..G..#   G(131)
      //      #.#.#G#   G(125)
      //      #..G#E#   G(200), E(125)
      //      #.....#
      //      #######
      val after25Rounds = fightSimulator.performRound(after24Rounds._1)
      after25Rounds._1.units shouldBe Map(
        Coordinate(2, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(3, 2) -> FightingUnit(UnitType.Goblin, 131),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 125),
        Coordinate(3, 4) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(5, 4) -> FightingUnit(UnitType.Elf, 125)
      )
      //
      //      After 26 rounds:
      //      #######
      //      #G....#   G(200)
      //      #.G...#   G(131)
      //      #.#.#G#   G(122)
      //      #...#E#   E(122)
      //      #..G..#   G(200)
      //      #######
      val after26Rounds = fightSimulator.performRound(after25Rounds._1)
      after26Rounds._1.units shouldBe Map(
        Coordinate(1, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(2, 2) -> FightingUnit(UnitType.Goblin, 131),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 122),
        Coordinate(5, 4) -> FightingUnit(UnitType.Elf, 122),
        Coordinate(3, 5) -> FightingUnit(UnitType.Goblin, 200)
      )

      //
      //      After 27 rounds:
      //      #######
      //      #G....#   G(200)
      //      #.G...#   G(131)
      //      #.#.#G#   G(119)
      //      #...#E#   E(119)
      //      #...G.#   G(200)
      //      #######
      val after27Rounds = fightSimulator.performRound(after26Rounds._1)
      after27Rounds._1.units shouldBe Map(
        Coordinate(1, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(2, 2) -> FightingUnit(UnitType.Goblin, 131),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 119),
        Coordinate(5, 4) -> FightingUnit(UnitType.Elf, 119),
        Coordinate(4, 5) -> FightingUnit(UnitType.Goblin, 200)
      )
      //
      //      After 28 rounds:
      //      #######
      //      #G....#   G(200)
      //      #.G...#   G(131)
      //      #.#.#G#   G(116)
      //      #...#E#   E(113)
      //      #....G#   G(200)
      //      #######
      val after28Rounds = fightSimulator.performRound(after27Rounds._1)
      after28Rounds._1.units shouldBe Map(
        Coordinate(1, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(2, 2) -> FightingUnit(UnitType.Goblin, 131),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 116),
        Coordinate(5, 4) -> FightingUnit(UnitType.Elf, 113),
        Coordinate(5, 5) -> FightingUnit(UnitType.Goblin, 200)
      )
      //      After 47 rounds:
      //      #######
      //      #G....#   G(200)
      //      #.G...#   G(131)
      //      #.#.#G#   G(59)
      //      #...#.#
      //      #....G#   G(200)
      //      #######
      val after47Rounds = Iterator.iterate(after28Rounds)(prevResult => fightSimulator.performRound(prevResult._1)).drop(19).next()
      after47Rounds._1.units shouldBe Map(
        Coordinate(1, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(2, 2) -> FightingUnit(UnitType.Goblin, 131),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 59),
        Coordinate(5, 5) -> FightingUnit(UnitType.Goblin, 200)
      )
    }
  }

  "battle" should {
    "finish when no targets remaining" in {
      val initialArena = arenaParser.parseArena(List(
        "#######",
        "#.G...#",
        "#...EG#",
        "#.#.#G#",
        "#..G#E#",
        "#.....#",
        "#######"
      ))

      //      After 47 rounds:
      //      #######
      //      #G....#   G(200)
      //      #.G...#   G(131)
      //      #.#.#G#   G(59)
      //      #...#.#
      //      #....G#   G(200)
      //      #######
      val result = fightSimulator.battle(initialArena)

      result._1.units shouldBe Map(
        Coordinate(1, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(2, 2) -> FightingUnit(UnitType.Goblin, 131),
        Coordinate(5, 3) -> FightingUnit(UnitType.Goblin, 59),
        Coordinate(5, 5) -> FightingUnit(UnitType.Goblin, 200)
      )
      result._1.score shouldBe 590
      result._2 shouldBe 47
    }

    "example 1" in {
      //      #######       #######
      //      #G..#E#       #...#E#   E(200)
      //      #E#E.E#       #E#...#   E(197)
      //      #G.##.#  -->  #.E##.#   E(185)
      //      #...#E#       #E..#E#   E(200), E(200)
      //      #...E.#       #.....#
      //      #######       #######
      //
      //      Combat ends after 37 full rounds
      //      Elves win with 982 total hit points left
      //      Outcome: 37 * 982 = 36334

      val initialArena = arenaParser.parseArena(List(
        "#######",
        "#G..#E#",
        "#E#E.E#",
        "#G.##.#",
        "#...#E#",
        "#...E.#",
        "#######"
      ))

      val result = fightSimulator.battle(initialArena)
      result._1.units shouldBe Map(
        Coordinate(5, 1) -> FightingUnit(UnitType.Elf, 200),
        Coordinate(1, 2) -> FightingUnit(UnitType.Elf, 197),
        Coordinate(2, 3) -> FightingUnit(UnitType.Elf, 185),
        Coordinate(1, 4) -> FightingUnit(UnitType.Elf, 200),
        Coordinate(5, 4) -> FightingUnit(UnitType.Elf, 200),
      )
      result._1.score shouldBe 982
      result._2 shouldBe 37
    }

    "example 2" in {
      //      #######       #######
      //      #E..EG#       #.E.E.#   E(164), E(197)
      //      #.#G.E#       #.#E..#   E(200)
      //      #E.##E#  -->  #E.##.#   E(98)
      //      #G..#.#       #.E.#.#   E(200)
      //      #..E#.#       #...#.#
      //      #######       #######
      //
      //      Combat ends after 46 full rounds
      //      Elves win with 859 total hit points left
      //      Outcome: 46 * 859 = 39514

      val initialArena = arenaParser.parseArena(List(
        "#######",
        "#E..EG#",
        "#.#G.E#",
        "#E.##E#",
        "#G..#.#",
        "#..E#.#",
        "#######"
      ))

      val result = fightSimulator.battle(initialArena)
      result._1.units shouldBe Map(
        Coordinate(2, 1) -> FightingUnit(UnitType.Elf, 164),
        Coordinate(4, 1) -> FightingUnit(UnitType.Elf, 197),
        Coordinate(3, 2) -> FightingUnit(UnitType.Elf, 200),
        Coordinate(1, 3) -> FightingUnit(UnitType.Elf, 98),
        Coordinate(2, 4) -> FightingUnit(UnitType.Elf, 200),
      )
      result._1.score shouldBe 859
      result._2 shouldBe 46
    }

    "example 3" in {
      //      #######       #######
      //      #E.G#.#       #G.G#.#   G(200), G(98)
      //      #.#G..#       #.#G..#   G(200)
      //      #G.#.G#  -->  #..#..#
      //      #G..#.#       #...#G#   G(95)
      //      #...E.#       #...G.#   G(200)
      //      #######       #######
      //
      //      Combat ends after 35 full rounds
      //      Goblins win with 793 total hit points left
      //      Outcome: 35 * 793 = 27755

      val initialArena = arenaParser.parseArena(List(
        "#######",
        "#E.G#.#",
        "#.#G..#",
        "#G.#.G#",
        "#G..#.#",
        "#...E.#",
        "#######"
      ))

      val result = fightSimulator.battle(initialArena)
      result._1.units shouldBe Map(
        Coordinate(1, 1) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(3, 1) -> FightingUnit(UnitType.Goblin, 98),
        Coordinate(3, 2) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(5, 4) -> FightingUnit(UnitType.Goblin, 95),
        Coordinate(4, 5) -> FightingUnit(UnitType.Goblin, 200)
      )
      result._1.score shouldBe 793
      result._2 shouldBe 35
    }

    "example 4" in {
      //      #######       #######
      //      #.E...#       #.....#
      //      #.#..G#       #.#G..#   G(200)
      //      #.###.#  -->  #.###.#
      //      #E#G#G#       #.#.#.#
      //      #...#G#       #G.G#G#   G(98), G(38), G(200)
      //      #######       #######
      //
      //      Combat ends after 54 full rounds
      //      Goblins win with 536 total hit points left
      //      Outcome: 54 * 536 = 28944

      val initialArena = arenaParser.parseArena(List(
        "#######",
        "#.E...#",
        "#.#..G#",
        "#.###.#",
        "#E#G#G#",
        "#...#G#",
        "#######"
      ))

      val result = fightSimulator.battle(initialArena)
      result._1.units shouldBe Map(
        Coordinate(3, 2) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(1, 5) -> FightingUnit(UnitType.Goblin, 98),
        Coordinate(3, 5) -> FightingUnit(UnitType.Goblin, 38),
        Coordinate(5, 5) -> FightingUnit(UnitType.Goblin, 200)
      )
      result._1.score shouldBe 536
      result._2 shouldBe 54
    }

    "example 5" in {
      //      #########       #########
      //      #G......#       #.G.....#   G(137)
      //      #.E.#...#       #G.G#...#   G(200), G(200)
      //      #..##..G#       #.G##...#   G(200)
      //      #...##..#  -->  #...##..#
      //      #...#...#       #.G.#...#   G(200)
      //      #.G...G.#       #.......#
      //      #.....G.#       #.......#
      //      #########       #########
      //
      //      Combat ends after 20 full rounds
      //      Goblins win with 937 total hit points left
      //      Outcome: 20 * 937 = 18740

      val initialArena = arenaParser.parseArena(List(
        "#########",
        "#G......#",
        "#.E.#...#",
        "#..##..G#",
        "#...##..#",
        "#...#...#",
        "#.G...G.#",
        "#.....G.#",
        "#########"
      ))

      val result = fightSimulator.battle(initialArena)
      result._1.units shouldBe Map(
        Coordinate(2, 1) -> FightingUnit(UnitType.Goblin, 137),
        Coordinate(1, 2) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(3, 2) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(2, 3) -> FightingUnit(UnitType.Goblin, 200),
        Coordinate(2, 5) -> FightingUnit(UnitType.Goblin, 200)
      )
      result._1.score shouldBe 937
      result._2 shouldBe 20
    }
  }
}
