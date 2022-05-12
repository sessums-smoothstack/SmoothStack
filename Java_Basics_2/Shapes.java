/*
    SmoothStacks

    name: Shapes.java
    contributor: Sam Sessums
    description:

        Simple driver class containing 1 interface and 3 children(?) of the interface. requires no CLI. 
        "Create a Shape Interface with the methods "calculateArea" and "display".
        Create a Rectangle, Circle, and Triangle class to implement that interface. "
        
        Assumptions
            values are of double and at least int
            Circle requires radius rather than diameter
            display() is meant to output the area of the shape to the console

        classes: Shapes
            (Interface) Shapes.shape
            Shapes.Rectangle(double length, double width)
            Shapes.Triangle(double height, double base)
            Shapes.Circle(double radius)

        
        Shapes.main()
            testing the use of declaring a variable of the child and super class
            Generating all three Shapes with values and and testing area correctness

        *notes:
            Used keyword 'this' just because.
*/
public class Shapes{
    interface Shape{
        public double calculateArea();
        public void display();
    }
    
    public static class Rectangle implements Shape{
        double length, width, area = 0;
    
        Rectangle(double l, double w){
            this.length = l;
            this.width = w;
            this.area = calculateArea();
        }
    
        public double calculateArea(){
            return this.length * this.width; 
        }
    
        public void display(){
            System.out.printf("The area of the rectangle is: %f\n", this.area);
        }
    }
    
    public static class Circle implements Shape{
        double radius, area = 0;
    
        Circle(double r){
            this.radius = r;
            this.area = calculateArea();
        }
    
        public double calculateArea(){
            return (Math.PI * Math.pow(this.radius, 2));
        }
    
        public void display(){
            System.out.printf("The area of the circle is: %f\n", this.area);
        }
    
    }
    
    public static class Triangle implements Shape{
        double height, base, area = 0;
    
        Triangle(double h, double b){
            this.height = h;
            this.base = b;
            this.area = this.calculateArea();
        }
    
        public double calculateArea(){
            return (0.5 * this.height * this.base);
        }
    
        public void display(){
            System.out.printf("The area of the triangle is: %f\n", this.area);
        }
    }
    public static void main(String[] args){
        java.util.Random doublesGen = new java.util.Random();

        Shape tri1 = new Triangle(1,2);
        Triangle tri2 = new Triangle(1,3);
        Shape rect1 = new Rectangle(1, 2);
        Rectangle rect2 = new Rectangle(4, 2);
        Shape circ1 = new Circle(2);
        Circle circ2 = new Circle(4);

        tri1.display();
        tri2.display();
        tri1.display();
        rect1.display();
        rect2.display();
        circ1.display();
        circ2.display();

        // having issues initalizing multiple values on one line (i.e. 'int passed, failed = 0;' )
        int testObjects = 1000;
        int failed = 0;

        double[][] vals = new double[2][testObjects]; // testing 1000 Shapes

        // generating values for the shapes to take on
        for(int i = 0; i < vals.length; i++){
            for(int j = 0; j < vals[i].length; j++){
                vals[i][j] = doublesGen.nextDouble();
            }
        }

        for(int i = 0; i < testObjects; i++){
            try{
                // Triangle using val[0][i] as height and val[1][i] as base
                double triArea = (.5 * vals[0][i] * vals[1][i]);
                Shape tri = new Triangle(vals[0][i], vals[1][i]);
                //System.out.println(triArea + " " + tri.calculateArea());
                assert(triArea == tri.calculateArea());
                
                // Rectangle using val[0][i] as length and val[1][i] as width
                double rectArea = (vals[0][i] * vals[1][i]);
                Shape rect = new Rectangle(vals[0][i], vals[1][i]);
                //System.out.println(rectArea + " " + rect.calculateArea());
                assert(rectArea == rect.calculateArea());

                // Triangle using val[0][i] + val[1][i] as radius
                double circArea = (Math.PI * Math.pow((vals[0][i] + vals[1][i]), 2));
                Shape circ = new Circle((vals[0][i] + vals[1][i]));
                //System.out.println(circArea + " " + circ.calculateArea());
                assert(circArea == circ.calculateArea());
            }
            catch (Exception e){
                System.err.println(e);
                failed++;
            }
        }

        assert(failed == 0);
        

    }
}