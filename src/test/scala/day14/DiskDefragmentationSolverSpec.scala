package day14

import org.scalatest.{Matchers, FlatSpec}

class DiskDefragmentationSolverSpec extends FlatSpec with Matchers {
  import DiskDefragmentationSolver._

  "A DiskDefragmentationSolver" should "count the memory squares in use" in {
    countSquaresInUse("flqrgnkx") should be(8108)
  }

  it should "count the memory \"regions\" (region = contiguous used squares)" in {
    countRegions("flqrgnkx") should be(1242)
  }
}
