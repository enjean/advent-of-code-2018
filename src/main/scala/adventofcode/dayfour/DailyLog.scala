package adventofcode.dayfour

case class DailyLog(guard: Int, sleepPeriods: Seq[(Int, Int)]) {
  def minutesAsleep: Int = {
    sleepPeriods.map(sp => sp._2 - sp._1).sum
  }

  def sleepMinutesList: Seq[Int] = {
    sleepPeriods.flatMap(sp => sp._1 until sp._2)
  }
}
