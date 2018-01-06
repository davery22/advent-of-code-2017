package day16

import scala.annotation.tailrec

object PermutationPromenadeSolver {
  private
  def parseStep(step: String): String => String = {
    def spin(len: Int)(s: String) = s.takeRight(len) ++ s.dropRight(len)
    def exchange(i: Int, j: Int)(s: String) = s.updated(i, s(j)).updated(j, s(i))
    def partner(a: Char, b: Char)(s: String) = s.updated(s.indexOf(a), b).updated(s.indexOf(b), a)

    val spinPattern = "s(\\d+)".r
    val exchangePattern = "x(\\d+)/(\\d+)".r
    val partnerPattern = "p(\\w+)/(\\w+)".r

    step match {
      case spinPattern(len) => spin(len.toInt) _
      case exchangePattern(i,j) => exchange(i.toInt, j.toInt) _
      case partnerPattern(a,b) => partner(a(0), b(0)) _
    }
  }

  def getFinalOrdering(dance: List[String], repeats: Int=1, programs: String="abcdefghijklmnop"): String = {
    @tailrec
    def execSteps(dance: List[String => String], programs: String): String = dance match {
      case Nil => programs
      case step :: _ => execSteps(dance.tail, step(programs))
    }
    @tailrec
    def repeatDance(dance: List[String => String], repeats: Int, seen: List[String]): String = repeats match {
      case 0 => seen.head
      case _ => {
        val newOrder = execSteps(dance, seen.head)
        if (seen.contains(newOrder)) {
          seen(seen.length-1-((repeats-1) % seen.length)) // Take advantage of repeating patterns to cut out early
        } else {
          repeatDance(dance, repeats-1, newOrder :: seen)
        }
      }
    }
    repeatDance(dance.map(parseStep), repeats, List(programs))
  }
}
