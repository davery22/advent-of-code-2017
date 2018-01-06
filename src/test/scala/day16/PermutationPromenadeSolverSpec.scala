package day16

import org.scalatest.{Matchers, FlatSpec}

class PermutationPromenadeSolverSpec extends FlatSpec with Matchers {
  import PermutationPromenadeSolver._

  "A PermutationPromenadeSolver" should "get the final program ordering after the dance(s)" in {
    getFinalOrdering(List("s1","x3/4","pe/b"), 1, "abcde") should be("baedc")
    getFinalOrdering(List("s1","x3/4","pe/b"), 2, "abcde") should be("ceadb")
  }
}
