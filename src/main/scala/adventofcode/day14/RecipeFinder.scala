package adventofcode.day14

import scala.annotation.tailrec

class RecipeFinder {

  def findNext10Recipes (after: Int) : String = {
    val recipeState = recipeStateWith(RecipeState(Vector(3, 7), 0, 1), after + 10)
    recipeState.recipes.slice(after, after + 10).mkString
  }

  @tailrec
  private def recipeStateWith(recipeState: RecipeState, numRecipesDesired: Int) : RecipeState = {
   // println(recipeState)
    if (recipeState.recipes.size >= numRecipesDesired) recipeState
    else recipeStateWith(recipeState.buildNextGeneration, numRecipesDesired)
  }

}
