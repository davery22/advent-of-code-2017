package day22

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(SporificaVirusSolver.countInfectionBursts(input, bursts=10000))
  println(SporificaVirusSolver.countInfectionBursts(input, bursts=10000000, evolved=true))
}
