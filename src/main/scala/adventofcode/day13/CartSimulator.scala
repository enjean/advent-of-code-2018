package adventofcode.day13

import scala.annotation.tailrec

class CartSimulator {

  @tailrec
  final def findCollision(carts: Seq[(Coordinate, Cart)], tracks: Map[Coordinate, Char]) : Coordinate = {
    def tickResult = tick(carts, tracks)
    if (tickResult._2.nonEmpty) tickResult._2.head
    else findCollision(tickResult._1, tracks)
  }

  def tick(carts: Seq[(Coordinate, Cart)], tracks: Map[Coordinate, Char]): (Seq[(Coordinate, Cart)], List[Coordinate]) = {
    val cartsInOrder = carts.sortBy(c => (c._1.y, c._1.x))
    cartsInOrder.foldLeft(Seq[(Coordinate, Cart)](), List[Coordinate]()) {
      (acc, cart) =>
        val movedCart = moveCart(cart._2, cart._1, tracks)
        val collisions = if (acc._1.map(_._1).contains(movedCart._1)) acc._2 :+ movedCart._1 else acc._2
        (acc._1 :+ movedCart, collisions)
    }
  }

  def moveCart(cart: Cart, currentPosition: Coordinate, tracks: Map[Coordinate, Char]) : (Coordinate, Cart) = {
    val nextPosition = getNextPosition(cart, currentPosition)
    val nextTrack = tracks(nextPosition)
    nextPosition -> cart.moveTo(nextTrack)
  }

  private def getNextPosition(cart: Cart, coordinate: Coordinate): Coordinate = {
    cart.heading match {
      case Direction.North => Coordinate(coordinate.x, coordinate.y - 1)
      case Direction.South => Coordinate(coordinate.x, coordinate.y + 1)
      case Direction.East => Coordinate(coordinate.x + 1, coordinate.y)
      case Direction.West => Coordinate(coordinate.x - 1, coordinate.y)
    }
  }
}
