package day21

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(FractalArtSolver.countOnPixels(input, iters=5))
  println(FractalArtSolver.countOnPixels(input, iters=18))
}
