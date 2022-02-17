
import java.nio.file.*;


/*
    SmoothStacks

    name: LetterSearch.java
    contributor: Sam Sessums
    description:
        Simple driver program. Requires at least one file letter pair combination
        "Write a Java program that counts the number of times a particular character, such as 'e', appears in a file.
         The character can be specified at the command line."
         Compile and run as:
         > java LetterSearch.java C:/dir/to/text/file.txt=e
        
        Assumptions
            Charset would be utf-8.
            CLI input can have multiple args, but consider where the jvm thinks the cwd is.
            Folder naming conventions dont use spaces.

        class: LetterSearch
        
            search(String file, char letter)
                file :- String for absolute or relative path to where this is running
                letter :- char search and count for in text file
                Searches and counts file for a single char.

            main()
                Can read multiple files for a single char.


*/

public class LetterSearch {
    public static int search(String file, char c){
        int c_count = 0; // character count

        //open file and read loop
        try(java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))){

            int chr;
            // reading one char at a time to find the letter and increment
            while((chr = br.read()) != -1){
                if (c == chr)
                    c_count++;
            }
            br.close();
            return c_count;
        }
        catch (java.io.FileNotFoundException e){
            System.err.printf("\"%s\" Not Found.\n", file);
        }
        catch (Exception e){
            System.err.println(e);
        }

        // compiler was mad this wasn't here
        // In case of caught exceptions this will return 0
        return c_count;
    }

    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("No File Input!");
        }
        else{
            // for multiple files to check
            for(String i: args){
                try{
                    // Getting the file path and letter to search for
                    String[] fileLetterPair = java.nio.file.Paths.get("", i).toAbsolutePath().toString().split("=");
            
                    if (fileLetterPair.length < 1){
                        throw new IllegalArgumentException("No letter to search for");
                    }
                    else if( fileLetterPair.length > 2){
                        throw new IllegalArgumentException("Bad format");
                    }
                    else{
                        if(fileLetterPair[1].length() > 1){
                            throw new IllegalArgumentException("Only one char can be searched a time");
                        }
                        else{
                            // find the letter in the text file
                            System.out.printf("%d %s's found in %s",
                                    search(fileLetterPair[0],
                                    fileLetterPair[1].charAt(0)),
                                    fileLetterPair[1],
                                    fileLetterPair[0]
                            );
                        }
                    }
                    
                }
                catch (Exception e){
                    System.err.println(e);
                }
            }
        }
    }
    
}
