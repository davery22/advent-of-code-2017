package day22

import scala.annotation.tailrec

object SporificaVirusSolver {
  def countInfectionBursts(input: List[String], bursts: Int, evolved: Boolean=false): Int = {
    val initInfected = input.map(_.zipWithIndex).zipWithIndex
      .map(_ match { case (a,x) => a.map(_ match { case (b,y) => ((x,y),b) }) })
      .reduce(_++_).toMap.filter(_._2 == '#')
    val initPos = (input.length/2, input(0).length/2)

    def mod(i: Int, n: Int) = { val ret = i % n; if (ret < 0) ret+n else ret }

    def updatePos(pos: (Int, Int), dir: Int) = dir match {
      case 3 /* Left */ => (pos._1, pos._2-1)
      case 2 /* Down */ => (pos._1+1, pos._2)
      case 1 /* Right */ => (pos._1, pos._2+1)
      case 0 /* Up */ => (pos._1-1, pos._2)
    }

    @tailrec
    def infect(pos: (Int, Int), dir: Int, bursts: Int, infected: Map[(Int,Int), Char], infectedCount: Int): Int = {
      if (bursts == 0) {
        infectedCount
      } else if (infected.contains(pos)) {
        val nextDir = mod(dir+1, 4)
        infect(updatePos(pos, nextDir), nextDir, bursts-1, infected-pos, infectedCount)
      } else {
        val nextDir = mod(dir-1, 4)
        infect(updatePos(pos, nextDir), nextDir, bursts-1, infected+(pos->'#'), infectedCount+1)
      }
    }

    @tailrec
    def infect2(pos: (Int, Int), dir: Int, bursts: Int, infected: Map[(Int, Int), Char], infectedCount: Int): Int = {
      if (bursts == 0) {
        infectedCount
      } else infected(pos) match {
        case 'W' =>
          infect2(updatePos(pos, dir), dir, bursts-1, infected+(pos->'#'), infectedCount+1)
        case '#' =>
          val nextDir = mod(dir+1, 4)
          infect2(updatePos(pos, nextDir), nextDir, bursts-1, infected+(pos->'F'), infectedCount)
        case 'F' =>
          val nextDir = mod(dir+2, 4)
          infect2(updatePos(pos, nextDir), nextDir, bursts-1, infected-pos, infectedCount)
        case '.' =>
          val nextDir = mod(dir-1, 4)
          infect2(updatePos(pos, nextDir), nextDir, bursts-1, infected+(pos->'W'), infectedCount)
      }
    }
    
    if (evolved) infect2(initPos, 0, bursts, initInfected.withDefaultValue('.'), 0) else infect(initPos, 0, bursts, initInfected, 0)
  }
}
