package day22

import org.scalatest.{Matchers, FlatSpec}

class SporificaVirusSolverSpec extends FlatSpec with Matchers {
  import SporificaVirusSolver._

  "A SporificaVirusSolver" should "determine how many bursts result in infection" in {
    countInfectionBursts(List("..#","#..","..."), bursts=7) should be(5)
    countInfectionBursts(List("..#","#..","..."), bursts=70) should be(41)
    countInfectionBursts(List("..#","#..","..."), bursts=10000) should be(5587)
  }

  it should "determine how many bursts result in infection with an evolved carrier" in {
    countInfectionBursts(List("..#","#..","..."), bursts=100, evolved=true) should be(26)
    countInfectionBursts(List("..#","#..","..."), bursts=10000000, evolved=true) should be(2511944)
  }
}
