package day09

import scala.annotation.tailrec

object StreamProcessingSolver {
  def getTotalScore(input: String): (Int, Int) = {
    @tailrec
    def process(input: String, inGarbage: Boolean, depth: Int, score: Int, garbageCount: Int): (Int, Int) = {
      if (input.isEmpty) {
        (score + depth, garbageCount)
      } else if (inGarbage) {
        input.head match {
          case '>' => process(input.tail, false, depth, score, garbageCount)
          case '!' => process(input.tail.tail, inGarbage, depth, score, garbageCount)
          case _ => process(input.tail, inGarbage, depth, score, garbageCount + 1)
        }
      } else {
        input.head match {
          case '{' => process(input.tail, inGarbage, depth + 1, score, garbageCount)
          case '}' => process(input.tail, inGarbage, depth - 1, score + depth, garbageCount)
          case '<' => process(input.tail, true, depth, score, garbageCount)
          case _ => process(input.tail, inGarbage, depth, score, garbageCount)
        }
      }
    }
    process(input, false, 0, 0, 0)
  }
}
