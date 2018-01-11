package day21

import scala.annotation.tailrec

/* One of my favorite solutions, largely due to the power of the derived square matrix `split` and `join` functions */

object FractalArtSolver {
  type Matrix = List[String]

  private
  def parseMatrix(matrixRaw: String): Matrix = matrixRaw.split("/").toList

  private
  def parseRule(ruleRaw: String): (Matrix, Matrix) = ruleRaw.split(" => ").map(parseMatrix).toList match { case List(a,b) => (a,b) }

  implicit class ExtendedMatrix(val matrix: Matrix) extends AnyVal {
    def rotr90: Matrix = matrix.transpose.map(_.reverse).map(_.mkString)
    def split(subsize: Int): List[Matrix] = matrix.map(_.grouped(subsize).toList).grouped(subsize).map(_.transpose).reduce(_ ++ _)
  }

  implicit class ExtendedListMatrix(val listMatrix: List[Matrix]) extends AnyVal {
    def join: Matrix = {
      val size = math.sqrt(listMatrix.length).toInt
      listMatrix.transpose.map(_.grouped(size).toList).map(_.map(_.reduce(_++_))).transpose.reduce(_++_)
    }
  }

  /* Could move this to the rule parser -- keyify all similar matrices up front, trading space for time */
  @tailrec
  private
  def enhanceMatrix(matrix: Matrix, rules: Map[Matrix, Matrix], flipped: Boolean=false): Matrix = {
    if (rules.contains(matrix)) {
      rules(matrix)
    } else {
      val nextMatrix = if (flipped) matrix.reverse.rotr90 else matrix.reverse
      enhanceMatrix(nextMatrix, rules, !flipped)
    }
  }

  def countOnPixels(rulesRaw: List[String], iters: Int, initMatrixRaw: String=".#./..#/###"): Int = {
    val initMatrix = parseMatrix(initMatrixRaw)
    val rules = rulesRaw.map(parseRule).toMap

    @tailrec
    def enhance(matrix: Matrix, iters: Int): Matrix = {
      if (iters == 0) {
        matrix
      } else {
        val subsize = if (matrix.length % 2 == 0) 2 else 3
        val nextMatrix = matrix.split(subsize).map(enhanceMatrix(_,rules)).join
        enhance(nextMatrix, iters-1)
      }
    }
    val finalMatrix = enhance(initMatrix, iters)
    finalMatrix.map(_.count(_ == '#')).reduce(_+_)
  }
}
