/*
    SmoothStacks

    name: Q4.java
    contributor: Sam Sessums
    description:
            Question 4
            Given a list of strings, return a list where each string has all its "x" removed.
        
    *notes:
        

*/
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q4 {
     
        static List<String> noX(List<String> l){
            return l.stream().map(x->x.replace("x","")).collect(Collectors.toList());
        }
        
        public static void main(String[] args) {
            List<String> l1 = Arrays.asList("ax", "bb", "c");
            List<String> l2 = Arrays.asList("xxax", "xbxbx", "xxcx");
            List<String> l3 = Arrays.asList("x");
            List<String> l4 = Arrays.asList("x", "xxxthisxisxxmyxxxnamexxx", "there is nothing here", "xnoXxx","xi xaxxxm txxxxhxxixxnkixxng");
            System.out.println(noX(l1));
            System.out.println(noX(l2));
            System.out.println(noX(l3));
            System.out.println(noX(l4));
        }
        
}
