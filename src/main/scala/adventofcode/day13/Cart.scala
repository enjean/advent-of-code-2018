package adventofcode.day13

import adventofcode.day13.Direction.Direction
import adventofcode.day13.Turn.{Left, Right, Straight, Turn}

case class Cart(heading: Direction, // N, E, W, S)
               nextTurn: Turn = Turn.Left
               ) {

  def moveTo(track: Char) : Cart = {
    track match {
      case '-' | '|' => this
      case '/' => moveToSlash
      case '\\' => moveToBackslash
      case '+' => moveToIntersection
    }
  }

  private def moveToSlash : Cart = {
    heading match {
      case Direction.North => copy(heading = Direction.East)
      case Direction.West => copy(heading = Direction.South)
      case Direction.South => copy(heading = Direction.West)
      case Direction.East => copy(heading = Direction.North)
    }
  }

  private def moveToBackslash : Cart = {
    heading match {
      case Direction.North => copy(heading = Direction.West)
      case Direction.West => copy(heading = Direction.North)
      case Direction.South => copy(heading = Direction.East)
      case Direction.East => copy(heading = Direction.South)
    }
  }

  private def moveToIntersection : Cart = {
    nextTurn match {
      case Turn.Straight => copy(nextTurn = getNextTurn(nextTurn))
      case Turn.Left =>
        heading match {
          case Direction.North => Cart(Direction.West, getNextTurn(nextTurn))
          case Direction.East => Cart(Direction.North, getNextTurn(nextTurn))
          case Direction.South => Cart(Direction.East, getNextTurn(nextTurn))
          case Direction.West => Cart(Direction.South, getNextTurn(nextTurn))
        }
      case Turn.Right =>
        heading match {
          case Direction.North => Cart(Direction.East, getNextTurn(nextTurn))
          case Direction.East => Cart(Direction.South, getNextTurn(nextTurn))
          case Direction.South => Cart(Direction.West, getNextTurn(nextTurn))
          case Direction.West => Cart(Direction.North, getNextTurn(nextTurn))
        }
    }
  }

  def getNextTurn(turn: Turn) : Turn = {
    turn match {
      case Left => Straight
      case Straight => Right
      case Right => Left
    }
  }
}