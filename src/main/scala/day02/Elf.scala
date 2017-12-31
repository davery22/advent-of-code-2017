package day02

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").map(_.split("\t")).map(_.map(_.toInt)).toArray
  println(CorruptionChecksumSolver.computeMaxMinChecksum(input))
  println(CorruptionChecksumSolver.computeDivisibilityChecksum(input))
}
