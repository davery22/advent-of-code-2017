package day06

import org.scalatest.{Matchers, FlatSpec}

class MemoryReallocationSolverSpec extends FlatSpec with Matchers {
  import MemoryReallocationSolver._

  "A MemoryReallocationSolver" should "determine how many redistributions can be done, and the size of the loop, before repeating configurations" in {
    getRedistributionCount(Array(0,2,7,0)) should be((5, 4))
    getRedistributionCount(Array(1,1,1,1,1,1)) should be((9, 6))
    getRedistributionCount(Array(8,6,7,5,3,0,9)) should be((22, 7))
  }
}
