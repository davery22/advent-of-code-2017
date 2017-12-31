package day07

import org.scalatest.{Matchers, FlatSpec}

class RecursiveCircusSolverSpec extends FlatSpec with Matchers {
  import RecursiveCircusSolver._

  "A RecursiveCircusSolver" should "get the bottom program" in {
    getBottomProgram(
      List(
        "pbga (66)",
        "xhth (57)",
        "ebii (61)",
        "havc (66)",
        "ktlj (57)",
        "fwft (72) -> ktlj, cntj, xhth",
        "qoyq (66)",
        "padx (45) -> pbga, havc, qoyq",
        "tknk (41) -> ugml, padx, fwft",
        "jptl (61)",
        "ugml (68) -> gyxo, ebii, jptl",
        "gyxo (61)",
        "cntj (57)")) should be("tknk")
  }

  it should "get the corrected weight required to balance the tree" in {
    getWeightToBalanceTree(
      List(
        "pbga (66)",
        "xhth (57)",
        "ebii (61)",
        "havc (66)",
        "ktlj (57)",
        "fwft (72) -> ktlj, cntj, xhth",
        "qoyq (66)",
        "padx (45) -> pbga, havc, qoyq",
        "tknk (41) -> ugml, padx, fwft",
        "jptl (61)",
        "ugml (68) -> gyxo, ebii, jptl",
        "gyxo (61)",
        "cntj (57)")) should be(60)
  }
}
