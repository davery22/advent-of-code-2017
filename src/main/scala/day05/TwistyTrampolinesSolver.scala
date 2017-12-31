package day05

import scala.annotation.tailrec

object TwistyTrampolinesSolver {
  def getStepsToExit(input: Array[Int], evenStranger: Boolean=false): Int = {
    @tailrec
    def rec(idx: Int, steps: Int): Int = {
      if (idx < 0 || idx >= input.length) {
        steps
      } else {
        val jump = idx + input(idx)
        input(idx) += (if (evenStranger && input(idx) >= 3) -1 else 1)
        rec(jump, steps+1)
      }
    }

    rec(0, 0)
  }
}
