package day04

import org.scalatest.{Matchers, FlatSpec}

class HighEntropyPassphrasesSolverSpec extends FlatSpec with Matchers {
  import HighEntropyPassphrasesSolver._

  "A HighEntropyPassphrasesSolver" should "count valid passphrases" in {
    countValidPassphrases(List("hello world", "still valid here", "also a valid passphrase")) should be(3)
    countValidPassphrases(List("hello world", "this is a passphrase", "a passphrase with duplicate words is not a valid passphrase")) should be(2)
    countValidPassphrases(List("no go no no", "this is not not not valid", "duplicate duplicate")) should be(0)
  }

  it should "count valid passphrases with no anagrams" in {
    countValidPassphrases(List("hello world", "still valid here", "also a valid passphrase"), noAnagrams=true) should be(3)
    countValidPassphrases(List("hello world", "this is a passphrase", "a passphrase with duplicate words is not a valid passphrase"), noAnagrams=true) should be(2)
    countValidPassphrases(List("listen is an anagrams of silent", "angel and angle will not work", "but this is fine"), noAnagrams=true) should be(1)
  }
}
