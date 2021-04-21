package Hangman.Hangman

/** Hangman game parameters used in the main game logic [[Hangman]]
 *
 * @param letterSet letters to guess
 * @param formattedPlayerInput formatted message displayed for players input
 * @param sum formatted summary message of the players score
 * @param endGame ending of the game
 * @param win wins of the player
 * @param loss losses of the player
 * @param setOfGuesses set of players guesses
 * @param guesses allowed guesses for the word
 */
case class GameState(letterSet: Set[Char] = Utilities.alphaSet,
                     formattedPlayerInput: String = "\t%s [Guesses left: %2d ] Letter: ",
                     sum: String = "%s Wins: %2d  Losses: %2d",
                     var endGame: Boolean = false,
                     var win: Int = 0,
                     var loss: Int = 0,
                     setOfGuesses: Set[Char] = Set(),
                     guesses: Int = 10) {
  val letters: Set[Char] =letterSet
  val formattedInput: String = formattedPlayerInput
  val summary: String = sum
  var end: Boolean = endGame
  var wins: Int = win
  var losses: Int = loss
  var guessSet: Set[Char] = setOfGuesses
  var guessCount: Int = guesses
  var playersInput : String = ""
  var splitWord : List[Char] = List()
  var wordUnderscoreGuess : List[Char] = List()
  var guessingWord : String = ""
  var newGame = false
  var letter: List[Char] = List()

}
