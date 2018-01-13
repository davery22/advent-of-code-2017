package day25

import christmas.northPole._

object Santa extends Sleigh with Reindeer {
  val input = getResourceLines("input.txt").mkString("\n")
  println(HaltingProblemSolver.getDiagnosticChecksum(input))
}
