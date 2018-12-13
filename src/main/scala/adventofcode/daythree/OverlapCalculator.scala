package adventofcode.daythree

class OverlapCalculator {
 def overlapSquares(claims: Seq[Claim]) : Seq[Square] = {
   claims.flatMap(_.getSquares)
     .groupBy(identity)
     .collect { case (x, List(_,_,_*)) => x }
     .toSeq
 }
}
