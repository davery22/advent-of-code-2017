package day16

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString.split(",").toList
  println(PermutationPromenadeSolver.getFinalOrdering(input))
  println(PermutationPromenadeSolver.getFinalOrdering(input, 1000000000))
}
