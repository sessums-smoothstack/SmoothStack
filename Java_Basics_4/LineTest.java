

/*
    SmoothStacks

    name: LineTestJava.java
    contributor: Sam Sessums
    description:
        Create unit tests for the Line Class (see video).

        Create a file called LineTest.java.
        Create tests for the getSlope, getDistance, and parallelTo methods.
        Because of rounding errors, it is bad practice to test double values for exact equality. 
        To get around this, you can pass a small value (such as .0001) to assertEquals to be used as a delta.

        assumptions:
            None for now

        classes: LineTest - Driver

            LineTest.testLine() - @Test
                makes 100 Line objects and tests that member fields weren't fuzzed

            LineTest.testGetSlope() - @Test
                makes 100 Line objects and calculates each slope outside of the instance and compares
                it to the instance's slope

            LineTest.testGetDistance() - @Test
                makes 100 Line objects and calculates the distance outside of the instance and compares
                it to the instance's distance

            LineTest.testParallelTo() - @Test
                makes 100 pairs of Line objects where each pair have the same slope, and asserts that they 
                are parallel
        
    *notes:
        No video was provided, so I am making it up. Will add more thorough tests later 
        

*/
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;


public class LineTest {
    Random gen = new Random();

    @Test
    public void testLine(){

        for(int i = 0; i < 100; i++){
            double x1 = gen.nextDouble() * gen.nextInt(1000);
            double y1 = gen.nextDouble() * gen.nextInt(1000);
            double x2 = gen.nextDouble() * gen.nextInt(1000);
            double y2 = gen.nextDouble() * gen.nextInt(1000);
            Line l = new Line(x1, y1, x2, y2);

            assertEquals(x1, l.x1, .0001);
            assertEquals(y1, l.y1, .0001);
            assertEquals(x2, l.x2, .0001);
            assertEquals(y2, l.y2, .0001);
        }
    }

    @Test
    public void testGetSlope(){
        
        for(int i = 0; i < 100; i++){
            double x1 = gen.nextDouble() * gen.nextInt(1000);
            double y1 = gen.nextDouble() * gen.nextInt(1000);
            double x2 = gen.nextDouble() * gen.nextInt(1000);
            double y2 = gen.nextDouble() * gen.nextInt(1000);
            double answer = (y2 - y1)/(x2 - x1);
            Line l = new Line(x1, y1, x2, y2);
            assertEquals(answer, l.getSlope(), .0001);
        }
    }

    @Test
    public void testGetDistance(){
        for(int i = 0; i < 100; i++){
            double x1 = gen.nextDouble() * gen.nextInt(1000);
            double y1 = gen.nextDouble() * gen.nextInt(1000);
            double x2 = gen.nextDouble() * gen.nextInt(1000);
            double y2 = gen.nextDouble() * gen.nextInt(1000);
            double answer = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
            Line l = new Line(x1, y1, x2, y2);
            assertEquals(answer, l.getDistance(), .0001);
        }
    }

    @Test
    public void testParallelTo(){
        // creating 2 lines that have the same slope
        // scaling so slopes are the same
        for(int i = 1; i < 101; i++){
            double x1 = (double) i;
            double y1 = (double) i;
            double x2 = (double) i + 1;
            double y2 = (double) i + 1;

            double x3 = x1 + 1;
            double y3 = y1 + 1;
            double x4 = x2 + 1;
            double y4 = y2 + 1;

            Line l1 = new Line(x1, y1, x2, y2);
            Line l2 = new Line(x3, y3, x4, y4);
            assertEquals(true, l1.parallelTo(l2));
            assertEquals(true, l2.parallelTo(l1));
        }
    }
}
