package day24

import scala.annotation.tailrec

object ElectromagneticMoatSolver {
  @tailrec
  private
  def mapComponents(compsRaw: List[String], comps: Map[Int, List[Int]]): Map[Int, List[Int]] = compsRaw match {
    case head :: tail => mapComponents(tail, head.split("/").map(_.toInt) match { case Array(a,b) => comps + ((a, b :: comps(a)),(b, a :: comps(b))) })
    case _ => comps
  }

  def getStrongestBridgeStrength(componentsRaw: List[String], longest: Boolean=false): Int = {
    val components = mapComponents(componentsRaw, Map().withDefaultValue(List()))

    def getBest(par: Int, children: List[Int], comps: Map[Int, List[Int]], curS: Int, maxS: Int, curL: Int, maxL: Int): (Int, Int) = children match {
      case child :: tail =>
        val newComps = comps + ((par, comps(par).diff(List(child))), (child, comps(child).diff(List(par))))
        val (newMaxS, newMaxL) = getBest(child, newComps(child), newComps, curS + par + child, maxS, curL + 1, maxL)
        getBest(par, tail, comps, curS, newMaxS, curL, newMaxL)
      case Nil =>
        if (!longest || curL == maxL) (math.max(curS, maxS), curL) else if (curL > maxL) (curS, curL) else (maxS, maxL)
    }
    getBest(0, components(0), components, 0, 0, 0, 0)._1
  }
}
