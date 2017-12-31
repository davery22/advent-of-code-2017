package day04

object HighEntropyPassphrasesSolver {
  def countValidPassphrases(input: List[String], noAnagrams: Boolean=false): Int = {
    def checkValidPassphrase(passphrase: String): Boolean = {
      val words = passphrase.split(" ")
      val uniqueWords = if (noAnagrams) Set(words: _*).map(_.sorted) else Set(words: _*)
      words.length == uniqueWords.size
    }

    input.filter(checkValidPassphrase).length
  }
}
