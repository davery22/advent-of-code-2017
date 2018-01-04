package day12

import org.scalatest.{Matchers, FlatSpec}

class DigitalPlumberSolverSpec extends FlatSpec with Matchers {
  import DigitalPlumberSolver._

  "A DigitalPlumberSolver" should "count the programs in the group" in {
    countProgramsInGroup(
      List(
        "0 <-> 2",
        "1 <-> 1",
        "2 <-> 0, 3, 4",
        "3 <-> 2, 4",
        "4 <-> 2, 3, 6",
        "5 <-> 6",
        "6 <-> 4, 5"),
      0) should be(6)
  }

  it should "count the number of program groups" in {
    countProgramGroups(
      List(
        "0 <-> 2",
        "1 <-> 1",
        "2 <-> 0, 3, 4",
        "3 <-> 2, 4",
        "4 <-> 2, 3, 6",
        "5 <-> 6",
        "6 <-> 4, 5")) should be(2)
  }
}
