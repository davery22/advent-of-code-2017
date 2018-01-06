package day18

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(DuetSolver.getFirstRecoveredFrequency(input))
  println(DuetSolver.getProg1SendCount(input))
}
