package adventofcode.day11

case class FuelCell(x: Int, y: Int) {

  def powerLevel(serialNumber: Int) : Int = {
    val rackId = x + 10
    val v0 = rackId * y
    val v1 = v0 + serialNumber
    val powerLevel = v1 * rackId

    val digit = (powerLevel / 100) % 10
    digit - 5
  }
}
