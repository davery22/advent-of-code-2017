package day09

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString
  println(StreamProcessingSolver.getTotalScore(input))
}
