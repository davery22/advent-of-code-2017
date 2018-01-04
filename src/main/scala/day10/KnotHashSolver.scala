package day10

import scala.annotation.tailrec
import scala.reflect._

object KnotHashSolver {
  implicit class ExtendedInt(val i: Int) extends AnyVal {
    def mod(n: Int): Int = { // Correctly handles negative operand (but not negative modulus)
      val ret = i % n
      if (ret < 0) ret + n else ret
    }
  }

  implicit class ExtendedArr[T:ClassTag](val arr: Array[T]) { // I guess you need reflection to input generic arrays in Scala?
    /* NOTE: Replaced functional swap with imperative swap. Drastic performance improvement for day14. */
    //def swap(idx1: Int, idx2: Int): Array[T] = arr.updated(idx1, arr(idx2)).updated(idx2, arr(idx1))
    def swap(idx1: Int, idx2: Int): Array[T] = { val temp = arr(idx1); arr(idx1) = arr(idx2); arr(idx2) = temp; arr }
  }

  def singleRoundHash(circle: Array[Int], lengths: Array[Int], start: Int, skip: Int): (Array[Int], Int, Int) = {
    @tailrec
    def reverseSection(circle: Array[Int], start: Int, length: Int): Array[Int] = {
      if (length/2 == 0) {
        circle
      } else {
        reverseSection(
          circle.swap(start, (start + length - 1) mod circle.length),
          (start + 1) mod circle.length,
          length - 2)
      }
    }
    @tailrec
    def knot(circle: Array[Int], lengths: Array[Int], start: Int, skip: Int): (Array[Int], Int, Int) = {
      if (lengths.isEmpty) {
        (circle, start, skip)
      } else {
        knot(
          reverseSection(circle, start, lengths.head),
          lengths.tail,
          (start + lengths.head + skip) mod circle.length,
          (skip + 1) mod circle.length)
      }
    }
    knot(circle, lengths, start, skip)
  }

  def checkKnottyList(lengths: Array[Int], circle: Array[Int]=(0 to 255).toArray): Int = {
    val knotted = singleRoundHash(circle, lengths, 0, 0)._1
    knotted(0) * knotted(1)
  }

  def getKnotHash(input: String, circle: Array[Int]=(0 to 255).toArray): String = {
    val lengths = input.toArray.map(_.toInt) ++ Array(17, 31, 73, 47, 23)

    def hash(circle: Array[Int], lengths: Array[Int], count: Int, start: Int, skip: Int): String = {
      if (count == 0) {
        circle.grouped(16).map(_.reduce(_^_)).map(a => f"${a}%02x").mkString
      } else {
        val (newCircle, newStart, newSkip) = singleRoundHash(circle, lengths, start, skip)
        hash(newCircle, lengths, count - 1, newStart, newSkip)
      }
    }
    hash(circle, lengths, 64, 0, 0)
  }
}
