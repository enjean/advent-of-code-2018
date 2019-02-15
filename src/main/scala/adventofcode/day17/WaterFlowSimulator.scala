package adventofcode.day17

import scala.annotation.tailrec

class WaterFlowSimulator(scanModel: ScanModel) {

  def flowWaterFrom(coordinate: Coordinate): UndergroundState = {
    moveWaterBFS(List(coordinate), UndergroundState(Set(), Set()), Map())
  }

  private def wallOrDrop(startX: Int, y: Int, xMod: Int, undergroundState: UndergroundState): Either[Int, Int] = {
    val foundX = Iterator.iterate(startX + xMod)(_ + xMod)
      .dropWhile(x => {
        val coord = Coordinate(x, y)
        !scanModel.claySpots.contains(coord) &&
          (scanModel.claySpots.contains(coord.down) || undergroundState.waterAtRest.contains(coord.down))
      }).next()
    if (scanModel.claySpots.contains(Coordinate(foundX, y))) Left(foundX)
    else Right(foundX)
  }

  private def headDown(coordinate: Coordinate, undergroundState: UndergroundState): (UndergroundState, Boolean) = {
    println(s"headDown($coordinate)")
    if (coordinate.y == 1900) {
      scanModel.prettyPrint(undergroundState)
    }
    val newState = undergroundState.addMovedThrough(coordinate)

    if (coordinate.y == scanModel.maxY) {
      //println("Terminating at bottom")
      (newState, false)
    }
    else {
      val downResult = if (scanModel.isClay(coordinate.down) || undergroundState.waterAtRest.contains(coordinate.down)) {
        (newState, true)
      } else {
        headDown(coordinate.down, newState)
      }

      if (downResult._2) {
        val leftResult = if (scanModel.isClay(coordinate.left)) {
          (downResult._1, Some(coordinate.x))
        } else {
          headLeft(coordinate.left, downResult._1)
        }

        val rightResult = if (scanModel.isClay(coordinate.right)) {
          (leftResult._1, Some(coordinate.x))
        } else {
          headRight(coordinate.right, leftResult._1)
        }

        if (leftResult._2.isDefined && rightResult._2.isDefined) {
          val stateWithStandingRow = (leftResult._2.get to rightResult._2.get).foldLeft(rightResult._1) {
            (state, x) => state.setStanding(Coordinate(x, coordinate.y))
          }
          (stateWithStandingRow, true)
        }
        else {
          (rightResult._1, false)
        }
      } else {
        downResult
      }
    }
  }

  @tailrec
  private def headLeft(coordinate: Coordinate, undergroundState: UndergroundState): (UndergroundState, Option[Int]) = {
    //println(s"headLeft($coordinate)")
    val newState = undergroundState.addMovedThrough(coordinate)
    val downResult = if (scanModel.isClay(coordinate.down) || undergroundState.waterAtRest.contains(coordinate.down)) {
      (newState, true)
    } else {
      headDown(coordinate.down, newState)
    }

    if (downResult._2) {
      if (scanModel.isClay(coordinate.left)) {
        (downResult._1, Some(coordinate.x))
      } else {
        headLeft(coordinate.left, downResult._1)
      }
    } else {
      (downResult._1, None)
    }
  }

  @tailrec
  private def headRight(coordinate: Coordinate, undergroundState: UndergroundState): (UndergroundState, Option[Int]) = {
    //println(s"headRight($coordinate)")
    val newState = undergroundState.addMovedThrough(coordinate)
    val downResult = if (scanModel.isClay(coordinate.down) || undergroundState.waterAtRest.contains(coordinate.down)) {
      (newState, true)
    } else {
      headDown(coordinate.down, newState)
    }

    if (downResult._2) {
      if (scanModel.isClay(coordinate.right)) {
        (downResult._1, Some(coordinate.x))
      } else {
        headRight(coordinate.right, downResult._1)
      }
    } else {
      (downResult._1, None)
    }
  }

  case class CoordinateAndParent(coordinate: Coordinate, parent: Coordinate)

  @tailrec
  private def moveWaterBFS(coordinatesToConsider: List[Coordinate], undergroundState: UndergroundState, parents: Map[Coordinate, Coordinate]): UndergroundState = {
    println(s"$coordinatesToConsider")
    if (coordinatesToConsider.isEmpty) {
      undergroundState
    }
    else {
      val coordinate = coordinatesToConsider.head

      if (coordinate.y > scanModel.maxY) {
        moveWaterBFS(coordinatesToConsider.tail, undergroundState, parents)
      }

      else {
        val newState = undergroundState.addMovedThrough(coordinate)

//        if (coordinate.y > 600) {
//          scanModel.prettyPrint(newState)
//        }

        if (scanModel.isClay(coordinate.down) || undergroundState.waterAtRest.contains(coordinate.down)) {
          val leftWallOrDrop = wallOrDrop(coordinate.x, coordinate.y, -1, newState)
          val rightWallOrDrop = wallOrDrop(coordinate.x, coordinate.y, 1, newState)
          (leftWallOrDrop, rightWallOrDrop) match {
            case (Left(leftWallAt), Left(rightWallAt)) =>
              val filledState = (leftWallAt + 1 until rightWallAt).foldLeft(newState) {
                (state, x) => state.setStanding(Coordinate(x, coordinate.y))
              }
              val upParent = Iterator.iterate(coordinate)(c => parents(c)).dropWhile(c => c.y == coordinate.y).next()
              println(s"Heading up to $upParent")
              val nextCoordinatesToConsider = if (coordinatesToConsider.tail.contains(upParent)) {
                coordinatesToConsider.tail
              } else {
                coordinatesToConsider.tail :+ upParent
              }
              moveWaterBFS(nextCoordinatesToConsider, filledState, parents)
            case _ =>
              val validLeftRight = Seq(coordinate.left, coordinate.right)
                .filterNot(scanModel.isClay)
                .filterNot(newState.seen)
              println(s"Heading sideways to $validLeftRight")
              moveWaterBFS(coordinatesToConsider.tail ++ validLeftRight, newState, parents ++ validLeftRight.map(c => c -> coordinate).toMap)
          }

        } else {
          val nextCoordinatesToConsider = if (coordinatesToConsider.tail.contains(coordinate.down)) {
            coordinatesToConsider.tail
          } else {
            coordinatesToConsider.tail :+ coordinate.down
          }
          moveWaterBFS(nextCoordinatesToConsider, newState, parents + (coordinate.down -> coordinate))
        }
      }
    }
  }


}
