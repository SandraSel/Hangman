import scala.io.StdIn.readLine

object Utilities extends App {

  // got the words here: http://www.gwicks.net/dictionaries.htm (UK English)
  def englishWords(file: String, diff : Int) : List[String] = {
    val source = io.Source.fromFile(file)
    val words: List[String] = source.getLines.toList
    source.close()
    if (diff == 0) {
      words.collect { case word if word.length >3 && word.length <7 && word.matches("^[a-zA-Z]+$") => word }
    } else if (diff == 1) {
      words.collect { case word if word.length >6 && word.length <11 && word.matches("^[a-zA-Z]+$") => word }
    }
    else {
      words.collect { case word if word.length >10 && word.matches("^[a-zA-Z]+$") => word }
    }
  }

  def setDifficulty() : Int = {
    val difficulty : String = readLine("Enter - 0 for short word, 1 for medium length word, 2 for long word ")
    difficulty.toInt
  }

  // Return a random word
  def randomWord(words : List[String]) : String = {
    words(scala.util.Random.nextInt(words.length) )
  }

  // Split a word into separate letters
  def wordSplit(word : String) : List[Char] = {
    word.toList
  }

  // Join the list of characters together with a space in-between.
  def wordJoin(wordlist : List[Char]) : String = {
    wordlist.mkString(" ")
    //other option: wordlist.mkString(" ").toUpperCase
  }

  // Set of upper case letters.
   def alphaSet : Set[Char] = {
    ('A' to 'Z').toSet
  }

  // Generate a new guess list based on letter, current matches and actual word.
  def applyGuess(letter : Char, guesslist : List[Char], hanglist : List[Char]) : List[Char] = {
    guesslist.zip(hanglist).map({case(g,h) => if (letter == h) h else g})
  }
}
