package adventofcode.daytwo

class Analyzer {
  def analyze(word: String) : Analysis = {
      val x = word.groupBy(identity)
      Analysis(x.exists(_._2.length == 2), x.exists(_._2.length == 3))
  }
}

case class Analysis(hasTwo: Boolean, hasThree: Boolean)