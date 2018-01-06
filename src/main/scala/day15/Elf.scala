package day15

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").map(_.toInt).toList match { case List(a,b) => (a,b) }
  println(DuelingGeneratorsSolver.getMatchCount(input._1, input._2))
  println(DuelingGeneratorsSolver.getPickyMatchCount(input._1, input._2))
}
