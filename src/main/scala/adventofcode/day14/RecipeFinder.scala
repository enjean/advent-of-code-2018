package adventofcode.day14

import scala.annotation.tailrec

class RecipeFinder {

  def findNext10Recipes(after: Int) : String = {
    val recipeState = recipeStateWith(RecipeState(Vector(3, 7), 0, 1), after + 10)
    recipeState.recipes.slice(after, after + 10).mkString
  }

  @tailrec
  private def recipeStateWith(recipeState: RecipeState, numRecipesDesired: Int) : RecipeState = {
   // println(recipeState)
    if (recipeState.recipes.size >= numRecipesDesired) recipeState
    else recipeStateWith(recipeState.buildNextGeneration, numRecipesDesired)
  }

  def findLeftOfPattern(pattern: String):Int = {
    val recipeState = findLeftOfPattern(pattern, RecipeState(Vector(3, 7), 0, 1))
    recipeState.recipes.size - pattern.length
  }

  @tailrec
  private def findLeftOfPattern(pattern: String, recipeState: RecipeState):RecipeState = {
    if (recipeState.recipes.takeRight(pattern.length+1).mkString.contains(pattern)) recipeState
    else findLeftOfPattern(pattern, recipeState.buildNextGeneration)
  }
}
