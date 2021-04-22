
/** Package for a name guessing game Hangman.
 * https://en.wikipedia.org/wiki/Hangman_(game)
 *
 * @author Sandra Selicka and Iveta Kalva
 * Case class implemented in this package is [[gHangman.GameState]].
 * @version 1.0
 */
package gHangman

import scala.io.StdIn.readLine

/** Hangman game logic
 *
 * Checks the players input against a randomly selected word based on
 * the players chosen word length
 */
object Hangman extends App {
  println("Welcome to the word guessing game - Hangman. You have 10 guesses.")

  val file = if (args.isEmpty) "./src/Resources/englishWords.txt" else args(0)
  var guessWords = Utilities.englishWords(file, Utilities.setWordLength())
  var state = GameState()

  while (!state.end) {

    println("********************************************************")
    println("Guess the word or type 'Exit' to leave the game!")

    state.guessingWord = Utilities.randomWord(guessWords)
    guessWords = guessWords.filterNot(_ == state.guessingWord)
    state.splitWord = Utilities.wordSplit(state.guessingWord.toUpperCase)
    state.wordUnderscoreGuess = Utilities.wordSplit("_" * state.guessingWord.length)
    Utilities.resetGameState()

    while (!state.newGame && !state.end) {

      DrawHangman.drawHangman(10 - state.guessCount)

      state.playersInput = readLine(state.formattedInput.format(Utilities.wordJoin(state.wordUnderscoreGuess), state.guessCount)).toUpperCase
      state.playersInput match {
        case "EXIT" => state.end = true
        case "" => Nil
        case _ =>
          state.letter = state.playersInput.toList
          if ((1 < state.letter.length) || !state.letters.contains(state.letter.head)) {
            println("It is not a valid guess ->" + state.playersInput)
          } else if (state.guessSet.contains(state.letter.head)) {
            println("You already guessed this! Try again!")
          } else {
            if (state.splitWord.contains(state.letter.head)) {
              state.wordUnderscoreGuess = Utilities.applyGuess(state.letter.head, state.wordUnderscoreGuess, state.splitWord)
              state.guessCount += 1
            }
            state.guessSet = state.guessSet + state.letter.head
            Utilities.checkGuesses()
          }
      }
    }
  }
  println("Thank you for playing guessing game - Hangman!")
}
