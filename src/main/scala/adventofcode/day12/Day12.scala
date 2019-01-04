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

  println(s"Part One: ${new GrowthSimulator().runSimulation(initialState, growthCombinations)}")
}
