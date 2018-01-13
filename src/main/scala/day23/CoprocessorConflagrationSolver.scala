package day23

import scala.annotation.tailrec

object CoprocessorConflagrationSolver {
  /* I really couldn't be bothered with data type defs this time -- we go directly from regex */

  private
  def resolve(x: String, regs: Map[String, Long]): Long = try { x.toLong } catch { case _: NumberFormatException => regs(x) }

  def countMulInExecution(instructions: List[String]): Int = {
    val Set = "set (\\w+) ([+-]?\\w+)".r
    val Sub = "sub (\\w+) ([+-]?\\w+)".r
    val Mul = "mul (\\w+) ([+-]?\\w+)".r
    val Jnz = "jnz ([+-]?\\w+) ([+-]?\\w+)".r
    
    @tailrec
    def exec(pc: Int, regs: Map[String, Long], mulCount: Int): Int = {
      if (pc < 0 || pc >= instructions.length) {
        mulCount
      } else instructions(pc) match {
        case Set(x,y) => exec(pc+1, regs+((x, resolve(y,regs))), mulCount)
        case Sub(x,y) => exec(pc+1, regs+((x, regs(x)-resolve(y,regs))), mulCount)
        case Mul(x,y) => exec(pc+1, regs+((x, regs(x)*resolve(y,regs))), mulCount+1)
        case Jnz(x,y) => exec(pc+(if (resolve(x,regs)!=0) resolve(y,regs).toInt else 1), regs, mulCount)
      }
    }
    exec(0, Map().withDefaultValue(0), 0)
  }

  /* A reduction of the input program */
  def countComposites(start: Int, end: Int, skip: Int=1): Int = {
    def isComposite(x: Int) = (2 to math.sqrt(x).toInt) exists (x % _ == 0)
    (start to end by skip).filter(isComposite).length
  }
}
