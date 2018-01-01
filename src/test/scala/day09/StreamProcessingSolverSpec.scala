package day09

import org.scalatest.{Matchers, FlatSpec}

class StreamProcessingSolverSpec extends FlatSpec with Matchers {
  import StreamProcessingSolver._

  "A StreamProcessingSolver" should "compute the total score for all groups, and count garbage characters" in {
    getTotalScore("{}") should be((1,0))
    getTotalScore("{{{}}}") should be((6,0))
    getTotalScore("{{},{}}") should be((5,0))
    getTotalScore("{{{},{},{{}}}}") should be((16,0))
    getTotalScore("{<a>,<a>,<a>,<a>}") should be((1,4))
    getTotalScore("{{<ab>},{<ab>},{<ab>},{<ab>}}") should be((9,8))
    getTotalScore("{{<!!>},{<!!>},{<!!>},{<!!>}}") should be((9,0))
    getTotalScore("{{<a!>},{<a!>},{<a!>},{<ab>}}") should be((3,17))
  }
}
