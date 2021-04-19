import Utilities.setDifficulty

import scala.io.StdIn.readLine

object Hangman extends App {

println("Welcome to the word guessing game - Hangman. You have 10 guesses.")

  val file = if (args.isEmpty) "./src/Resources/englishWords.txt" else args(0)
  var guessWords = Utilities.englishWords(file, setDifficulty())

  //guessWords.foreach(println)

  //TODO maybe put them in a class?
  val letters: Set[Char] = Utilities.alphaSet
  val formattedPlayerInput = "\t%s [Guesses Left: %2d ] Letter: "
  val summary = "%s Wins : %2d  Losses : %2d"

  var endGame = false
  var win = 0
  var loss = 0

  while (!endGame){

    println("Type 'Exit' to leave the game, 'New' for a new game!")

    val guessingWord = Utilities.randomWord(guessWords)
    guessWords = guessWords.filterNot(_ == guessingWord)
    val splitWord = Utilities.wordSplit(guessingWord.toUpperCase)
    var wordUnderscoreGuess = Utilities.wordSplit("_" * guessingWord.length) //shows the length of the word with underscores
    var guessSet : Set[Char] = Set()
    var guessCount = 10

    var newGame = false
    while (!newGame && !endGame) {
      def checkGuesses(): Unit = {

        def printResult(message: String): Unit = {
          println("\t" + Utilities.wordJoin(splitWord) + "\n")
          println(summary.format(message, win, loss)) //https://docs.scala-lang.org/overviews/core/string-interpolation.html
          //https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Formatter.html#detail
          //https://alvinalexander.com/scala/scala-string-formatting-java-string-format-method/
        }
  // if the player guesses the word correctly then if versions, else looses a move
        if (splitWord == wordUnderscoreGuess) {
          newGame = true
          win += 1
          printResult("Congratulations! You won!")
        } else {
          guessCount match {
            case 1 =>
              newGame = true
              loss += 1
              printResult("Sorry, but try again! ;)")
            case _ => guessCount -= 1
          }
        }
      }

      val playersInput = readLine(formattedPlayerInput.format(Utilities.wordJoin(wordUnderscoreGuess), guessCount)).toUpperCase
      playersInput match {
        case "NEW" => guessCount = 1 ; checkGuesses()
        case "EXIT" => endGame = true
        case "" => Nil
        case _ =>
          val letter : List[Char] = playersInput.toList
          if ((1 < letter.length) || !letters.contains(letter.head)) {
            println("It is not a valid guess ->" + playersInput)
          } else if (guessSet.contains(letter.head)) {
            println ("You already guessed this! Try again!")
          } else {
            if (splitWord.contains(letter.head)) {
              wordUnderscoreGuess = Utilities.applyGuess(letter.head, wordUnderscoreGuess, splitWord)
              guessCount += 1
            }
            guessSet = guessSet + letter.head
            checkGuesses()
          }
      }
    }
  }
  println("Thank you for playing guessing game - Hangman!")
}
