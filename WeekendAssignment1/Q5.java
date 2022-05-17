/*
    SmoothStacks

    name: Q5.java
    contributor: Sam Sessums
    description:

        Question 5
            Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums to the given target, with this additional constraint:
            if there are numbers in the array that are adjacent and the identical value, they must either all be chosen, or none of them chosen.
            For example, with the array {1, 2, 2, 2, 5, 2}, either all three 2's in the middle must be chosen or not, all as a group.
            (one loop can be used to find the extent of the identical values).

            groupSumClump(0, [2, 4, 8], 10) → true
            groupSumClump(0, [1, 2, 4, 8, 1], 14) → true
            groupSumClump(0, [2, 4, 4, 8], 14) → false

    *notes:
        

*/
 

public class Q5 {
    static boolean groupSumClump(int i, int[] l, int s){
        // Zero out clumps and find a clump that works
        for (int j = 0; j < l.length; j++){
            if (clumps(l[j], j, l, s) == true){
                return true;
            }
        }

        // No solution
        if (i >= l.length){
            return s == 0 ? true: false;
        }

        // use current list num and subtract
        if(groupSumClump(i+1, l, s-l[i])){
            return true;
        }

        // otherwise check the next digit
        if(groupSumClump(i+1, l, s)){
            return true;
        }
        else return false;
    }

    // the idea is to check 3 consecutive integers at a time if permitted and zeroing out the first element
    static boolean clumps(int num, int index, int[] list, int sum){
        // if lists arent 3 or more ints
        if(list.length == 0) return false;
        else if (list.length == 1 && num == sum) return true;
        else if (list.length == 1 && num != sum) return false;
        else if (list.length == 2){
            if(num == list[index] && num == list[index+1]){
                list[index] = 0;
                list[index+1] = 0;
                if (sum - (num*2) == 0){
                    return true;
                }
                else return false;
            }
            else return false;
        }
        // lists have 3 or more ints
        else{
            // check if three elements can be accessed
            if(index + 2 < list.length){

                // all three equal the num
                if(num == list[index] && num == list[index+1] && num == list[index+2]){
                    list[index] = 0;
                    return clumps(num, index+1, list, (sum - (3*num)));
                }
                // only two equal the num
                else if(num == list[index] && num == list[index+1]){
                    list[index] = 0;
                    list[index+1] = 0;
                    if (sum - (2*num) == 0) return true;
                    return false;
                }
                // only one does, not a clump
                else if(num == list[index] && num != list[index+1]){
                    return false;
                }
            }

            // can't check three at a time, only 2
            else if(index + 1 < list.length){
                if(num == list[index] && num == list[index+1]){
                    list[index] = 0;
                    list[index+1] = 0;
                    if(sum - (2*sum) == 0) return true;
                    return false;
                }
                else if(num == list[index] && num != list[index+1]){
                    return false;
                }
            }
        }
        return false;
    }
   
    public static void main(String[] args){
        @FunctionalInterface
        interface o<T>{
            void print(T x);
        }

        o<int[]> list = (l) -> {
            for (int i : l){
                System.out.print(i + " ");
            }
            System.out.println();
        };

        int[] t1 = {2, 4, 8};
        int[] t2 = {1, 2, 4, 8, 1};
        int[] t3 = {2, 4, 4, 8};
        int[] t4 = {1,2,2,2,3,4,25,25,25};
        int[] t5 = {2, 4, 4, 8, 4, 4, 4, 4, 2, 1, 4, 5, 6, 7};
        int[] t6 = {2, 4, 4, 8, 8, 4, 8};
        int[] t7 = {16, 16, 2, 4, 4, 8, 4, 4, 4, 4, 2, 1, 4, 5, 6, 7};
        int[] t8 = {2, 2};
        int[] t9 = {5, 7, 11, 21, 11, 11, 11, 50, 100, 75, 23, 75, 20};
        int[] t10 = {2, 2, 2, 3, 4, 4, 4, 5, 78};

        
        System.out.println(groupSumClump(0, t1, 10));
        System.out.println(groupSumClump(0, t2, 14));
        System.out.println(groupSumClump(0, t3, 14));
        System.out.println(groupSumClump(0, t4, 75));
        System.out.println(groupSumClump(0, t5, 16));
        System.out.println(groupSumClump(0, t6, 16));
        System.out.println(groupSumClump(0, t7, 32));
        System.out.println(groupSumClump(0, t8, 4));
        System.out.println(groupSumClump(0, t9, 220));
        System.out.println(groupSumClump(0, t10, 220));

        list.print(t1);
        list.print(t2);
        list.print(t3);
        list.print(t4);
        list.print(t5);
        list.print(t6);
        list.print(t7);
        list.print(t8);
        list.print(t9);
        list.print(t10);

    }
}
