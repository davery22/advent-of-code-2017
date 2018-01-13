package day25

import org.scalatest.{Matchers, FlatSpec}

class HaltingProblemSolverSpec extends FlatSpec with Matchers {
  import HaltingProblemSolver._

  "A HaltingProblemSolver" should "get the diagnostic checksum for the Turing Machine" in {
    getDiagnosticChecksum(
      """Begin in state A.
        |Perform a diagnostic checksum after 6 steps.

        |In state A:
        |  If the current value is 0:
        |    - Write the value 1.
        |    - Move one slot to the right.
        |    - Continue with state B.
        |  If the current value is 1:
        |    - Write the value 0.
        |    - Move one slot to the left.
        |    - Continue with state B.

        |In state B:
        |  If the current value is 0:
        |    - Write the value 1.
        |    - Move one slot to the left.
        |    - Continue with state A.
        |  If the current value is 1:
        |    - Write the value 1.
        |    - Move one slot to the right.
        |    - Continue with state A.""".stripMargin) should be(3)
  }
}
