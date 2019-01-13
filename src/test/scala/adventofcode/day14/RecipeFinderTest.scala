package adventofcode.day14

import org.scalatest.{FlatSpec, Matchers}

class RecipeFinderTest extends FlatSpec with Matchers {

  private val recipeFinder = new RecipeFinder()
 "findNext10Recipes" should "return values of next ten recipes after input" in {
//   If the Elves think their skill will improve after making 9 recipes, the scores of the ten recipes after the first nine on the scoreboard would be 5158916779 (highlighted in the last line of the diagram).
//     After 5 recipes, the scores of the next ten would be 0124515891.
//   After 18 recipes, the scores of the next ten would be 9251071085.
//   After 2018 recipes, the scores of the next ten would be 5941429882.
   recipeFinder.findNext10Recipes(9) shouldBe "5158916779"
   recipeFinder.findNext10Recipes(5) shouldBe "0124515891"
   recipeFinder.findNext10Recipes(18) shouldBe "9251071085"
   recipeFinder.findNext10Recipes(2018) shouldBe "5941429882"
 }

  "findLeftOfPattern" should "return number of recipes observed before pattern found" in {
    recipeFinder.findLeftOfPattern("51589") shouldBe 9
    recipeFinder.findLeftOfPattern("01245") shouldBe 5
    recipeFinder.findLeftOfPattern("92510") shouldBe 18
    recipeFinder.findLeftOfPattern("59414") shouldBe 2018
  }
}
