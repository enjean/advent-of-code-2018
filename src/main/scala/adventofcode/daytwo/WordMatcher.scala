package adventofcode.daytwo

class WordMatcher {
  def findCloseMatch(words: Seq[String]) : Seq[String] = {
    val value = words.head
    val restOfList = words.drop(1)
    val matches = restOfList.find(w => oneLetterDiff(value, w))
    if (matches.isDefined) Seq(value, matches.get) else findCloseMatch(restOfList)
  }

  def oneLetterDiff(word1: String, word2: String) : Boolean = {
    word1.zipWithIndex.map {
      case(char, index) => if (char == word2.charAt(index)) 1 else 0
    }
      .sum == word1.length - 1

  }
}
