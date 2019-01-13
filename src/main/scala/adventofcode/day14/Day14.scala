package adventofcode.day14

object Day14 extends App {
  private val recipeFinder = new RecipeFinder()
  val part1 = recipeFinder.findNext10Recipes(890691)
  println(s"Part One: $part1")
  println(s"Part Two: ${recipeFinder.findLeftOfPattern("890691")}")
}
