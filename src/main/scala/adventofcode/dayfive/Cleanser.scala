package adventofcode.dayfive

class Cleanser {
  def cleanse(polymer: String, unit: Char) : String = {
    polymer.replaceAll(s"[${unit.toLower}, ${unit.toUpper}]", "")
  }
}
