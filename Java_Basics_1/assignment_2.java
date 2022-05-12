import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/*
    SmoothStacks

    name: assignment_2.java
    contributor: Sam Sessums
    description:
        simple driver class to play a guessing game, where the user tries to guess a number 1 to 100 inclusive.
        Can use CLI with single integer argument for number of guesses. Defaults to 5 guesses.

        class: assignment_2
        
        assignment_2.main()
            CLI checks, game loop, ending message
        
        assignment_2.play(int guesses)
            Gets a random number for user to guess and uses "guesses" parameter to control the maximum loop length.
            If either the amount of guesses is used up or the user guesses within the delta range
            the loop is terminated.

            guesses :- Any natural number, but no hard upper bound is set.
        
        *notes:
            Bad valued or incorrect formats of guesses won't count towards the users limit of guesses.

*/

public class assignment_2 {
    public static void main(String[] args){

        try{
            // default amount of guesses
            int guesses = 5;

            // assessing if CLI was given
            if (args.length == 1){
                try{
                    guesses = Integer.parseInt(args[0]); // could be a bad int
                    if (guesses <= 0){
                        guesses = 5;
                        throw new IllegalArgumentException("Number of guesses has to be greater than zero.\n");
                        
                    }
                }
                catch (Exception e){
                    System.out.println("Bad type for CLI guess limit. Defaulting to 5 guesses.");
                    System.err.println(e);
                }
            }
            // too many CLI arguments
            else if (args.length > 1){
                //System.err.println("here");
                throw new IllegalArgumentException("CLI argument must be one integer for limit on guesses.");
            }

            // Game intro
            System.out.println("You've got " + guesses + " guesses to guess a number between 1 and 100 inclusive.");

            // Game loop
            int[] stats = play(guesses);

            // Checking if the user was correct and revealing the answer
            if(stats[0] == 1){
                System.out.println("you've won the number was " + stats[1]);
            }
            else if (stats[0] == 0){
                System.out.println("Sorry, the number was " + stats[1]);
            }
        }
        catch (Exception e){
            System.err.println(e);
            System.out.println("Exitting!");
        }

    }
    
    public static int[] play(int numOfguesses){
        java.util.Random random = new java.util.Random();
        int answer =  random.nextInt(100) + 1;
        java.util.Scanner userInput = new java.util.Scanner(System.in);
        int[] deltas = {answer-10, answer+10};
        boolean win = false;
        boolean gotGuess = false;
        int turns = 0;
        int int_guess = 0;
        String[] str_guess;
        do{
            do{
                try{
                    System.out.printf("Guess: ");
                    str_guess = userInput.nextLine().split(" ");
                    
                    if(str_guess.length > 1){
                        throw new InputMismatchException();
                    }

                    int_guess = Integer.parseInt(str_guess[0]);

                    if (int_guess <= 0 || int_guess > 100){
                        throw new IllegalArgumentException();
                    }
                    else{
                        gotGuess = true;
                        turns++;
                    }
                }
                catch (InputMismatchException e){
                    System.out.println("Try a number between 1 - 100 inclusive.");
                }
                catch (NumberFormatException e){
                    System.out.println("Try a number between 1 - 100 inclusive.");
                }
                catch (IllegalArgumentException e){
                    System.out.println("Try a number between 1 - 100 inclusive.");
                }
                catch (NoSuchElementException e){
                    // catch for system interrupt
                    userInput.close(); //close up stream before scope ends
                    throw new NoSuchElementException();
                }

            }while(!gotGuess);

            
            if (int_guess >= deltas[0] && int_guess <= deltas[1]){
                win = true;
            }
            else if (turns == numOfguesses){
                break;
            }
            else{
                gotGuess = false;
            }
        }while(turns < numOfguesses && !win);

        userInput.close();
        int win_int = win ? 1:0;
        int[] stats = {win_int, answer};
        
        return stats;
    }
}
