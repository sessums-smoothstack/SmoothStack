import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Traveler {
    static User user = null;
    static Connection con = null;
    

    public static void main(){
        try{
            Integer memb_num = null;
            Scanner sc = new Scanner(System.in);
            Boolean quit = false;
            do{
                System.out.println("Enter your Membership ID: ");
                try{
                    memb_num = sc.nextInt();
                    // get access here 
                    TRAV1();
                    quit = true;
                    
                }
                catch (InputMismatchException e){
                    System.out.println("Please enter a number!");
                    sc.next(); // flush 
                }
                catch(Exception e){
                    System.out.println(e);
                }
                finally{
                    sc.close();
                    System.out.println("");
                }
            }while(!quit);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void TRAV1(){
        try{
            Scanner sc = new Scanner(System.in);
            Integer option = null;
            Boolean quit = false;
            do{
                try{
                    System.out.println("1) Book a Ticket\n2) Cancel an Upcoming Trip\n3) Quit to Previous Menu");
                    option = sc.nextInt();
                    switch (option){
                        case 1:
                            TRAV2();
                            break;

                        case 2:
                            TRAV3();
                            break;

                        case 3:
                            quit = true;
                            break;
                        
                        default:
                            System.out.println("Enter a number 1-3");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Enter a choice");
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }while(!quit);
            sc.close();

        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public static void TRAV2()throws Exception{
        Flight[] flights = null;
        // Retrieve flights here
        Statement flightsStmt = con.createStatement();
        ResultSet flightSet = flightsStmt.executeQuery("");

        try{
            /*
            if(flights != null){
                for(int i = 0; i < flights.length; i++){
                    System.out.printf("%d) %s\n",i, flights[i].getRoute());
                }
            }
            */
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public static void TRAV3(){
        
    }



    
}
