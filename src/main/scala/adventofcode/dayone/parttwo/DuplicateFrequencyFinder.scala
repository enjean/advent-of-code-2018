package adventofcode.dayone.parttwo

import scala.io.Source

class DuplicateFrequencyFinder {
  def findFirstDupe(filename: String) : Int = {
    val changes = Source.fromFile(filename).getLines()
      .map(_.toInt)
      .toList

    findFirstDupe(changes, changes, Set(0), 0)
  }

  private def findFirstDupe(fullList: List[Int], currentList: List[Int], seen: Set[Int], currentFrequency: Int) : Int = {
    if (currentList.isEmpty) {
      findFirstDupe(fullList, fullList, seen, currentFrequency)
    } else {
      val newFrequency = currentFrequency + currentList.head
      if (seen.contains(newFrequency)) {
        newFrequency
      } else {
        findFirstDupe(fullList, currentList.drop(1), seen + newFrequency, newFrequency)
      }
    }
  }

}

case class Holder(currentFreq: Int, allFreqs: Seq[Int])
