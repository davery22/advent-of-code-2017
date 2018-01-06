package day17

import scala.annotation.tailrec

object SpinlockSolver {
  def getPostFinalValue(stepSize: Int, finalValue: Int, afterValue: Int): Int = {
    if (afterValue == 0) {
      getPostFinalValueAfterZero(stepSize, finalValue)
    } else {
      val endCond = finalValue+1
      @tailrec
      def insert(circle: List[Int], pos: Int, value: Int): Int = value match {
        case `endCond` => circle((circle.indexOf(afterValue) + 1) % value) // NOTE: value == circle.length
        case _ => {
          val newPos = ((pos + stepSize) % value) + 1
          insert(circle.patch(newPos, List(value), 0), newPos, value+1)
        }
      }
      insert(List(0), 0, 1)
    }
  }

  private
  def getPostFinalValueAfterZero(stepSize: Int, finalValue: Int): Int = {
    // Zero stays in first position, so we can optimize -- only need to track the most recent element inserted after position 0
    val endCond = finalValue+1
    @tailrec
    def trackVal(pos: Int, value: Int, result: Int): Int = value match {
      case `endCond` => result
      case _ => {
        val newPos = ((pos + stepSize) % value) + 1
        trackVal(newPos, value+1, (if (newPos == 1) value else result))
      }
    }
    trackVal(0, 1, 1)
  }
}
