package day19

import org.scalatest.{Matchers, FlatSpec}

class SeriesOfTubesSolverSpec extends FlatSpec with Matchers {
  import SeriesOfTubesSolver._

  "A SeriesOfTubesSolver" should "recover the correct string (letters in correct order), and number of steps, from the path" in {
    getPathLetters(
      List(
        "     |          ",
        "     |  +--+    ",
        "     A  |  C    ",
        " F---|----E|--+ ",
        "     |  |  |  D ",
        "     +B-+  +--+ ")) should be(("ABCDEF", 38))
  }
}
