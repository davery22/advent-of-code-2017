package day23

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(CoprocessorConflagrationSolver.countMulInExecution(input))
  println(CoprocessorConflagrationSolver.countComposites(105700, 122700, 17))
}
