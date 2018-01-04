package day13

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").map(_.split(": ").map(_.toInt)).collect{ case Array(x,y) => (x,y) }.toStream
  println(PacketScannersSolver.getTripSeverity(input))
  println(PacketScannersSolver.getShortestDelayToAvoidCapture(input))
}
