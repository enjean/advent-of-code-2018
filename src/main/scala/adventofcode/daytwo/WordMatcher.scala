package adventofcode.daytwo

class WordMatcher {
  def findCloseMatch(words: Seq[String]) : Seq[String] = {
    val value = words.head
    val restOfList = words.drop(1)
    val matches = restOfList.find(w => oneLetterDiff(value, w))
    if (matches.isDefined) Seq(value, matches.get) else findCloseMatch(restOfList)
  }

  def oneLetterDiff(word1: String, word2: String) : Boolean = {
    val matchingCount = word1.zipWithIndex.count {
      case (char, index) => char == word2.charAt(index)
    }
    matchingCount == word1.length - 1
  }
}
