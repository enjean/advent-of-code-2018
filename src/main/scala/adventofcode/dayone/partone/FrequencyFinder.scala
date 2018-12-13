package adventofcode.dayone.partone

import scala.io.Source

class FrequencyFinder {

  def frequency(filename: String) : Int = {
    Source.fromFile(filename).getLines()
      .map(str => str.toInt)
      .sum
  }
}
