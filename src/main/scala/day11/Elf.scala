package day11

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString.split(",").toList
  println(HexEdSolver.getMinSteps(input))
}
