package day02

import org.scalatest.{Matchers, FlatSpec}

class CorruptionChecksumSolverSpec extends FlatSpec with Matchers {
  import CorruptionChecksumSolver._

  "A CorruptionChecksumSolver" should "compute the max-min checksum" in {
    computeMaxMinChecksum(Array(Array(5,1,9,5), Array(7,5,3), Array(2,4,6,8))) should be(18)
    computeMaxMinChecksum(Array(Array(1,2,3), Array(4,5,6), Array(7,8,22))) should be(19)
  }

  it should "compute the divisibility checksum" in {
    computeDivisibilityChecksum(Array(Array(5,9,2,8), Array(9,4,7,3), Array(3,8,6,5))) should be(9)
    computeDivisibilityChecksum(Array(Array(2,7,5,15), Array(22,24,26,28,14), Array(10,1000,9))) should be(105)
  }
}
