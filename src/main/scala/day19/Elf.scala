package day19

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(SeriesOfTubesSolver.getPathLetters(input))
}
