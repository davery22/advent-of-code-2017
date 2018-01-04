package day14

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString
  println(DiskDefragmentationSolver.countSquaresInUse(input))
  println(DiskDefragmentationSolver.countRegions(input))
}
