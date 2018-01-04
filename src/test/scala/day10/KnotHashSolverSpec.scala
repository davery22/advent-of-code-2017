package day10

import org.scalatest.{Matchers, FlatSpec}

class KnotHashSolverSpec extends FlatSpec with Matchers {
  import KnotHashSolver._

  "A KnotHashSolver" should "verify the product of the first two numbers of the knotted list" in {
    checkKnottyList(Array(3,4,1,5), Array(0,1,2,3,4)) should be(12)
  }

  "A KnotHashSolver" should "compute the correct hash" in {
    getKnotHash("") should be("a2582a3a0e66e6e86e3812dcb672a272")
    getKnotHash("AoC 2017") should be("33efeb34ea91902bb2f59c9920caa6cd")
    getKnotHash("1,2,3") should be("3efbe78a8d82f29979031a4aa0b16a9d")
    getKnotHash("1,2,4") should be("63960835bcdc130f0b66d7ff4f6a5a8e")
  }
}
