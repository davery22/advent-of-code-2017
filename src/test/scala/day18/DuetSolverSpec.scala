package day18

import org.scalatest.{Matchers, FlatSpec}

class DuetSolverSpec extends FlatSpec with Matchers {
  import DuetSolver._

  "A DuetSolver" should "get the first recovered frequency" in {
    getFirstRecoveredFrequency(
      List(
        "set a 1",
        "add a 2",
        "mul a a",
        "mod a 5",
        "snd a",
        "set a 0",
        "rcv a",
        "jgz a -1",
        "set a 1",
        "jgz a -2")) should be(4)
  }

  it should "count the times program 1 sends a value" in {
    getProg1SendCount(
      List(
        "set a 1",
        "add a 2",
        "mul a a",
        "mod a 5",
        "snd a",
        "set a 0",
        "rcv a",
        "jgz a -1",
        "set a 1",
        "jgz a -2")) should be(1)
  }
}
