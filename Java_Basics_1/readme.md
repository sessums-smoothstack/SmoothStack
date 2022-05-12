## assignment_1.java
### description:
* simple driver class
* class: assignment_1
<br>
<br>
* assignment_1.main()
  * Prints four triangles in both the left and center alignments and in both up and down orientations.
<br>
<br>
* assignment_1.triangle(String orientation, String alignment)
  * prints the respective triangle.
  * orientation :- "up" or "u" for the up orientation || "down" or "d" for the down orientation.   
  * alignment :-  "left" or "l" for a left aligned triangle || "center" or "c" for a centered triangle.   
<br>
<br>
* notes
  * The bases of the triangles have been hard coded as it seems to not represent a clear pattern within the loop.

## assignment_2.java
### description:
 * simple driver class to play a guessing game, where the user tries to guess a number 1 to 100 inclusive.
Can use CLI with single integer argument for number of guesses. Defaults to 5 guesses.
 * class: assignment_2
 <br>
 <br>
 * assignment_2.main()
   * CLI checks, game loop, ending message
<br>
<br>
 * assignment_2.play(int guesses)
   * Gets a random number for user to guess and uses "guesses" parameter to control the maximum loop length.
   * If either the amount of guesses is used up or the user guesses within the delta range
   the loop is terminated.
   
   * guesses :- Any natural number, but no hard upper bound is set.
<br>
<br>     
 * notes:
   * Bad valued or incorrect formats of guesses won't count towards the users limit of guesses.
