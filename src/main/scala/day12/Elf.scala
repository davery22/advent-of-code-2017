package day12

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(DigitalPlumberSolver.countProgramsInGroup(input, 0))
  println(DigitalPlumberSolver.countProgramGroups(input))
}
