package day05

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").map(_.toInt).toArray
  println(TwistyTrampolinesSolver.getStepsToExit(input.clone))
  println(TwistyTrampolinesSolver.getStepsToExit(input.clone, evenStranger=true))
}
