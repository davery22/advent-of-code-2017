package day06

import scala.annotation.tailrec

object MemoryReallocationSolver {
  def getRedistributionCount(banks: Array[Int]): (Int, Int) = {
    @tailrec
    def redistribute(idx: Int, blocks: Int): Unit = {
      if (blocks > 0) {
        banks(idx) += 1
        redistribute((idx+1) % banks.length, blocks-1)
      }
    }
    @tailrec
    def countRedistributions(steps: Int, configurations: Map[Vector[Int],Int]): (Int, Int) = {
      val banksConfig = banks.toVector
      if (configurations.contains(banksConfig)) {
        (steps, steps-configurations(banksConfig))
      } else {
        val idx = banks.indexOf(banks.max)
        val blocks = banks(idx)
        banks(idx) = 0
        redistribute((idx+1) % banks.length, blocks)
        countRedistributions(steps+1, configurations + ((banksConfig, steps)))
      }
    }

    countRedistributions(0, Map())
  }
}
