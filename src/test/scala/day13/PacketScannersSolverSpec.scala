package day13

import org.scalatest.{Matchers, FlatSpec}

class PacketScannersSolverSpec extends FlatSpec with Matchers {
  import PacketScannersSolver._

  "A PacketScannersSolver" should "get the total trip severity" in {
    getTripSeverity(Stream((0,3),(1,2),(4,4),(6,4))) should be(24)
  }
  
  it should "determine the shortest initial delay to avoid capture during the trip" in {
    getShortestDelayToAvoidCapture(Stream((0,3),(1,2),(4,4),(6,4))) should be(10)
  }
}
