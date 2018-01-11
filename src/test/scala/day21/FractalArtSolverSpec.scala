package day21

import org.scalatest.{Matchers, FlatSpec}

class FractalArtSolverSpec extends FlatSpec with Matchers {
  import FractalArtSolver._

  "A FractalArtSolver" should "count the on pixels after a given number of iterations" in {
    countOnPixels(
      List(
        "../.# => ##./#../...",
        ".#./..#/### => #..#/..../..../#..#"), iters=2) should be(12)
  }
}
