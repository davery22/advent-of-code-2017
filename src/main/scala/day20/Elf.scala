package day20

import christmas.northPole._

object Elf extends Workshop with Presents {
  val input = getResourceLines("input.txt").toList
  println(ParticleSwarmSolver.getParticleClosestToOriginOverTime(input))
  println(ParticleSwarmSolver.countParticlesAfterCollisions(input))
}
