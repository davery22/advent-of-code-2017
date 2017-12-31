package day02

import scala.annotation.tailrec

object CorruptionChecksumSolver {
  def computeMaxMinChecksum(nums: Array[Array[Int]]): Int = {
    nums.map(a => a.max-a.min).foldRight(0)(_+_)
  }

  def computeDivisibilityChecksum(nums: Array[Array[Int]]): Int = {
    nums.map(getDivisionResult).foldRight(0)(_+_)
  }

  def getDivisionResult(arr: Array[Int]): Int = {
    @tailrec
    def rec(a: Array[Int], b: Array[Int]): Int = {
      a match {
        case Array(aHead, _*) => b match {
          case Array(bHead, _*) => if (aHead % bHead == 0) {
            aHead / bHead
          } else if (bHead % aHead == 0) {
            bHead / aHead
          } else {
            rec(a, b.tail)
          }
          case _ => rec(a.tail, a.tail.tail)
        }
        case _ => -1
      }
    }

    rec(arr, arr.tail)
  }
}
