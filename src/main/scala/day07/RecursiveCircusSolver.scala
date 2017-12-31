package day07

import scala.annotation.tailrec

object RecursiveCircusSolver {
  case class Program(name: String, weight: Int, subTower: Array[String])

  def parseProgram(programRaw: String): Program = {
    // Matches:
    //   prog (weight)
    //   prog (weight) -> subprog1, subprog2...
    val pattern = "(\\w+) \\((\\d+)\\)(?: -> ((?:\\w+(?:, )?)+))?".r

    programRaw match {
      case pattern(name, weight, subs) => Program(name, weight.toInt, (if (subs != null) subs.split(", ") else Array()))
    }
  }

  def getBottomProgram(programInfo: List[String]): String = {
    val programs = programInfo.map(parseProgram)

    @tailrec
    def getFloaters(programs: List[Program], marked: Set[String]): Set[String] = {
      if (programs.isEmpty) {
        marked
      } else {
        getFloaters(programs.tail, marked ++ programs.head.subTower)
      }
    }

    val floaters = getFloaters(programs, Set())
    programs.find(prog => !floaters.contains(prog.name)).get.name
  }

  def getWeightToBalanceTree(programInfo: List[String]): Int = {
    val bottom = getBottomProgram(programInfo)
    val programs = programInfo.map(parseProgram)
    val progMap = Map(programs.map(_.name).zip(programs): _*)

    def findBalancingWeight(progname: String): (Int, Option[Int]) = {
      val program = progMap(progname)
      val towerResults = program.subTower.view.map(findBalancingWeight)
      val towerWeights = towerResults.map(_._1)

      val ret = towerResults.find(!_._2.isEmpty)
      if (!ret.isEmpty) {
        ret.get
      } else if (towerWeights.isEmpty) {
        (program.weight, None)
      } else if (towerWeights.forall(_ == towerWeights(0))) {
        (program.weight + (towerWeights(0) * towerWeights.length), None)
      } else {
        val leastCommon = towerWeights.groupBy(identity).minBy(_._2.size)._1
        val mostCommon = towerWeights.find(_ != leastCommon).get
        val unbalancedWeight = progMap(towerWeights.zip(program.subTower).find(_._1 == leastCommon).get._2).weight
        (0, Some(unbalancedWeight + mostCommon - leastCommon))
      }
    }

    findBalancingWeight(bottom)._2.get
  }
}
