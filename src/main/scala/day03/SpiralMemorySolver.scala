package day03

import scala.annotation.tailrec

object SpiralMemorySolver {
  def getManhattanDistance(index: Int): Int = {
    /* Imperative solution with looping */
    var sideLength = 0
    var area = 1
    while (area < index) {
      sideLength += 2
      area += sideLength*4
    }
    while (area > index) { // Implicitly upper-bounded to 4 iterations
      area -= sideLength
    }
    sideLength/2 + Math.abs(index - (area + sideLength/2))


    /* Constant-time solution (prone to floating point precision problems for large indices)
    val layer = math.ceil((math.sqrt(index)+1)/2).toInt
    var sideLength = 2*(layer-1)
    var area = (sideLength+1)*(sideLength+1)
    while (area > index) {
      area -= sideLength
    }
    sideLength/2 + Math.abs(index - (area + sideLength/2))*/


    /* Functional solution (while-loops reformed as recursion)
    @tailrec
    def rec1(area: Int, sideLength: Int, index: Int): Int = {
      if (area >= index) {
        rec2(area, sideLength, index)
      } else {
        rec1(area + sideLength*4 + 8, sideLength + 2, index)
      }
    }
    @tailrec
    def rec2(area: Int, sideLength: Int, index: Int): Int = {
      if (area <= index) {
        sideLength/2 + Math.abs(index - (area + sideLength/2))
      } else {
        rec2(area - sideLength, sideLength, index)
      }
    }
    rec1(1, 0, index)
    */
  }

  def getFirstLargerInCumulativeSpiral(value: Int): Int = {
    val size = 101 // Arbitrary "large enough" size
    val spiral = Array.fill(size,size)(0)
    var x,y = size/2
    spiral(x)(y) = 1
    spiral(x+1)(y) = 1
    x += 1

    def sumSurroundingSquare(): Int = {
      spiral(x-1)(y-1) + spiral(x)(y-1) + spiral(x+1)(y-1) +
      spiral(x-1)(y)           +          spiral(x+1)(y)   +
      spiral(x-1)(y+1) + spiral(x)(y+1) + spiral(x+1)(y+1)
    }

    def fillNextSpiralPosition(dx: Int, dy: Int): Int = {
      x += dx
      y += dy
      spiral(x)(y) = sumSurroundingSquare()
      spiral(x)(y)
    }

    @tailrec
    def fillNextSpiralSide(count: Int, dx: Int, dy: Int): Option[Int] = {
      if (count == 0) {
        None
      } else {
        val ret = fillNextSpiralPosition(dx, dy)
        if (ret > value) {
          Some(ret)
        } else {
          fillNextSpiralSide(count-1, dx, dy)
        }
      }
    }

    /* The annoying way to do the top-level; see below
    @tailrec
    def fillSpiral(sideLength: Int): Int = {
      var ret = fillNextSpiralSide(sideLength-1, 0, -1)
      if (!ret.isEmpty) {
        ret.get
      } else {
        ret = fillNextSpiralSide(sideLength, -1, 0)
        if (!ret.isEmpty) {
          ret.get
        } else {
          ret = fillNextSpiralSide(sideLength, 0, 1)
          if (!ret.isEmpty) {
            ret.get
          } else {
            ret = fillNextSpiralSide(sideLength+1, 1, 0)
            if (!ret.isEmpty) {
              ret.get
            } else {
              fillSpiral(sideLength+2)
            }
          }
        }
      }
    }*/

    /* Syntactic sugar... (uses zero-arg functions -- Scala doesn't support call-by-name with varargs)*/
    @tailrec
    def firstToSatisfy[A,B](values: () => A*)(check: A => Boolean, retFunc: A => B): B = {
      // Invariant: values is guaranteed to include a value that satisfies the check
      val ret = values.head()
      if (check(ret)) {
        retFunc(ret)
      } else {
        firstToSatisfy(values.tail: _*)(check, retFunc)
      }
    }

    def fillSpiral(sideLength: Int): Int = {
      firstToSatisfy(
        () => fillNextSpiralSide(sideLength-1, 0, -1),
        () => fillNextSpiralSide(sideLength, -1, 0),
        () => fillNextSpiralSide(sideLength, 0, 1),
        () => fillNextSpiralSide(sideLength+1, 1, 0),
        () => Some(fillSpiral(sideLength+2)) // We did lose tail recursion though
      )(!_.isEmpty, _.get)
    }

    if (value < 1) 1 else fillSpiral(2)
  }
}
