package day07

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(RecursiveCircusSolver.getBottomProgram(input))
  println(RecursiveCircusSolver.getWeightToBalanceTree(input))
}
