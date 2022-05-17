/*
    SmoothStacks

    name: Q2.java
    contributor: Sam Sessums
    description:

            Question 2
            Given a list of non-negative integers, return an integer list of the rightmost digits. (Note: use %)

    *notes:
        

*/
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q2 {
    
        // collects the mapped x % 10 output to a list 
        static List<Integer> rightDigit(final List<Integer> l){
            return l.stream().map(x->x % 10).collect(Collectors.toList());
        }

        // simple driver with one list
        public static void main(String[] args){
            List<Integer> l = Arrays.asList(1234, 221, 342, 4, 3455);
            System.out.println(rightDigit(l));
        }
        

}
