/*
    SmoothStacks

    name: LambdasFunctionalInterfacesStreamsAndDates.java
    contributor: Sam Sessums
    description:
        Please see JavaBasics_Day5.docx
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.*;
import java.time.temporal.*;


public class LambdasFunctionalInterfacesStreamsAndDates {

    // Sorts words with 'e' at the beginning of the word
    // pseudo comparator
    public static int eSort(String a, String b){
        if(a.charAt(0) == 'e' && b.charAt(0) == 'e'){
            return a.compareTo(b);
        }

        else if(a.charAt(0) == 'e' && b.charAt(0) != 'e'){
            return -1;
        }

        else if(a.charAt(0) != 'e' && b.charAt(0) == 'e'){
            return 1;
        }

        else{
            return a.compareTo(b);
        }
    }
    

    public static void main(String[] args){


        List<String> myStrings = Arrays.asList("SmoothStack", "Training", "Every", "Day", "earned", "eeeee", "goals");

        /*
            Question 1
        */

        // 1
        List<String> shortToLongList = myStrings.stream().sorted((String a, String b) -> Integer.compare(a.length(), b.length())).collect(Collectors.toList());
        
        // 2
        List<String> LongToShortList = myStrings.stream().sorted((String a, String b) -> Integer.compare(b.length(), a.length())).collect(Collectors.toList());
        
        // 3
        List<String> LexiList = myStrings.stream().sorted((String a, String b) -> a.compareToIgnoreCase(b)).collect(Collectors.toList());
        
        //4
        List<String>  eAlphaList = myStrings.stream().sorted(Comparator.comparingInt((String a) -> a.startsWith("e") ? 0:1).thenComparing(Comparator.naturalOrder())).collect(Collectors.toList());
        
        //5
        List<String> eMethList = myStrings.stream().sorted((String a, String b) -> eSort(a, b)).collect(Collectors.toList());


        System.out.println(shortToLongList);
        System.out.println();
        System.out.println(LongToShortList);
        System.out.println();
        System.out.println(LexiList);
        System.out.println();
        System.out.println(eAlphaList);
        System.out.println();
        System.out.println(eMethList);
        System.out.println();
        /**************************************************************************************************************************/

        /*
            Question 2
        */
        @FunctionalInterface
        interface parityPrepend<T>{
            String process(T x);
        }

        parityPrepend<Integer> parityProcess = (x) -> {return x % 2 == 0 ? "e" + x.toString():"o"+x.toString();};

        // Took me a while to figue out that IntStream is not Stream<Integer>. go boxed()!
        String t = IntStream.range(0, 100).boxed().map((x) -> parityProcess.process(x)).collect(Collectors.joining(","));

        System.out.println(t);
        System.out.println();


        /**************************************************************************************************************************/


        /*
            Question 3
        */

        List<String> a3Strings = Arrays.asList("daft", "ere", "egg", "hip",  "air", "ape", "org", "eagle", "com", "iar", "tempe", "latitude", "brevity");
        List<String> filterA3 = a3Strings.stream().filter(x -> x.length() == 3 && x.startsWith("a")).collect(Collectors.toList());
        System.out.println(filterA3);
        System.out.println();


        /**************************************************************************************************************************/
        /* DateTime API */
        //Question 1
        /*
            Which class would you use to store your birthday in years, months, days, seconds, and nanoseconds?
        */
        LocalDateTime birthDateTime = LocalDateTime.of(LocalDate.of(1993, Month.MARCH, 17), LocalTime.of(13, 34, 23, 45));
        System.out.println(birthDateTime);


        //Question 2
        /*
            Given a random date, how would you find the date of the previous Thursday?
        */
        // With a temporal adjuster to the previous thursday
        LocalDateTime lastThursday = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
        System.out.println(lastThursday);

        //Question 3
        /*
            What is the difference between a ZoneId and a ZoneOffset?
            
            ZoneId specifies a time zone identifier and provides rules for converting between an Instant and a LocalDateTime.
            ZoneOffset specifies a time zone offset from Greenwich/UTC time.
        */

        //Question 4
        /*
            How would you convert an Instant to a ZonedDateTime? How would you convert a ZonedDateTime to an Instant?
        */

        //Instant to a ZonedDateTime
        Instant stamp = Instant.now();
        System.out.println(stamp);
        ZonedDateTime zonedStamp = stamp.atZone(ZoneId.systemDefault());

        System.out.println(stamp);
        System.out.println(zonedStamp);

        //ZonedDateTime to an Instant
        Instant originalStamp = Instant.from(zonedStamp);

        // Checking that the original Instances are equal after transformation
        assert(stamp.equals(originalStamp));

        //Question 5
        /*
            Write an example that, for a given year, reports the length of each month within that year.
        */
        
        // this works but just prints them
        //IntStream.range(1, 13).forEach(i -> {System.out.println(YearMonth.of(1999, i).lengthOfMonth());});
        
        // this will collect to a list
        List<Integer> daysInEachMonth = IntStream.range(1, 13).boxed().map(x -> YearMonth.of(2022, x).lengthOfMonth()).collect(Collectors.toList());
        System.out.println(daysInEachMonth);
        
        
        //Question 6
        /*
            Write an example that, for a given month of the current year, lists all of the Mondays in that month.
        */

        ArrayList<LocalDate> allMondays = new ArrayList<>();
        LocalDate startDate = LocalDate.of(2022, 2, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());

        for(LocalDate date = startDate; !date.isAfter(endDate); date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY))){
            allMondays.add(date);
        }
        System.out.println(allMondays);
        
        //Question 
        /*
            Write an example that tests whether a given date occurs on Friday the 13th.
        */
        @FunctionalInterface
        interface ListFriday13<T, R>{
            R is(T date);
        }

        ListFriday13<LocalDate, Boolean> friday13 = (x) -> {return (x.getDayOfWeek()==DayOfWeek.FRIDAY && x.getDayOfMonth()==13) ? true:false;};
        
    }
}
