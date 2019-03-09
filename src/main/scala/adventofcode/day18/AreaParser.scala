package adventofcode.day18

import adventofcode.day18.AcreType.AcreType

class AreaParser {
  def parseArea(input: List[String]) : AreaState = {
    AreaState(input.foldLeft(Vector[Vector[AcreType]]()) {
      (rowsSoFar, line) =>
        val row = line.foldLeft(Vector[AcreType]()) {
          (rowSoFar, character) =>
            val acreType =  character match {
              case '.' => AcreType.OpenGround
              case '|' => AcreType.Trees
              case '#' => AcreType.Lumberyard
            }
            rowSoFar :+ acreType
        }
        rowsSoFar :+ row
    })
  }
}
