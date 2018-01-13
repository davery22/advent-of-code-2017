package day24

import org.scalatest.{Matchers, FlatSpec}

class ElectromagneticMoatSolverSpec extends FlatSpec with Matchers {
  import ElectromagneticMoatSolver._

  "An ElectromagneticMoatSolver" should "determine the strength of the strongest possible bridge" in {
    getStrongestBridgeStrength(
      List(
        "0/2",
        "2/2",
        "2/3",
        "3/4",
        "3/5",
        "0/1",
        "10/1",
        "9/10")) should be(31)
  }

  it should "determine the strength and length of the longest possible bridge (ties resolved by greater strength)" in {
    getStrongestBridgeStrength(
      List(
        "0/2",
        "2/2",
        "2/3",
        "3/4",
        "3/5",
        "0/1",
        "10/1",
        "9/10"), longest=true) should be(19)
  }
}
