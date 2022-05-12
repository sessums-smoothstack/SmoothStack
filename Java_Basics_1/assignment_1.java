/*
    SmoothStacks

    name: assignment_1.java
    contributor: Sam Sessums
    description:
        simple driver class

        class: assignment_1
        
        assignment_1.main()
            Prints four triangles in both the left and center alignments and in both up and down orientations. 
        
        assignment_1.triangle(String orientation, String alignment) - prints the respective triangle.

            orientation :- "up" or "u" for the up orientation || "down" or "d" for the down orientation.
            
            alignment :-  "left" or "l" for a left aligned triangle || "center" or "c" for a centered triangle.
        
        *notes
            The bases of the triangles have been hard coded as it seems to not represent a clear pattern within the loop.
*/

public class assignment_1{
    public static void main(String[] args){
        try{
            System.out.println("1)");
            triangle("u", "l");

            System.out.println("2)");
            triangle("d", "l");

            System.out.println("3)");
            triangle("u", "c");

            System.out.println("4)");
            triangle("d", "c");
        }
        catch(Exception e){
            System.err.println(e);
        }
    }

    public static void triangle(String orientation, String alignment){
        String sym = "*";
        String spc = " ";
        if (orientation == "up" || orientation == "u"){
            if (alignment == "left" || alignment == "l"){
                for(int i = 0; i < 5; i++){
                    System.out.println(sym.repeat(i+1));
                }
                System.out.println(".........\n");
            }
            else if (alignment == "center" || alignment == "c"){
                int spc_c = 5;
                for(int i = 1; i < 7; i += 2){
                    System.out.println(spc.repeat(spc_c) + sym.repeat(i));
                    spc_c--;
                }
                System.out.println("...........\n");
            }
        }
        else if (orientation == "down" || orientation == "d") {
            if (alignment == "left" || alignment == "l"){
                System.out.println("..........");
                for(int i = 4; i > 0; i--){
                    System.out.println(sym.repeat(i));
                }
                System.out.println();
            }
            else if (alignment == "center" || alignment == "c"){
                int spc_c = 2;
                
                System.out.println("............");
                for(int i = 7; i > 0; i -= 2){
                    System.out.println(spc.repeat(spc_c) + sym.repeat(i));
                    spc_c++;
                }
                System.out.println();
            } 
        }
    }
}
