package adventofcode.day15

import scala.annotation.tailrec

class FightSimulator {

  def battle(arena: Arena) : (Arena, Int) = {
    battleRound(arena, 1)
  }

  @tailrec
  private def battleRound(arena: Arena, round: Int) : (Arena, Int) = {
    println(s"Battle Round $round")
    val roundResult = performRound(arena)
    if (roundResult._2) {
      (roundResult._1, round - 1)
    }
    else {
      battleRound(roundResult._1, round + 1)
    }
  }

  def performRound(arena: Arena) : (Arena, Boolean) = {
    val unitsToMove = arena.units.toList
        .sortBy(cu => (cu._1.y, cu._1.x))
    performMoveOfRound(arena, unitsToMove.map(_._1))
  }

  @tailrec
  private def performMoveOfRound(arena: Arena, unitsLeftToMove: List[Coordinate]): (Arena, Boolean) = {
    if (unitsLeftToMove.isEmpty) {
      (arena, false)
    } else {
      val unitLocToMove = unitsLeftToMove.head
      //println(s"$arena")
      if (!arena.units.contains(unitLocToMove)) {
        // must have died earlier in turn
        performMoveOfRound(arena, unitsLeftToMove.tail)
      }
      else if (arena.hasUnitOfType(arena.units(unitLocToMove).opponentType)) {
        val moveResult = arena.moveUnit(unitLocToMove)
        val arenaAfterFight = moveResult._1.fight(moveResult._2)
        performMoveOfRound(arenaAfterFight, unitsLeftToMove.tail)
      }
      else {
        (arena, true)
      }
    }
  }
}
