package Hangman

import scala.io.StdIn.readLine
import Hangman.state

object Utilities {

  // got the words here: http://www.gwicks.net/dictionaries.htm (UK English)
  def englishWords(file: String, diff: Int): List[String] = {
    val source = io.Source.fromFile(file)
    val words: List[String] = source.getLines.toList
    source.close()
    if (diff == 1) {
      words.collect { case word if word.length > 3 && word.length < 7 && word.matches("^[a-zA-Z]+$") => word }
    } else if (diff == 2) {
      words.collect { case word if word.length > 6 && word.length < 11 && word.matches("^[a-zA-Z]+$") => word }
    }
    else {
      words.collect { case word if word.length > 10 && word.matches("^[a-zA-Z]+$") => word }
    }
  }

  def setDifficulty(): Int = {
    val difficulty: String = readLine("Enter - 1 for short word, 2 for medium length word, 3 for long word. Good luck! ")
    difficulty.toInt
  }

  // Return a random word
  def randomWord(words: List[String]): String = {
    words(scala.util.Random.nextInt(words.length))
  }

  // Split a word into separate letters
  def wordSplit(word: String): List[Char] = {
    word.toList
  }

  // Join the list of characters together with a space in-between.
  def wordJoin(wordlist: List[Char]): String = {
    wordlist.mkString(" ")
  }

  // Set of upper case letters.
  def alphaSet: Set[Char] = {
    ('A' to 'Z').toSet
  }

  // Generate a new guess list based on letter, current matches and actual word.
  def applyGuess(letter: Char, guesslist: List[Char], hanglist: List[Char]): List[Char] = {
    guesslist.zip(hanglist).map({ case (a, b) => if (letter == b) b else a })
  }

  def printResult(message: String): Unit = {
    println("\t" + wordJoin(state.splitWord) + "\n")
    println(state.summary.format(message, state.wins, state.losses)) //https://docs.scala-lang.org/overviews/core/string-interpolation.html
    //https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Formatter.html#detail
    //https://alvinalexander.com/scala/scala-string-formatting-java-string-format-method/
  }

  def checkGuesses(): Unit = {
    // if the player guesses the word correctly then if versions, else looses a move
    if (state.splitWord == state.wordUnderscoreGuess) {
      state.newGame = true
      state.wins += 1
      Utilities.printResult("Congratulations! You won!")
    } else {
      state.guessCount match {
        case 1 =>
          DrawHangman.drawHangman(10)
          state.newGame = true
          state.losses += 1
          Utilities.printResult("Sorry, but try again! ;)")
        case _ => state.guessCount -= 1
      }
    }
  }

}
