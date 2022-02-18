/*
    SmoothStacks

    name: Line.java
    contributor: Sam Sessums
    description:
        Basic Line class, holds two pairs of points as fields x1, y1, x2, y2
        this assumes the classic cartesian coordinates

        classes: Line
            Line(double x1, double y1, double x2, double y2) - Constructor
                x1, y1, x2, y2 :- member fields x1, y1, x2, y2 

            Line.getSlope() - Method
                returns a double of the slope for the lines slope

            Line.getDistance() - Method
                returns a double of the lines distance between its two points

            Line.parallelTo(Line l) - Method
                l :- Line to compare slopes to
                returns a boolean if the lines have the same slope

        
    *notes:
        Haven't decided on handing Division by zero (throw exception or do something else)
            

*/

import java.util.Random;

public class Line {
    double x1, y1, x2, y2;

    Line(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getSlope(){
        return ((y2-y1)/(x2-x1));
    }

    public double getDistance(){
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }

    public boolean parallelTo(Line l){
        return (l.getSlope() == getSlope()) ? true:false;
    }

}
