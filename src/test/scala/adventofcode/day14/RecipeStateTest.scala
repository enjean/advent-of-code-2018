package adventofcode.day14

import org.scalatest.{Matchers, WordSpec}

class RecipeStateTest extends WordSpec with Matchers {
  "buildNextGeneration" should {

//    (3)[7]
//    (3)[7] 1  0
//    3  7  1 [0](1) 0
//    3  7  1  0 [1] 0 (1)
//    (3) 7  1  0  1  0 [1] 2
//    3  7  1  0 (1) 0  1  2 [4]
//    3  7  1 [0] 1  0 (1) 2  4  5
//    3  7  1  0 [1] 0  1  2 (4) 5  1
//    3 (7) 1  0  1  0 [1] 2  4  5  1  5
//    3  7  1  0  1  0  1  2 [4](5) 1  5  8
//    3 (7) 1  0  1  0  1  2  4  5  1  5  8 [9]
//    3  7  1  0  1  0  1 [2] 4 (5) 1  5  8  9  1  6
//    3  7  1  0  1  0  1  2  4  5 [1] 5  8  9  1 (6) 7
//    3  7  1  0 (1) 0  1  2  4  5  1  5 [8] 9  1  6  7  7
//    3  7 [1] 0  1  0 (1) 2  4  5  1  5  8  9  1  6  7  7  9
//    3  7  1  0 [1] 0  1  2 (4) 5  1  5  8  9  1  6  7  7  9  2

    "handle each generation" in {
      val gen1 = RecipeState(Vector(3, 7), 0, 1).buildNextGeneration
      gen1 shouldBe RecipeState(Vector(3, 7, 1, 0), 0, 1)

      val gen2 = gen1.buildNextGeneration
      gen2 shouldBe RecipeState(Vector(3, 7, 1, 0, 1, 0), 4, 3)

      val gen3 = gen2.buildNextGeneration
      gen3 shouldBe RecipeState(Vector(3, 7, 1, 0, 1, 0, 1), 6, 4)

      val gen4 = gen3.buildNextGeneration
      gen4 shouldBe RecipeState(Vector(3, 7, 1, 0, 1, 0, 1, 2), 0, 6)
    }
  }
}
