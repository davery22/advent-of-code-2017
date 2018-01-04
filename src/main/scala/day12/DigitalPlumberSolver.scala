package day12

import scala.annotation.tailrec

object DigitalPlumberSolver {
  private
  def parsePipe(pipe: String, pidGroups: Map[Int, List[Int]]): Map[Int, List[Int]] = {
    val pipePattern = "(\\d+) <-> ((?:\\d+(?:, )?)+)".r

    pipe match {
      case pipePattern(parent, children) => pidGroups.updated(parent.toInt, children.split(", ").map(_.toInt).toList)
    }
  }

  @tailrec
  private
  def getPidGroups(pipes: List[String], pidGroups: Map[Int, List[Int]]): Map[Int, List[Int]] = pipes match {
    case pipe :: _ => getPidGroups(pipes.tail, parsePipe(pipe, pidGroups))
    case Nil => pidGroups
  }

  @tailrec
  private
  def countChildren(children: List[Int], count: Int, visited: Set[Int], pidGroups: Map[Int, List[Int]]): (Int, Set[Int]) = children match {
    case child :: _ => {
      val (countD, visitedD) = countGroupMembers(child, count, visited, pidGroups)
      countChildren(children.tail, countD, visitedD, pidGroups)
    }
    case Nil => (count, visited)
  }
  
  private
  def countGroupMembers(currentPid: Int, count: Int, visited: Set[Int], pidGroups: Map[Int, List[Int]]): (Int, Set[Int]) = {
    if (visited.contains(currentPid)) {
      (count, visited)
    } else {
      countChildren(pidGroups(currentPid), count + 1, visited + currentPid, pidGroups)
    }
  }

  def countProgramsInGroup(input: List[String], pid: Int): Int = {
    /* My first fully functional DFS! */
    countGroupMembers(pid, 0, Set(), getPidGroups(input, Map()))._1
  }

  def countProgramGroups(input: List[String]): Int = {
    @tailrec
    def countGroups(count: Int, visited: Set[Int], pidGroups: Map[Int, List[Int]]): Int = pidGroups.keys.find(!visited.contains(_)) match {
      case Some(pid) => countGroups(count + 1, countGroupMembers(pid, 0, visited, pidGroups)._2, pidGroups)
      case None => count
    }
    countGroups(0, Set(), getPidGroups(input, Map()))
  }
}
