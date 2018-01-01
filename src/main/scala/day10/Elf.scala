package day10

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString
  println(KnotHashSolver.checkKnottyList((0 to 255).toArray, input.split(",").map(_.toInt)))
  println(KnotHashSolver.getHash((0 to 255).toArray, input))
}
