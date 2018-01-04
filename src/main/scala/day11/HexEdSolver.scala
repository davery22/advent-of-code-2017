package day11

import scala.annotation.tailrec

object HexEdSolver {
  def getMinSteps(path: List[String]): (Int, Int) = {
    def sameSign(a: Int, b: Int) = (a != 0) && (b != 0) && ((a > 0) == (b > 0))

    @tailrec
    def reduceSteps(north: Int, northE: Int, northW: Int): Int = {
      if (sameSign(northE, northW)) {
        val joined = if (northE > 0) math.min(northE, northW) else math.max(northE, northW)
        reduceSteps(north + joined, northE - joined, northW - joined)
      } else if (sameSign(north, -northW)) {
        val joined = if (north > 0) math.min(north, -northW) else math.max(north, -northW)
        reduceSteps(north - joined, northE + joined, northW + joined)
      } else if (sameSign(north, -northE)) {
        val joined = if (north > 0) math.min(north, -northE) else math.max(north, -northE)
        reduceSteps(north - joined, northE + joined, northW + joined)
      } else {
        math.abs(north) + math.abs(northE) + math.abs(northW)
      }
    }

    @tailrec
    def tracePath(path: List[String], north: Int, northE: Int, northW: Int, maxStepsAway: Int): (Int, Int) = {
      val stepsAway = reduceSteps(north, northE, northW)
      val newMaxStepsAway = math.max(stepsAway, maxStepsAway)
      path match {
        case dir :: _ => dir match {
          case "n" => tracePath(path.tail, north + 1, northE, northW, newMaxStepsAway)
          case "ne" => tracePath(path.tail, north, northE + 1, northW, newMaxStepsAway)
          case "nw" => tracePath(path.tail, north, northE, northW + 1, newMaxStepsAway)
          case "s" => tracePath(path.tail, north - 1, northE, northW, newMaxStepsAway)
          case "se" => tracePath(path.tail, north, northE, northW - 1, newMaxStepsAway)
          case "sw" => tracePath(path.tail, north, northE - 1, northW, newMaxStepsAway)
        }
        case Nil => (stepsAway, newMaxStepsAway)
      }
    }
    tracePath(path, 0, 0, 0, 0)
  }
}
