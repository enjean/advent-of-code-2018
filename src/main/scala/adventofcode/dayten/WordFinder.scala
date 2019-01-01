package adventofcode.dayten

import scala.annotation.tailrec

class WordFinder {
  def findGridShowingWord(skyGrid: SkyGrid) : (SkyGrid, Int) = findGridShowingWord(skyGrid, 0)

  @tailrec
  private def findGridShowingWord(skyGrid: SkyGrid, second: Int) : (SkyGrid, Int) = {
    println(s"Second $second")
    val positions = skyGrid.points.map(_.position)
    val isShowingWord = positions
      .forall { position =>
        positions.exists(_.isAdjacentTo(position))
      }
    if (isShowingWord) (skyGrid, second) else findGridShowingWord(skyGrid.advance, second + 1)
  }
}
