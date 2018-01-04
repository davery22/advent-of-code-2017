package day11

import org.scalatest.{Matchers, FlatSpec}

class HexEdSolverSpec extends FlatSpec with Matchers {
  import HexEdSolver._

  "A HexEdSolver" should "get the minimum steps for the path, and max steps away" in {
    getMinSteps(List("ne","ne","ne")) should be(3,3)
    getMinSteps(List("ne","ne","sw","sw")) should be(0,2)
    getMinSteps(List("ne","ne","s","s")) should be(2,2)
    getMinSteps(List("se","sw","se","sw","sw")) should be(3,3)
  }
}
