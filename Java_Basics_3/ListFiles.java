import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.ArrayList;
/*
    SmoothStacks

    name: LetterSearch.java
    contributor: Sam Sessums
    description:
        Simple driver class. requires no CLI. 
        "Write a Java program to get a list of all file/directory names (including in subdirectories) under a given directory."
        compile and run as:
            > java ListFiles.java C:\this\is\a\dir D:\this\is\another\dir
        
        Assumptions
            requires absolute paths for input
            if no path(s) are given as input, cwd will be searched
            if there are paths, each should be separated by spaces
            Some directories or files may point to null and will be skipped over
            

        class: ListFiles
            walk(ArrayList<Path> fileDirCont, Path currentDirectory)
                fileDirCont :- This is the container that will be appended to when walking through directories.
                currentDirectory :- This is the directory to peruse through.
                Recursive depth first search that adds the absolute paths of directories and files.

            ListFiles
                If no absolute paths (args) are used, the cwd will be walked through.
                If absolute paths are provided, they will be walked through and contents will be listed.

*/


public class ListFiles {
    public static ArrayList<Path> walk(ArrayList<Path> files, Path currPath){
        // Getting all files/directories
        for (String f: currPath.toFile().list()){
            Path abs_path = Paths.get(currPath.toAbsolutePath().toString(), f);
            files.add(abs_path); // add file/directory

            // recurse if it is a directory
            if(Files.isDirectory(abs_path)){
                try{
                    files = walk(files, abs_path);
                }

                // Null pointer Exception if directory fails to open, I think
                catch(Exception e){
                    System.err.println(e);
                }
            }
        }
        // when the end of a directory is reached return the files for the next possible recurse
        return files;
    }
    public static void main(String[] args){
        try{
            // no CLI, get contents of cwd
            if (args.length == 0){
                ArrayList<Path> cwdFiles = walk(new ArrayList<Path>(), Paths.get("").toAbsolutePath());
                
                for(Path i: cwdFiles){
                    System.out.println(i);
                }
            }

            // walk directory(s)
            else{
                for(String i: args){
                    Path argDir = Paths.get(i).toAbsolutePath();
                    if (Files.isDirectory(argDir)){
                        ArrayList<Path> argFiles = walk(new ArrayList<Path>(), argDir);
                        for(Path j: argFiles){
                            System.out.println(j);
                        }
                    }
                    // CLI directory does not exist
                    else{
                        System.err.printf("%s is not an existing directory", i);
                    }
                }
            }
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
