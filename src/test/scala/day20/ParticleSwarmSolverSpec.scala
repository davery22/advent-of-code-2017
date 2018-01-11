package day20

import org.scalatest.{Matchers, FlatSpec}

class ParticleSwarmSolverSpec extends FlatSpec with Matchers {
  import ParticleSwarmSolver._

  "A ParticleSwarmSolver" should "determine which particle will stay closest to the origin over eternity" in {
    getParticleClosestToOriginOverTime(
      List(
        "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>",
        "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>")) should be(0)
  }

  it should "count remaining particles after collisions" in {
    countParticlesAfterCollisions(
      List(
        "p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>",
        "p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>",
        "p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>",
        "p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>")) should be(1)
  }
}
