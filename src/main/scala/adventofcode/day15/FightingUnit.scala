package adventofcode.day15

import adventofcode.day15.UnitType.UnitType

case class FightingUnit(unitType: UnitType, hitPoints: Int = 200) {

  def opponentType : UnitType = {
    unitType match {
      case UnitType.Elf => UnitType.Goblin
      case UnitType.Goblin => UnitType.Elf
    }
  }

  def takeDamage(attackPower: Int) : FightingUnit = this.copy(hitPoints = hitPoints - attackPower)

  def toChar: Char = unitType match {
    case UnitType.Elf => 'E'
    case UnitType.Goblin => 'G'
  }
}
