package day04

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(HighEntropyPassphrasesSolver.countValidPassphrases(input))
  println(HighEntropyPassphrasesSolver.countValidPassphrases(input, noAnagrams=true))
}
