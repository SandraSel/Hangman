package gHangman

import gHangman.Hangman.state

import scala.io.StdIn.readLine

/** Utilities object holding methods for [[Hangman]]
 *
 * includes all the functions used in the Hangman game
 */
object Utilities {
  /** Retrieves guessable words from an added file
   *
   * @param file used to retrieve words for the word guessing game
   *             file retrieved from http://www.gwicks.net/dictionaries.htm (UK English)
   * @param diff determines the lengths of the words in the returned word list
   * @return word lists to be guessed by player that is filtered by the word length
   *         also chosen by the player
   */

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

  /** Set the length of the guess word by letters count
   *
   * @return a word chosen by the player based on preferred word length
   */
  def setWordLength(): Int = {
    readLine("Enter - 1 for short word, 2 for medium length word, 3 for long word. Good luck! ").toInt
  }

  /** Generates a random word from a list of words
   *
   * @param words is a list of words
   * @return a random word for the game to be guessed
   */
  def randomWord(words: List[String]): String = {
    words(scala.util.Random.nextInt(words.length))
  }

  /** Method that splits a word into separate letters
   *
   * @param word - a word that is to be guessed
   * @return a word split into separate letters
   */
  // Split a word into separate letters
  def wordSplit(word: String): List[Char] = {
    word.toList
  }

  /** Joins the separated word letters with spaces in-between
   *
   * @param wordlist a word whose letters are separated into a list
   * @return word whose separated letters have been joined again into a string by a space
   */
  def wordJoin(wordlist: List[Char]): String = {
    wordlist.mkString(" ")
  }

  /** Set of upper case letters
   *
   * @return a set of upper case letters
   */
  def alphaSet: Set[Char] = {
    ('A' to 'Z').toSet
  }

  /** Method to reset the game
   *
   */
  def resetGameState(): Unit = {
    state.guessCount = 10
    state.guessSet = Set()
    state.newGame = false
  }

  /** Generate a new guess list based on letter, current matches and actual word
   *
   * @param letter    letters that have been guessed
   * @param guesslist shows the word in underscore containing matched letters
   * @param hanglist  the actual guessed word
   * @return a split word in underscores or with guessed letters
   */
  def applyGuess(letter: Char, guesslist: List[Char], hanglist: List[Char]): List[Char] = {
    guesslist.zip(hanglist).map({ case (a, b) => if (letter == b) b else a })
  }

  /** Method to format the end game messages displayed to the player
   *
   * @param message to the player about the guessed word and score of previous word guesses
   */
  def printResult(message: String): Unit = {
    println("\t" + wordJoin(state.splitWord) + "\n")
    println(state.summary.format(message, state.wins, state.losses))
  }

  /** Checking the players guessed letters against the word that is chosen for the game
   *
   * if the player guesses the word correctly then wins, else looses a move
   */
  def checkGuesses(): Unit = {
    if (state.splitWord == state.wordUnderscoreGuess) {
      state.newGame = true
      state.wins += 1
      printResult("Congratulations! You won!")
    } else {
      state.guessCount match {
        case 1 =>
          DrawHangman.drawHangman(10)
          state.newGame = true
          state.losses += 1
          printResult("Sorry, but try again! ;)")
        case _ => state.guessCount -= 1
      }
    }
  }
}
