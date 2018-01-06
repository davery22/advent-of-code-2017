package day17

import org.scalatest.{Matchers, FlatSpec}

class SpinlockSolverSpec extends FlatSpec with Matchers {
  import SpinlockSolver._

  "A SpinlockSolver" should "get the value after the specified value in the completed circular buffer" in {
    getPostFinalValue(3, 2017, 2017) should be(638)
    getPostFinalValue(3, 2017, 0) should be(1226)
  }
}
