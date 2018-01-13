package day23

import org.scalatest.{Matchers, FlatSpec}

class CoprocessorConflagrationSolverSpec extends FlatSpec with Matchers {
  import CoprocessorConflagrationSolver._

  "A CoprocessorConflagrationSolver" should "count the number of time the `mul` instruction is invoked" in {
    countMulInExecution(
      List(
        "set a 5",
        "set b 1",
        "mul b a",
        "sub a 1",
        "jnz a -2",
        "sub b 120",
        "jnz b 2",
        "mul b 0")) should be(6)
  }

  it should "count the number of composite numbers in the specified interval" in {
    countComposites(2,20) should be(11)
    countComposites(40,60,3) should be(6)
  }
}
