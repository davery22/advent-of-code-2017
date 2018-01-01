package day08

import org.scalatest.{Matchers, FlatSpec}

class YouLikeRegistersSolverSpec extends FlatSpec with Matchers {
  import YouLikeRegistersSolver._

  "A YouLikeRegistersSolver" should "find the largest value in any register after (and during) instructions" in {
    getLargestValueAfterInstructions(
      List(
        "b inc 5 if a > 1",
        "a inc 1 if b < 5",
        "c dec -10 if a >= 1",
        "c inc -20 if c == 10")) should be((1,10))
  }
}
