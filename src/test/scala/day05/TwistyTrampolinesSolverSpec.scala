package day05

import org.scalatest.{Matchers, FlatSpec}

class TwistyTrampolinesSolverSpec extends FlatSpec with Matchers {
  import TwistyTrampolinesSolver._

  "A TwistyTrampolinesSplver" should "determine the number of steps required to exit the maze" in {
    getStepsToExit(Array(0,0,0,1)) should be(7)
    getStepsToExit(Array(0,3,0,1,-3)) should be(5)
    getStepsToExit(Array(1,-2,3,4,5)) should be(2)
  }

  it should "determine the number of steps required to exit the maze with even stranger rules" in {
    getStepsToExit(Array(0,0,0,1), evenStranger=true) should be(7)
    getStepsToExit(Array(0,3,0,1,-3), evenStranger=true) should be(10)
    getStepsToExit(Array(1,3,3,4,3,0,-1,-3), evenStranger=true) should be(14)
  }
}
