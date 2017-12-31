package day01

import org.scalatest.{Matchers, FlatSpec}

class InverseCaptchaSolverSpec extends FlatSpec with Matchers {
  import InverseCaptchaSolver._

  "An InverseCaptchaSolver" should "sum matching, circularly adjacent digits" in {
    sumAdjacentMatchingDigits(Array(1,1,2,2)) should be(3)
    sumAdjacentMatchingDigits(Array(1,1,1,1)) should be(4)
    sumAdjacentMatchingDigits(Array(1,2,3,4)) should be(0)
    sumAdjacentMatchingDigits(Array(9,1,2,1,2,1,2,9)) should be(9)
  }

  it should "sum matching, circularly opposite digits" in {
    sumOppositeMatchingDigits(Array(1,2,1,2)) should be(6)
    sumOppositeMatchingDigits(Array(1,2,2,1)) should be(0)
    sumOppositeMatchingDigits(Array(1,2,3,4,2,5)) should be(4)
    sumOppositeMatchingDigits(Array(1,2,3,1,2,3)) should be(12)
    sumOppositeMatchingDigits(Array(1,2,1,3,1,4,1,5)) should be(4)
  }
}
