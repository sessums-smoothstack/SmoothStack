/*
    SmoothStacks

    name: Array2D.java
    contributor: Sam Sessums
    description:
        Simple driver class. requires no CLI. 
        "Construct a 2D array and find the max number and show its position in the array."
        
        Assumptions
            2D array is meant for integers.
            If another element equals the current max, this will override the current max

        class: Array2D
        
        Array2D.main()
            Builds a 2D array of random sizes and inserts random ints from 0 to 999999
            Searches to find the max value and its indices.

        *notes:
            It is noted that I could have just found the max element and indices while building the array;
            however, I think that defeats the purpose of this assignment.
*/

public class Array2D{
    public static void main(String[] args){
        try{
            java.util.Random intGen = new java.util.Random();
            int[] dims = {intGen.nextInt(99)+2, intGen.nextInt(99)+2}; // minimum range for rows & columns 2 - 100 
            int[][] arr2D = new int[dims[0]][dims[1]];

            System.out.printf("Created a 2D array with %d rows and %d columns\n", dims[0], dims[1]);

            // To assert that the search found the insert max
            int insert_max = 0;
            int[] insert_coords = {0, 0};

            // inserting random values to fill the 2D array
            for(int i = 0; i < dims[0]; i++){
                for(int j = 0; j < dims[1]; j++){
                    arr2D[i][j] = intGen.nextInt(1000000);
                    if (i == 0 && j == 0){
                        insert_max = arr2D[i][j];
                    }
                    else{
                        if (arr2D[i][j] >= insert_max){
                            insert_max = arr2D[i][j];
                            insert_coords[0] = i;
                            insert_coords[1] = j;
                        }
                    }

                }
            }

            int max = arr2D[0][0]; // setting the max to the 0th column of the 0th row
            int[] coords = {0, 0}; // indices for row and column respectively 

            // actually search for the max and the max's position
            for(int i = 0; i < dims[0]; i++){
                for(int j = 0; j < dims[1]; j++){
                    if (arr2D[i][j] >= max){
                        max = arr2D[i][j];
                        coords[0] = i;
                        coords[1] = j;
                    }
                }
            }

            
            assert(insert_max == max);
            assert(insert_coords[0] == coords[0] && insert_coords[1] == coords[1]);

            System.out.printf("The max integer is : %d @ indices [%d][%d]\n", max, coords[0], coords[1]);
        }
        // ideally this would not be entered unless memory can not be allocated or
        // assertions fails
        catch (Exception e){
            System.err.println(e);
        }   
    }
}

