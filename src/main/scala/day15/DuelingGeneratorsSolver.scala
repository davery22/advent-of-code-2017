package day15

object DuelingGeneratorsSolver {
  case class Generator(var currentEl: Int, val update: Int => Int) extends Iterator[Int] {
    def hasNext = true
    def next() = { currentEl = update(currentEl); currentEl }
  }

  def matches(a: Int, b: Int): Boolean = (a & 0xffff) == (b & 0xffff)

  def getMatchCount(aSeed: Int, bSeed: Int, count: Int=40000000): Int = {
    val genA = Generator(aSeed, x => ((16807L * x) % 2147483647).toInt)
    val genB = Generator(bSeed, x => ((48271L * x) % 2147483647).toInt)
    (genA zip genB).take(count).filter{ case (a,b) => matches(a,b) }.length 
  }

  def getPickyMatchCount(aSeed: Int, bSeed: Int, count: Int=5000000): Int = {
    val genA = Generator(aSeed, x => ((16807L * x) % 2147483647).toInt)
    val genB = Generator(bSeed, x => ((48271L * x) % 2147483647).toInt)
    val genAPicks = for (x <- genA if x % 4 == 0) yield x
    val genBPicks = for (x <- genB if x % 8 == 0) yield x
    (genAPicks zip genBPicks).take(count).filter{ case (a,b) => matches(a,b) }.length 
  }
}
