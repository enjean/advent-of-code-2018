package adventofcode.dayten

class WordFinder {
  def findGridShowingWord(skyGrid: SkyGrid) : SkyGrid = {

    var found = false
    var currentGrid = skyGrid
    var second = 0
    while (!found) {
      second = second + 1
      println(s"At second $second")
      currentGrid = currentGrid.advance
      val positions = currentGrid.points.map(_.position)
      found = positions
        .forall { position =>
//          println(s"$position is adjacent to ${positions.find(_.isAdjacentTo(position))}")
          positions.exists(_.isAdjacentTo(position))
        }

    }
    currentGrid
  }
}
