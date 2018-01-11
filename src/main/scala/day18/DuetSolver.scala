package day18

import scala.annotation.tailrec

object DuetSolver {
  trait Instruction
  case class Snd(val x: String) extends Instruction
  case class Rcv(val x: String) extends Instruction
  case class Set(val x: String, val y: String) extends Instruction
  case class Add(val x: String, val y: String) extends Instruction
  case class Mul(val x: String, val y: String) extends Instruction
  case class Mod(val x: String, val y: String) extends Instruction
  case class Jgz(val x: String, val y: String) extends Instruction

  def parseInstruction(instruction: String): Instruction = {
    val sndPattern = "snd ([+-]?\\w+)".r
    val rcvPattern = "rcv ([+-]?\\w+)".r
    val setPattern = "set (\\w+) ([+-]?\\w+)".r
    val addPattern = "add (\\w+) ([+-]?\\w+)".r
    val mulPattern = "mul (\\w+) ([+-]?\\w+)".r
    val modPattern = "mod (\\w+) ([+-]?\\w+)".r
    val jgzPattern = "jgz (\\w+) ([+-]?\\w+)".r

    instruction match {
      case sndPattern(value) => Snd(value)
      case rcvPattern(value) => Rcv(value)
      case setPattern(reg, value) => Set(reg, value)
      case addPattern(reg, value) => Add(reg, value)
      case mulPattern(reg, value) => Mul(reg, value)
      case modPattern(reg, value) => Mod(reg, value)
      case jgzPattern(reg, value) => Jgz(reg, value)
    }
  }

  private
  def resolve(x: String, registers: Map[String, Long]): Long = try { x.toLong } catch { case _: NumberFormatException => registers(x) }

  def getFirstRecoveredFrequency(instructions: List[String]): Long = {
    def execTilRecovery(program: List[Instruction], registers: Map[String, Long], pc: Int): Option[Long] = {
      if (pc < 0 || pc >= program.length) {
        None
      } else program(pc) match {
        case Snd(x) => execTilRecovery(program, registers.updated("snd", resolve(x, registers)), pc+1)
        case Rcv(x) => (if (resolve(x, registers) != 0) Some(registers("snd")) else execTilRecovery(program, registers, pc+1))
        case Set(x,y) => execTilRecovery(program, registers.updated(x, resolve(y, registers)), pc+1)
        case Add(x,y) => execTilRecovery(program, registers.updated(x, registers(x) + resolve(y, registers)), pc+1)
        case Mul(x,y) => execTilRecovery(program, registers.updated(x, registers(x) * resolve(y, registers)), pc+1)
        case Mod(x,y) => execTilRecovery(program, registers.updated(x, registers(x) % resolve(y, registers)), pc+1)
        case Jgz(x,y) => execTilRecovery(program, registers, (if (resolve(x, registers) > 0) pc + resolve(y, registers).toInt else pc+1))
      }
    }
    execTilRecovery(instructions.map(parseInstruction), Map().withDefaultValue(0), 0).get
  }

  /* Not my favorite solution, but would you believe I got it first try? I wonder how much setup it would take to do this (concurrency) proper, but still functional style... */
  def getProg1SendCount(instructions: List[String]): Int = {
    def dualExec(program: List[Instruction], pidA: Int, registers: List[Map[String, Long]], pc: List[Int], buf: List[List[Long]], p1Sends: Int): Int = {
      val pidB = 1-pidA // Assumption: pids are 0,1
      if (pc(pidA) < 0 || pc(pidA) >= program.length) {
        if (pc(pidB) < 0 || pc(pidB) >= program.length) { // Both jumped out of execution (halted)
          p1Sends
        } else {
          dualExec(program, pidB, registers, pc, buf, p1Sends)
        }
      } else program(pc(pidA)) match {
        case Snd(x) => dualExec(
          program, pidA, registers,
          pc.updated(pidA, pc(pidA) + 1),
          buf.updated(pidB, buf(pidB) :+ resolve(x, registers(pidA))),
          (if (pidA == 1) p1Sends + 1 else p1Sends))
        case Rcv(x) => buf(pidA) match {
          case Nil => {
            if (pc(pidB) < 0 || pc(pidB) >= program.length || (program(pc(pidB)).isInstanceOf[Rcv] && buf(pidB).isEmpty)) { // Either locked and halted, or deadlock
              p1Sends
            } else {
              dualExec(program, pidB, registers, pc, buf, p1Sends)
            }
          }
          case item :: _ => dualExec(
            program, pidA,
            registers.updated(pidA, registers(pidA).updated(x, item)),
            pc.updated(pidA, pc(pidA) + 1),
            buf.updated(pidA, buf(pidA).tail),
            p1Sends)
        }
        case Set(x,y) => dualExec(
          program, pidA,
          registers.updated(pidA, registers(pidA).updated(x, resolve(y, registers(pidA)))),
          pc.updated(pidA, pc(pidA) + 1),
          buf, p1Sends)
        case Add(x,y) => dualExec(
          program, pidA,
          registers.updated(pidA, registers(pidA).updated(x, registers(pidA)(x) + resolve(y, registers(pidA)))),
          pc.updated(pidA, pc(pidA) + 1),
          buf, p1Sends)
        case Mul(x,y) => dualExec(
          program, pidA,
          registers.updated(pidA, registers(pidA).updated(x, registers(pidA)(x) * resolve(y, registers(pidA)))),
          pc.updated(pidA, pc(pidA) + 1),
          buf, p1Sends)
        case Mod(x,y) => dualExec(
          program, pidA,
          registers.updated(pidA, registers(pidA).updated(x, registers(pidA)(x) % resolve(y, registers(pidA)))),
          pc.updated(pidA, pc(pidA) + 1),
          buf, p1Sends)
        case Jgz(x,y) => dualExec(
          program, pidA, registers,
          pc.updated(pidA, (if (resolve(x, registers(pidA)) > 0) pc(pidA) + resolve(y, registers(pidA)).toInt else pc(pidA) + 1)),
          buf, p1Sends)
      }
    }
    dualExec(
      instructions.map(parseInstruction),
      0,
      List(Map(("p", 0L)).withDefaultValue(0L), Map(("p", 1L)).withDefaultValue(0L)),
      List(0,0),
      List(List(), List()),
      0)
  }
}
