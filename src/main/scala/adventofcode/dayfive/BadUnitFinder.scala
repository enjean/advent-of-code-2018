package adventofcode.dayfive

class BadUnitFinder {
  private val cleanser = new Cleanser()
  private val polymerReactor = new PolymerReactor()

  def findWorstUnit(polymer: String) : (Char, Int) = {
    val cleansedPerUnit = polymer.toLowerCase.distinct
      .map { unit =>
        val result = polymerReactor.react(cleanser.cleanse(polymer, unit))
        (unit, result.length)
      }

    cleansedPerUnit.minBy(_._2)
  }
}
