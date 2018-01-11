package day19

import scala.annotation.tailrec

object SeriesOfTubesSolver {
  def getPathLetters(path: List[String]): (String, Int) = {
    type Direction = (Int, Int) // (up/down, left/right)
    def posExists(row: Int, col: Int): Boolean = row >= 0 && row < path.length && col >= 0 && col < path(row).length
    def posIsValid(row: Int, col: Int, dir: Direction): Boolean = posExists(row, col) && path(row)(col) != ' ' && path(row)(col) != (if (dir._1 == 0) '-' else '|')

    @tailrec
    def walk(row: Int, col: Int, dir: Direction, chars: String, steps: Int): (String, Int) = {
      if (!posExists(row, col)) {
        (chars, steps)
      } else path(row)(col) match {
        case ' ' => (chars, steps)
        case '+' => dir match {
          case (0, _) => (if (posIsValid(row+1, col, dir)) walk(row+1, col, (1, 0), chars, steps+1) else walk(row-1, col, (-1, 0), chars, steps+1))
          case (_, 0) => (if (posIsValid(row, col+1, dir)) walk(row, col+1, (0, 1), chars, steps+1) else walk(row, col-1, (0, -1), chars, steps+1))
          case _ => (chars, steps) // For the compiler; currently unreachable
        }
        case '|' | '-' => walk(row + dir._1, col + dir._2, dir, chars, steps+1)
        case letter => walk(row + dir._1, col + dir._2, dir, chars + letter, steps+1)
      }
    }
    walk(0, path(0).indexOf('|'), (1, 0), "", 0)
  }
}
