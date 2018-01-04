package day10

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString
  println(KnotHashSolver.checkKnottyList(input.split(",").map(_.toInt)))
  println(KnotHashSolver.getKnotHash(input))
}
