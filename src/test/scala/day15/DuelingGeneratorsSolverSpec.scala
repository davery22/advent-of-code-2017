package day15

import org.scalatest.{Matchers, FlatSpec}

class DuelingGeneratorsSolverSpec extends FlatSpec with Matchers {
  import DuelingGeneratorsSolver._

  "A DuelingGeneratorsSolver" should "count the number of times the generators' outputs match (lowest 16 bits)" in {
    getMatchCount(65, 8921) should be(588)
  }

  it should "count the number of times the generators' outputs match when they're being picky" in {
    getPickyMatchCount(65, 8921) should be(309)
  }
}
