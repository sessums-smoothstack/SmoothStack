import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeAgent {

    static User user = null;
    static Connection con = null;

    static void setConnection(Connection c){con = c;}

    @FunctionalInterface
    interface print<T>{
        void println(T x);
    }
    static void emp1(){
        boolean quit = false;
        Scanner sc = new Scanner(System.in);
        print<String> cout = (x) -> System.out.println(x);
        do{
            cout.println("1) Enter Flights You Manage");
            cout.println("2) Quit to previous");
            System.out.print("Enter: ");
            try{
                Integer input = sc.nextInt();
            
                cout.println("");
                if (input == 1){
                    cout.println("Getting managed flights");
                }
                else if (input == 2){
                    cout.println("Going to previous menu");
                    quit = true;
                }
            }
            catch (InputMismatchException e){
                cout.println("Please enter a number!");
                sc.next(); // "flush"
            }
            finally{
                cout.println("");
            }
        }while(!quit);
        sc.close();
    }

    static void emp2(){

    }

    static void emp3(){

    }

    static void GetManagedFlights(){
        
    }
}
