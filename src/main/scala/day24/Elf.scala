package day24

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(ElectromagneticMoatSolver.getStrongestBridgeStrength(input))
  println(ElectromagneticMoatSolver.getStrongestBridgeStrength(input, longest=true))
}
