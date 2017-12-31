package day03

import org.scalatest.{Matchers, FlatSpec}

class SpiralMemorySolverSpec extends FlatSpec with Matchers {
  import SpiralMemorySolver._

  "A SpiralMemorySolver" should "get the manhattan distance from the index to the center" in {
    val expected = Array(0,1,2,1,2,1,2,1,2,3,2,3,4,3,2,3,4,3,2,3,4,3,2,3,4,5,4,3,4,5,6,5,4,3,4,5,6,5,4,3,4,5,6,5,4,3,4,5,6,7,6,5,4,5)
    for (i <- 1 to expected.length) {
      getManhattanDistance(i) should be(expected(i-1))
    }
  }

  it should "get the first larger value in an accumulating spiral" in {
    getFirstLargerInCumulativeSpiral(0) should be(1)
    getFirstLargerInCumulativeSpiral(2) should be(4)
    getFirstLargerInCumulativeSpiral(10) should be(11)
    getFirstLargerInCumulativeSpiral(11) should be(23)
    getFirstLargerInCumulativeSpiral(800) should be(806)
  }
}
