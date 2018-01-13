/* Note: This program doesn't actually solve the real Halting Problem. I know, big surprise. */
package day25

import scala.annotation.tailrec

object HaltingProblemSolver {
  case class State(val branches: Map[Int, (Int, Int, String)])
  
  private
  def parseBranch(branch: String): (Int, (Int, Int, String)) = {
    val pattern = "value is (\\d+)[\\d\\D]*value (\\d+)[\\d\\D]*to the (\\w+)[\\d\\D]*state (\\w+)".r.unanchored
    branch match {
      case pattern(read, write, move, nextStateName) =>
        (read.toInt, (write.toInt, (if (move == "right") 1 else -1), nextStateName))
    }
  }

  private
  def parseState(state: String): (String, State) = {
    val parts = state.split("\\R")
    val name: String = "(?<=state )\\w+".r.findAllIn(parts.head).next
    val branches = parts.tail.grouped(4).map(_.mkString).map(parseBranch).toMap
    (name, State(branches))
  }

  private
  def parseTuringMachine(tm: String): (String, Int, Map[String, State]) = {
    val parts = tm.split("\\R{2,}")
    val initStateName: String = "(?<=state )\\w+".r.findAllIn(parts.head).next
    val steps: Int = "(?<=after )\\d+".r.findAllIn(parts.head).next.toInt
    val states = parts.tail.map(parseState).toMap
    (initStateName, steps, states)
  }

  def getDiagnosticChecksum(tm: String): Int = {
    val (initStateName, steps, states) = parseTuringMachine(tm)

    @tailrec
    def execTM(name: String, steps: Int, tape: Set[Int], pos: Int): Int = steps match {
      case 0 => tape.size
      case _ =>
        val read = if (tape.contains(pos)) 1 else 0
        val (write, move, nextState) = states(name).branches(read)
        execTM(nextState, steps-1, (if (write == 1) tape+pos else tape-pos), pos+move)
    }
    execTM(initStateName, steps, Set(), 0)
  }
}
