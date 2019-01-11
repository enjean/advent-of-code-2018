package adventofcode.day14

case class RecipeState(recipes: Vector[Int], elf1Current: Int, elf2Current: Int) {

  def buildNextGeneration: RecipeState = {
    val elf1Recipe = recipes(elf1Current)
    val elf2Recipe = recipes(elf2Current)
    val newRecipes = (elf1Recipe + elf2Recipe).toString.map(_.asDigit)
    val updatedRecipes = recipes ++ newRecipes
    RecipeState (recipes ++ newRecipes,
      nextRecipe(elf1Current, elf1Recipe, updatedRecipes),
      nextRecipe(elf2Current, elf2Recipe, updatedRecipes))
  }

  private def nextRecipe(currentRecipeIndex: Int, currentRecipeValue: Int, updatedRecipes: Vector[Int]) : Int =
    (currentRecipeIndex + 1 + currentRecipeValue) % updatedRecipes.size
}
