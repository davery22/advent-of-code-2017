package day01

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").mkString.split("").map(_.toInt)
  println(InverseCaptchaSolver.sumAdjacentMatchingDigits(input))
  println(InverseCaptchaSolver.sumOppositeMatchingDigits(input))
}
