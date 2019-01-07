package adventofcode.day12

object Day12 extends App {
  val initialState = "######....##.###.#..#####...#.#.....#..#.#.##......###.#..##..#..##..#.##..#####.#.......#.....##.."
  val growthCombinations = Set(
    "...##",
    "##.#.",
    "##...",
    "#..#.",
    ".##.#",
    "..###",
    "..#.#",
    ".####",
    ".#..#",
    "####.",
    "#.###",
    ".#..."
  )

  println(s"Part One: ${new GrowthSimulator().runSimulation(Pots(initialState), growthCombinations, 20)}")
  println(s"Part Two: ${new GrowthSimulator().runSimulation(Pots(initialState), growthCombinations, 50000000000L)}")
}
