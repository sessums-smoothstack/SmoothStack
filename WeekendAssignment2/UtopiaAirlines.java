import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.sql.Statement;

public class UtopiaAirlines {

    static Connection con = null;

    @FunctionalInterface
    interface print<T>{
        void println(T x);
    }

    static void setConnection(Connection c){ con = c;}

    static void main_menu() throws Exception{
        boolean quit = false;
        print<String> cout = (x) -> System.out.println(x);


        Scanner sc = new Scanner(System.in);
        do{
            cout.println("Welcome to the Utopia Airlines Management System.\nWhich category of a user are you?");
            cout.println("1) Employee/Agent");
            cout.println("2) Administrator");
            cout.println("3) Traveler"); 
            cout.println("4) Quit");
            try{
                Integer input = sc.nextInt();
                

                switch (input){
                    case 1:
                        cout.println("Not Fully Implemented");
                        //EmployeeAgent.emp1();
                        break;

                    case 2:
                        cout.println("");
                        Admin.setConnection(con);
                        Admin.ADMIN1();
                        break;

                    case 3:
                        cout.println("Not Fully Implemented");
                        //Traveler.main();
                        break;

                    case 4:
                        quit = true;
                        break;

                    default:
                        break;
                }
            }
            catch (InputMismatchException e){
                cout.println("Please enter a number!");
                sc.next(); // flush
                
            }
            catch (NoSuchElementException e){
                //e.printStackTrace();
                //System.out.println(e);
                quit = true; // without, will print the first part of do loop
                cout.println("");
                cout.println("Have a great day!");
            }
            finally{
                cout.println("");
            }

        }while(!quit);
        sc.close();
    }

    static void SignIn(){
        try{
            Boolean quit = false;
            Scanner sc = new Scanner(System.in);
            String email = null;
            String pass = null;

            do{
                try{
                    System.out.println("Please Sign In");
                    System.out.print("email: ");
                    email = sc.nextLine();
                    System.out.println();
                    pass = System.console().readPassword("password :").toString();
                    Statement stmt = con.createStatement();
                    ResultSet userSet = stmt.executeQuery("select * from utopia_airlines.users where email = '"+ email + "' AND password = '"+ pass + "'");
                    // there should only be one result, emails will be unique to the user
                    


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
    


    public static void main() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/utopia_airlines", "sam", "3301");

            if(con != null){
                setConnection(con);
                //Admin.setConnection(con);
                //Admin.main();
                //SignIn();
                main_menu();
            }
            else{
                System.out.println("Connection is null");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
}
