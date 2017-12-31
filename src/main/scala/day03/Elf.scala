package day03

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString.toInt
  println(SpiralMemorySolver.getManhattanDistance(input))
  println(SpiralMemorySolver.getFirstLargerInCumulativeSpiral(input))
}
