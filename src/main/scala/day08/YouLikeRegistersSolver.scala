package day08

import scala.annotation.tailrec

object YouLikeRegistersSolver {
  def parseInstruction(instruction: String, registers: Map[String, Int]): (String, Int) = {
    // Matches:
    //   b inc 5 if a > 1
    //   c dec -10 if b >= 1
    val instructionPattern = "(\\w+) (inc|dec) (-?\\d+) if (\\w+) (>=|<=|==|!=|>|<) (-?\\d+)".r

    instruction match {
      case instructionPattern(r1, op, amount, r2, ineq, bound) => {
        val inc = op match {
          case "inc" => amount.toInt
          case "dec" => -amount.toInt
        }
        val passed = ineq match {
          case ">=" => registers(r2) >= bound.toInt
          case "<=" => registers(r2) <= bound.toInt
          case "==" => registers(r2) == bound.toInt
          case "!=" => registers(r2) != bound.toInt
          case ">" => registers(r2) > bound.toInt
          case "<" => registers(r2) < bound.toInt
        }
        (r1, registers(r1) + (if (passed) inc else 0))
      }
    }
  }

  def getLargestValueAfterInstructions(instructions: List[String]): (Int, Int) = {
    @tailrec
    def execInstructions(instructions: List[String], registers: Map[String, Int], maxSoFar: Int): (Int, Int) = {
      if (instructions.isEmpty) {
        (registers.maxBy(_._2)._2, maxSoFar)
      } else {
        val kv = parseInstruction(instructions.head, registers)
        val newMax = if ((kv._2) > maxSoFar) kv._2 else maxSoFar 
        execInstructions(instructions.tail, registers.updated(kv._1, kv._2), newMax)
      }
    }
    execInstructions(instructions, Map().withDefaultValue(0), 0)
  }
}
