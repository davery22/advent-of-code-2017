package day20

import scala.annotation.tailrec

object ParticleSwarmSolver {
  case class Particle(val coords: Map[Int, List[Int]])

  def parseParticle(particle: String): Particle = {
    val n = "[+-]?\\d+"
    val particlePattern = f"p=<($n,$n,$n)>, v=<($n,$n,$n)>, a=<($n,$n,$n)>".r

    particle match {
      case particlePattern(p,v,a) => Particle(Map((0, p.split(",").map(_.toInt).toList), (1, v.split(",").map(_.toInt).toList), (2, a.split(",").map(_.toInt).toList)))
    }
  }

  private
  def getMDist(particle: Particle, key: Int) = particle.coords(key).map(math.abs).sum

  def getParticleClosestToOriginOverTime(particlesRaw: List[String]): Int = {
    val particles = particlesRaw.map(parseParticle)

    @tailrec
    def getClosest(key: Int, all: List[Particle], ties: List[Particle], best: Option[Int]): Int = all match {
      case Nil => if (ties.length == 1 || key == 0) particles.indexOf(ties.head) else getClosest(key-1, ties, List(), None)
      case head :: _ => if (best.isEmpty || getMDist(head, key) == best.get) {
        getClosest(key, all.tail, head :: ties, Some(getMDist(head, key)))
      } else if (getMDist(head, key) < best.get) {
        getClosest(key, all.tail, List(head), Some(getMDist(head, key)))
      } else {
        getClosest(key, all.tail, ties, best)
      }
    }
    getClosest(2, particles, List(), None)
  }

  /* Sadly, sans a CAS system, the most reasonable approach seems to be brute force -- update for an arbitrary amount of time until collisions seem to be over */
  def countParticlesAfterCollisions(particlesRaw: List[String]): Int = {
    val particles = particlesRaw.map(parseParticle)

    def update(particle: Particle): Particle = {
      val a = particle.coords(2)
      val v = particle.coords(1) zip a map { case (x,y) => x+y }
      val p = particle.coords(0) zip v map { case (x,y) => x+y }
      Particle(Map((0, p), (1, v), (2, a)))
    }

    @tailrec
    def getUncollidedParticles(all: List[Particle], remaining: List[Particle], collisions: Map[List[Int], Boolean]): List[Particle] = remaining match {
      case Nil => all.filter(p => collisions.filter(_._2).contains(p.coords(0)))
      case head :: _ => getUncollidedParticles(all, remaining.tail, collisions.updated(head.coords(0), !collisions.contains(head.coords(0))))
    }

    @tailrec
    def timeStep(particles: List[Particle], roundsSinceCollision: Int): Int = roundsSinceCollision match {
      case 20 /* Long enough? */ => particles.length
      case _ =>
        val updated = particles.map(update)
        val newParticles = getUncollidedParticles(updated, updated, Map())
        timeStep(newParticles, (if (updated.length != newParticles.length) 0 else roundsSinceCollision+1))
    }
    timeStep(particles, 0)
  }
}
