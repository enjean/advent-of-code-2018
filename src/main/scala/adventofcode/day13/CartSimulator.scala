package adventofcode.day13

import scala.annotation.tailrec

class CartSimulator {

  @tailrec
  final def findCollision(carts: Seq[(Coordinate, Cart)], tracks: Map[Coordinate, Char]): Coordinate = {
    def tickResult = tick(carts, tracks)

    if (tickResult._2.nonEmpty) tickResult._2.head
    else findCollision(tickResult._1, tracks)
  }

//  @tailrec
  final def findFinalRemainingCart(carts: Seq[(Coordinate, Cart)], tracks: Map[Coordinate, Char]): Coordinate = {
   // prettyPrint(carts, tracks)
    if (carts.size == 1) {
//      prettyPrint(carts, tracks)
      carts.head._1
    }
    else {
      val tickResult = tick(carts, tracks)
      //      println(s"$tickResult")
      findFinalRemainingCart(tickResult._1, tracks)
    }
  }

  private def prettyPrint(carts: Seq[(Coordinate, Cart)], tracks: Map[Coordinate, Char]) : Unit = {

    val lines = (0 to tracks.map(_._1.y).max).foldLeft(Seq[String]()) {
      (linesSoFar, y) =>
        linesSoFar :+ (0 to tracks.map(_._1.x).max).foldLeft(Seq[Char]()) {
          (lineSoFar, x) =>
            val coord = Coordinate(x, y)
            val charForCoord = carts.find(_._1 == coord).map(_._2.toChar).getOrElse(tracks.getOrElse(Coordinate(x, y), ' '))
            lineSoFar :+ charForCoord
        }.mkString
    }
    println(lines.mkString("\n"))
  }

  def tick(carts: Seq[(Coordinate, Cart)], tracks: Map[Coordinate, Char]): (Seq[(Coordinate, Cart)], List[Coordinate]) = {
    //println(s"tick($carts)")
    val cartsInOrder = carts.sortBy(c => (c._1.y, c._1.x))
    cartsInOrder.foldLeft(carts, List[Coordinate]()) {
      (acc, cart) =>
        if (acc._1.contains(cart)) {
          val otherCarts = acc._1.diff(Seq(cart))

          val movedCart = moveCart(cart._2, cart._1, tracks)
          val newPosition = movedCart._1
          val cartsRunInto = otherCarts.filter(_._1 == newPosition)
          if (cartsRunInto.nonEmpty) {
            (otherCarts.diff(cartsRunInto), acc._2 :+ newPosition)
          }
          else {
            (otherCarts :+ movedCart, acc._2)
          }
        }
        else {
          // cart has been removed due to collision
          acc
        }
    }
  }

  def moveCart(cart: Cart, currentPosition: Coordinate, tracks: Map[Coordinate, Char]): (Coordinate, Cart) = {
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
