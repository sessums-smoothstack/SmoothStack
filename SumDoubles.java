import java.util.NoSuchElementException;

/*
    SmoothStacks

    name: SumDoubles.java
    contributor: Sam Sessums
    description:
        Simple driver class. requires no CLI. 
        "Take multiple values from the command line and show the result of adding all of them."
        
        Assumptions
            The mutlitple values are entered one at a time and not as a list with a delimiter.
            The numbers are of type double or int (this may be changed in future updates).
            Bad values should not crash the program.
            If the input is empty (i.e. the user enters nothing) then the program will terminate and display the sum.

        class: SumDoubles
        
        SumDoubles.main()
            Asks for numbers and continously adds them until system interrupt or user wants the sum.

        *notes:
            N/A

*/

public class SumDoubles {
    public static void main(String[] args){
        try{
            java.util.Scanner userInput = new java.util.Scanner(System.in);
            double sum = 0;
            boolean flag = false;
            String[] input;

            while(!flag){
                try{
                    System.out.printf("Enter number to sum: ");
                    input = userInput.nextLine().split(" "); // reading nums 1 by 1, not many at once

                    // more than one "number" for input
                    if (input.length > 1){
                        throw new IllegalArgumentException();
                    }

                    // extract "number"
                    else if (input.length == 1){
                        // user entered nothin and is done wants the sum
                        if (input[0].equals("")){
                            flag = true;
                        }
                        else{
                            try{
                                sum += Double.parseDouble(input[0]);
                            }
                            // possibly adding illegal characters in number
                            catch(NumberFormatException e){
                                System.out.println("Please enter an integer");
                            }
                        }
                    }
                }
                // "Ctrl+C" system interrupt
                catch(NoSuchElementException e){
                    System.out.println();
                    break;
                }

                // bad values not numbers
                catch(IllegalArgumentException e){
                    System.out.println("Please enter an integer");
                }   
            }

            // If the user terminates the loop by entering nothing
            if (flag)
                System.out.printf("The sum is: %f", sum);
            
            // If the user hits "Ctrl+C"
            else
                System.out.println("Exitting!");
            
            // Closing up the scanner
            userInput.close();
        }

        catch (Exception e){
            System.err.println(e);
        }
    }
}
