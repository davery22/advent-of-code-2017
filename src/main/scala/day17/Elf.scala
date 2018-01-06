package day17

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString.toInt
  println(SpinlockSolver.getPostFinalValue(input, 2017, 2017))
  println(SpinlockSolver.getPostFinalValue(input, 50000000, 0))
}
