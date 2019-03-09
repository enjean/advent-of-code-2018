package adventofcode.day18

class WoodsSimulator {
  def runSimulation(areaState: AreaState, minutes: Int) : AreaState = {
    Iterator.iterate(areaState)(_.evolve).drop(minutes).next()
  }
}
