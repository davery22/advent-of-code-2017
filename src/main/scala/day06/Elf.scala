package day06

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString.split("\t").map(_.toInt).toArray
  println(MemoryReallocationSolver.getRedistributionCount(input))
}
