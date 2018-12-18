package adventofcode.dayfive

class PolymerReactor {
  def react(input: String) : String = {
    val reactedString = reactionPass(input)
    if (reactedString == input) reactedString else react(reactedString)
  }

  def reactionPass(input: String) : String = {
    val reactCombos = input.sliding(2)
      .filter { combo =>
        combo.head.toUpper == combo.last.toUpper && combo.head.isUpper != combo.last.isUpper
      }
        .toList
    reactCombos.foldLeft(input) {
      (result, comboToRemove) => result.replaceAll(comboToRemove, "")
    }
  }
}
