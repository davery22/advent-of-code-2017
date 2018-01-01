package day08

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(YouLikeRegistersSolver.getLargestValueAfterInstructions(input))
}
