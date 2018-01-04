package day13

import scala.annotation.tailrec

object PacketScannersSolver {
  def getTripSeverity(scanners: Stream[(Int, Int)], captureIsWeighted: Boolean=true, delay: Int=0): Int = {
    scanners.map{ case (depth, range) => {
      val seqLen = 2 * (range - 1)
      val arrivalTime = depth + delay
      val scannerPositionOnArrival = math.min(seqLen - arrivalTime % seqLen, arrivalTime % seqLen)
      val scannerHit = scannerPositionOnArrival == 0
      if (scannerHit) (if (captureIsWeighted) depth * range else 1) else 0
    }}.reduce(_+_)
  }

  def getShortestDelayToAvoidCapture(scanners: Stream[(Int, Int)]): Int = {
    Stream.from(0).map(getTripSeverity(scanners, false, _)).indexOf(0)
  }
}
