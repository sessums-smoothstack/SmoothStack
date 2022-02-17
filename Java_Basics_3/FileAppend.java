/*
    SmoothStacks

    name: FileAppend.java
    contributor: Sam Sessums
    description:
        Simple driver class. 
        // Write a Java program to append text to an existing file.
        //uses relative path 
        
        Assumptions
            

        class: FileAppend
            FileAppend.FileAppender
                FileAppender(String fileName) - Constructor
                    fileName :- String of the relative or absolute path, make sure you know where this script is running.

                FileAppender(File filename) - Constructor
                    fileName :- File of the relative or absolute path, make sure you know where this script is running.

                toString() - Method
                    returns the filename that the FileAppender is appending to as a String.

                Append(String data) - Method
                    data :- String data to write to the file
                    Appends the string data to the file

        FileAppend.main()
            Uses CLI input as file input and appends a simple string to file.
            If there is no file, nothing happens.

        *notes:
            Will only output String data to file.
            Ideally, anything can be appended to the files contents.

*/

import java.io.*;

public class FileAppend {

    public static class FileAppender {
        // easy access to append to saved file
        String str_filename = null;
        File file_filename = null;
        
        // constructor with string file
        FileAppender(String filename){
            str_filename = filename;
            file_filename = new File(filename);
        }

        // constructor with File instead of String
        FileAppender(File filename){
            file_filename = filename;
            str_filename = filename.toString();
        }

        // returns the current file that is saved
        public String toString(){
            return str_filename;
        }

        // Appends String to the end of the file
        public void Append(String data) throws IOException{
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file_filename, true)));
            out.write(data, 0, data.length());
            out.close();
        }

    }
    
    // Driver for "appending some information" to the file if it exists.
    public static void main(String[] args){
        try{
            if (args.length == 0){
                System.out.println("No file input given.");
            }

            else{
                for(String i: args){
                    File appendFile = new File(i);
                    if(appendFile.exists()){
                        FileAppender logging = new FileAppender(i);
                        logging.Append("Appending some information\n");
                    }
                    else{
                        System.out.printf("%s doesn't exist");
                    }
                }
            }
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
}
