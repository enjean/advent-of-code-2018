package adventofcode.daythree

class UniqueClaimFinder {
  def uniqueClaim(claims: Seq[Claim]) : Claim = {
      val squares = claims.flatMap(_.getSquares)
        .groupBy(identity)

      claims.find { c=>
        c.getSquares.forall(squares(_).size == 1)
      }.get
  }
}
