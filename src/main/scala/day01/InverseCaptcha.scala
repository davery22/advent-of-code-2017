package day01

import scala.annotation.tailrec

object InverseCaptchaSolver {
  def sumAdjacentMatchingDigits(digits: Array[Int]): Int = {
    sumMatchingDigits(0, 0, digits, 1)
  }

  def sumOppositeMatchingDigits(digits: Array[Int]): Int = {
    sumMatchingDigits(0, 0, digits, digits.length/2)
  }

  @tailrec
  def sumMatchingDigits(i: Int, sum: Int, digits: Array[Int], step: Int): Int = {
    if (i < digits.length) {
      if (digits(i) == digits((i + step) % digits.length)) {
        sumMatchingDigits(i + 1, sum + digits(i), digits, step)
      } else {
        sumMatchingDigits(i + 1, sum, digits, step)
      }
    } else {
      sum
    }
  }
}
