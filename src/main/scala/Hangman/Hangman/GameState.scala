package Hangman

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
