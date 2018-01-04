package day14

import scala.annotation.tailrec
import day10.KnotHashSolver.getKnotHash

object DiskDefragmentationSolver {
  private
  def countSetBits(i: Int): Int = { // Brian Kernighan's algo.
    @tailrec
    def rec(i: Int, count: Int): Int = i match {
      case 0 => count
      case _ => rec(i&(i-1), count+1)
    }
    rec(i, 0)
  }

  def countSquaresInUse(key: String): Int = {
    (for (i <- 0 to 127) yield key + f"-$i").map(getKnotHash(_).grouped(2).map(Integer.parseInt(_, 16)).map(countSetBits).reduce(_+_)).reduce(_+_)
  }

  def countRegions(key: String): Int = {
    val memory = (for (i <- 0 to 127) yield key + f"-$i").map(getKnotHash(_))
      .map(hex => BigInt(hex, 16).toString(2).reverse.padTo(hex.length*4, '0').reverse).toList // convert to binary strings

    def visitRegion(x: Int, y: Int, memory: List[String]): List[String] = {
      if (y < 0 || y >= memory.length || x < 0 || x >= memory(y).length || memory(y)(x) == '0') {
        memory
      } else {
        visitRegion(x-1, y,
          visitRegion(x+1, y,
            visitRegion(x, y-1,
              visitRegion(x, y+1,
                memory.updated(y, memory(y).updated(x, '0'))))))
      }
    }

    @tailrec
    def loopOverMemory(x: Int, y: Int, count: Int, memory: List[String]): Int = {
      if (y == memory.length) {
        count
      } else if (x == memory(y).length) {
        loopOverMemory(0, y+1, count, memory)
      } else if (memory(y)(x) == '1') {
        loopOverMemory(x+1, y, count+1, visitRegion(x, y, memory))
      } else {
        loopOverMemory(x+1, y, count, memory)
      }
    }
    loopOverMemory(0, 0, 0, memory)
  }
}
