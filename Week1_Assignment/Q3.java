import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
    SmoothStacks

    name: Q3.java
    contributor: Sam Sessums
    description:

            Question 3
            Given a list of integers, return a list where each integer is multiplied by 2.

    *notes:
        

*/
public class Q3 {
        static int[] doubling(int[] l){
            return IntStream.of(l).boxed()
            .map(x->x*2)
            .collect(Collectors.toList())
            .stream()
            .mapToInt(Integer::intValue)
            .toArray();
        }

        public static void main(String[] args){
            List<Integer> l1 = Arrays.asList(1234, 221, 342, 4, 3455);
            List<Integer> l1Doubled = l1.stream().map(x->x*2).collect(Collectors.toList());

            int[] l2 = doubling(l1.stream().mapToInt(Integer::intValue).toArray());

            System.out.println(l1Doubled);
            System.out.println(l2);
        }
}
