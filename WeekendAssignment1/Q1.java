
/*
    SmoothStacks

    name: Q1.java
    contributor: Sam Sessums
    description:

            Question 1
            PerformOperation isOdd(): The lambda expression must return  if a number is odd or  if it is even.
            PerformOperation isPrime(): The lambda expression must return  if a number is prime or  if it is composite.
            PerformOperation isPalindrome(): The lambda expression must return  if a number is a palindrome or  if it is not.
            Input Format

            ***************************NOT PROVIDED***************************
            *Input is handled for you by the locked stub code in your editor.*
            ***************************NOT PROVIDED***************************

        
    *notes:
        

*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.nio.file.Paths;

public class Q1 {
    public static void fileRead(String file){

        // making functional interface local to fileRead(), because why not
        @FunctionalInterface
        interface Operator<T, R> {
        R PerformOperation(T x);
        }

        // Lambda function for odds and evens
        Operator<Integer, String> isOdd = (x) -> {
            return x % 2 != 0 ? "ODD":"EVEN";
        };

        // Lambda function for casting Int to a reverse of its string and checking against the original
        Operator<Integer, String> isPalindrome = (x) -> {
            String revStr = new StringBuilder(x.toString()).reverse().toString();
            return x.toString().equals(revStr) ? "PALINDROME":"NOT PALINDROME";
        };

        // Lambda for converting an Int to a BigInt for probable prime method and checking if it is a prime
        Operator<Integer, String> isPrime = (x) -> {
            return BigInteger.valueOf(x).isProbablePrime(1) ? "PRIME":"COMPOSITE";
        };


        // see if file is valid if not throw an exception
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String str_tests = br.readLine(); // get amount of lines to read in file as per spec
            // was the file empty
            if (str_tests == null){
                throw new IllegalArgumentException("Nothing in file.");
            }

            // was it not just one number 
            if (str_tests.split(" ").length != 1){
                throw new IllegalArgumentException("Number of cases to test is in an invalid format.");
            }
            
            else{
                // maybe the amount of lines to read was not a number.
                int int_tests = Integer.parseInt(str_tests);
                // loop to read the amount of lines 
                for(int i = 0; i < int_tests; i++){
                    String str_case_num = br.readLine(); // get caseId and num to check

                    //turns out there was nothing there
                    if (str_case_num == null){
                        throw new IllegalArgumentException("Case and Num doesn't exist.");
                    }
                    // get caseId and num
                    String[] case_num = str_case_num.split(" ");
                    
                    // more than caseId and num was there
                    if (case_num.length != 2){
                        throw new IllegalArgumentException("Case in an invalid format");
                    }
                    else{
                        // maybe caseId and num aren't numbers
                        int caseId = Integer.parseInt(case_num[0]);
                        int num = Integer.parseInt(case_num[1]);
                        // finally the lambdas get used
                        switch (caseId) {
                            case 1:
                                System.out.println(isOdd.PerformOperation(num));
                                break;
        
                            case 2:
                                System.out.println(isPrime.PerformOperation(num));
                                break;
        
                            case 3:
                                System.out.println(isPalindrome.PerformOperation(num));
                                break;
        
                            default:
                                //The receiving caseId was an int, but not a defined case
                                System.out.println("CaseId not declared");
                        }
                    }
                }
            }
            // closeup the stream
            br.close();
        }
        catch(FileNotFoundException e){
            // Bad file Input
            System.err.println(e);
        }
        catch(NumberFormatException e){
            // Bad parse of int
            System.err.println(e);
        }
        catch(Exception e){
            // Unaccounted Exceptions
            System.err.println(e);
        }
        

    }

    public static void main(String[] args){
        if(args.length == 0){
        System.out.println("No File was found.");
        }

        else{
            for(String i: args){
                String file = Paths.get("", i).toAbsolutePath().toString();
                System.out.println(file);
                fileRead(file);
            }
        }
    }
    
}
