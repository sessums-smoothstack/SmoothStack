import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.*;

public class Admin {

    static Connection con = null;

    public static void setConnection(Connection c){
        con = c;
    }

    

    public static void AddFlights(Flight flight) throws SQLException{
        try{
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("insert into utopia_airlines.flights (flight_id, origin_iata, origin_city, destination_iata, destination_city, depart_time, reserved_seats, seat_price) values(?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, flight.flight_id);
            stmt.setString(2, flight.origin.iata_id);
            stmt.setString(3, flight.origin.city);
            stmt.setString(4, flight.destination.iata_id);
            stmt.setString(5, flight.destination.city);
            stmt.setString(6, flight.depart_time);
            stmt.setInt(7, flight.reserved_seats);
            stmt.setDouble(8, flight.seat_price);
            stmt.executeUpdate();
            con.commit();
        }
        catch (SQLException e){
            System.out.println(e);
            if(con != null){
                System.out.println("Rolling Back");
                con.rollback();
            }
        }
        con.setAutoCommit(true); // not sure where to put this
    }

    public static void UpdateFlights(Flight oldFlight, Flight newFlight) throws SQLException{
        try{
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("update utopia_airlines.flights set flight_id = ?, origin_iata =  ?, origin_city = ?, destination_iata = ?, destination_city = ?, depart_time = ?, reserved_seats = ?, seat_price = ? WHERE flight_id = ?");

            //not sure why this isn't working with the update, but for now we have to move on
            //and origin_iata =  ? and origin_city = ? and destination_iata = ? and destination_city = ? and depart_time = ? and reserved_seats = ? and seat_price = ?

            stmt.setInt(1, newFlight.flight_id);
            stmt.setString(2, newFlight.origin.iata_id);
            stmt.setString(3, newFlight.origin.city);
            stmt.setString(4, newFlight.destination.iata_id);
            stmt.setString(5, newFlight.destination.city);
            stmt.setString(6, newFlight.depart_time);
            stmt.setInt(7, newFlight.reserved_seats);
            stmt.setDouble(8, newFlight.seat_price);

            stmt.setInt(9, oldFlight.flight_id);

            //Only try to use if the above uncommented query is added to prepared statement
            /*
            stmt.setString(10, oldFlight.origin.iata_id);
            stmt.setString(11, oldFlight.origin.city);
            stmt.setString(12, oldFlight.destination.iata_id);
            stmt.setString(13, oldFlight.destination.city);
            stmt.setString(14, oldFlight.depart_time);
            stmt.setInt(15, oldFlight.reserved_seats);
            stmt.setDouble(16, oldFlight.seat_price);
            */
            stmt.executeUpdate();

            con.commit();
        }
        catch (SQLException e){
            con.rollback();
        }
        con.setAutoCommit(true);
    }

    public static void DeleteFlights(Flight flight) throws SQLException{
        try{
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("delete from utopia_airlines.flights where flight_id = ? and origin_iata = ? and origin_city = ? and destination_iata = ? and destination_city = ? and depart_time = ? and reserved_seats = ? and seat_price = ? ");
            stmt.setInt(1, flight.flight_id);
            stmt.setString(2, flight.origin.iata_id);
            stmt.setString(3, flight.origin.city);
            stmt.setString(4, flight.destination.iata_id);
            stmt.setString(5, flight.destination.city);
            stmt.setString(6, flight.depart_time);
            stmt.setInt(7, flight.reserved_seats);
            stmt.setDouble(8, flight.seat_price);
            stmt.executeUpdate();
            con.commit();
        }
        catch (SQLException e){
            System.out.println(e);
            if(con != null){
                System.out.println("Rolling Back");
                con.rollback();
            }
        }
        con.setAutoCommit(true); // not sure where to put this
    }

    public static ArrayList<Flight> ReadFlights() throws Exception{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from utopia_airlines.flights");
        ArrayList<Flight> flights = new ArrayList<Flight>();
        while(rs.next()){
            Integer flight_id = rs.getInt(1);
            Airport origin = new Airport(rs.getString(2), rs.getString(3));
            Airport destination = new Airport(rs.getString(4), rs.getString(5));
            String time = rs.getString(6);
            Integer reserved_seats = rs.getInt(7);
            Double seat_price = rs.getDouble(8);
            Flight newFlight = new Flight(flight_id, origin, destination, time, reserved_seats, seat_price);
            flights.add(newFlight);

        }
        return flights;
        
    }

    public static void AddAirports(String iata_id, String city) throws SQLException{
        try{
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("insert into utopia_airlines.airports (iata_id, city) values(?, ?)");
            stmt.setString(1, iata_id);
            stmt.setString(2, city);
            stmt.executeUpdate();
            con.commit();
        }
        catch (SQLException e){
            System.out.println(e);
            if(con != null){
                System.out.println("Rolling Back");
                con.rollback();
            }
        }
        con.setAutoCommit(true); // not sure where to put this
    }

    public static void UpdateAirports(Airport oldAirport, Airport newAirport) throws SQLException{
        try{
            con.setAutoCommit(false);

            PreparedStatement stmt = con.prepareStatement("update utopia_airlines.airports set iata_id = ?, city = ? where iata_id = ? and city = ?");
            stmt.setString(1, newAirport.iata_id);
            stmt.setString(2, newAirport.city);

            stmt.setString(3, oldAirport.iata_id);
            stmt.setString(4, oldAirport.city);

            stmt.executeUpdate();
            con.commit();
        }
        catch(SQLException e){
            System.out.println(e);
            if (con != null){
                con.rollback();
            }       
        }
        con.setAutoCommit(true);
    }

    public static void DeleteAirports(Airport airport)throws SQLException{
        try{
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("delete from utopia_airlines.airports where iata_id =  ? and city = ?");
            stmt.setString(1, airport.iata_id);
            stmt.setString(2, airport.city);
            stmt.executeUpdate();
            con.commit();
        }
        catch (SQLException e){
            System.out.println(e);
            if(con != null){
                System.out.println("Rolling Back");
                con.rollback();
            }
        }
        con.setAutoCommit(true); // not sure where to put this
    }

    public static ArrayList<Airport> ReadAirports(){
        ArrayList<Airport> airports = new ArrayList<Airport>();
        try{
            
            //Integer count = 1;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from utopia_airlines.airports");
            while(rs.next()){
                String iata = rs.getString(1);
                String city = rs.getString(2);
                Airport a = new Airport(iata, city);
                airports.add(a);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return airports;
    }

    public static void AddPassengers(Passenger passenger) throws SQLException{
        try{
            if (con != null){
                con.setAutoCommit(false);
                PreparedStatement stmt = con.prepareStatement("insert into utopia_airlines.passengers values(?, ?, ?) ");
                stmt.setInt(1, passenger.id);
                stmt.setInt(2, passenger.booking_id);
                stmt.setString(3, passenger.full_name);
                stmt.executeUpdate();
                con.commit();
            }
        }
        catch(SQLException e){
            if(con != null){
                con.rollback();
            }
        }
        con.setAutoCommit(true);
    }

    public static void UpdatePassengers(Passenger oldPassenger, Passenger newPassenger) throws SQLException{
        try{
            con.setAutoCommit(false);

            PreparedStatement stmt = con.prepareStatement("update utopia_airlines.passengers set id = ?, booking_id = ?, full_name =  ? where id = ? and booking_id = ? and full_name = ?");
            stmt.setInt(1, newPassenger.id);
            stmt.setInt(2, newPassenger.booking_id);
            stmt.setString(3, newPassenger.full_name);

            stmt.setInt(4, oldPassenger.id);
            stmt.setInt(5, oldPassenger.booking_id);
            stmt.setString(6, oldPassenger.full_name);

            stmt.executeUpdate();
            con.commit();
        }
        catch(SQLException e){
            System.out.println(e);
            if (con != null){
                con.rollback();
            }       
        }
        con.setAutoCommit(true);
    }

    public static void DeletePassenger(Passenger passenger) throws SQLException{
        try{
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("delete from utopia_airlines.passengers where id = ? and booking_id = ? and full_name = ?");
            
            stmt.setInt(1, passenger.id);
            stmt.setInt(2, passenger.booking_id);
            stmt.setString(3, passenger.full_name);
            stmt.executeUpdate();
            con.commit();

        }
        catch(SQLException e){
            if (con != null){
                con.rollback();
            }
        }
        con.setAutoCommit(true);
    }

    public static ArrayList<Passenger> ReadPassengers(){
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from utopia_airlines.passengers");
            while(rs.next()){
                Integer id = rs.getInt(1);
                Integer booking_id = rs.getInt(2);
                String name = rs.getString(3);
                passengers.add(new Passenger(id, booking_id, name));
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return passengers;

    }

    public static ArrayList<Passenger> getPassengers(Flight flight){
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from utopia_airlines.passengers where booking_id = ?");
            stmt.setInt(1, flight.flight_id);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                passengers.add(new Passenger(rs.getInt(1), rs.getInt(2), rs.getString(3)));

            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return passengers;
    }

    public static void AddEmployees(Employee employee) throws SQLException{
        try{
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("insert into utopia_airlines.employees values(?, ?, ?, ?)");
            
            stmt.setInt(1, employee.id);
            stmt.setInt(2, employee.role_id);
            stmt.setString(3, employee.f_name);
            stmt.setString(4, employee.l_name);
            stmt.executeUpdate();
            con.commit();

        }
        catch(SQLException e){
            if (con != null){
                con.rollback();
            }
        }
        con.setAutoCommit(true);
    }

    public static void UpdateEmployees(Employee oldEmployee, Employee newEmployee) throws SQLException{
        try{
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("update utopia_airlines.employees set id = ?, role_id = ?, f_name = ?, l_name = ? where id = ? and role_id = ? and f_name = ? and l_name = ?");
            stmt.setInt(1, newEmployee.id);
            stmt.setInt(2, newEmployee.role_id);
            stmt.setString(3, newEmployee.f_name);
            stmt.setString(4, newEmployee.l_name);

            stmt.setInt(5, oldEmployee.id);
            stmt.setInt(6, oldEmployee.role_id);
            stmt.setString(7, oldEmployee.f_name);
            stmt.setString(8, oldEmployee.l_name);
            stmt.executeUpdate();
            con.commit();
        }
        catch(SQLException e){
            if (con != null){
                con.rollback();
            }
        }
        con.setAutoCommit(true);
    }

    public static void DeleteEmployees(Employee employee) throws SQLException{
        try{
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("delete from utopia_airlines.employees where id = ? and role_id = ? and f_name = ? and l_name = ?");
            
            stmt.setInt(1, employee.id);
            stmt.setInt(2, employee.role_id);
            stmt.setString(3, employee.f_name);
            stmt.setString(4, employee.l_name);
            stmt.executeUpdate();
            con.commit();

        }
        catch(SQLException e){
            if (con != null){
                con.rollback();
            }
        }
        con.setAutoCommit(true);
    }

    public static ArrayList<Employee> ReadEmployees(){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from utopia_airlines.employees");
            while(rs.next()){
                Integer id = rs.getInt(1);
                Integer role_id = rs.getInt(2);
                String f_name = rs.getString(3);
                String l_name = rs.getString(4);

                employees.add(new Employee(id, role_id, f_name, l_name));
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return employees;
    }

    public static void OverrideTripCancel(){

    }

    public static void ADMIN1(){
        try{
            Boolean quit = false;
            Scanner sc = new Scanner(System.in);
            int choice = 0;
            do{
                System.out.println("Select an option");
                System.out.println("1) Flights");
                System.out.println("2) Seats");
                System.out.println("3) Passengers");
                System.out.println("4) Airports");
                System.out.println("5) Employees");
                System.out.println("6) Logout");
                System.out.print("> ");
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
                
                switch (choice){
                    case 1:
                        FLIGHTS();
                        break;

                    case 2:
                        SEATS();
                        break;
                    
                    case 3:
                        PASSENGERS();
                        break;

                    case 4:
                        AIRPORTS();
                        break;

                    case 5:
                        EMPLOYEES();
                        break;

                    case 6:
                        quit = true;
                        break;

                    default:
                        System.out.println("Please Select a number 1-8");
                }

            }while(!quit);

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void FLIGHTS(){
        try{
            Boolean quit = false;
            Scanner sc = new Scanner(System.in);
            Integer choice = 0;
            do{
                System.out.println("Select an Option");
                System.out.println("1) Add Flights");
                System.out.println("2) Update Flights");
                System.out.println("3) Delete Flights");
                System.out.println("4) Read Flights");
                System.out.println("5) Go Back");
                System.out.print("> ");
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
                
                switch (choice){
                    case 1:
                        ADDFLIGHTS();
                        break;

                    case 2:
                        UPDATEFLIGHTS();
                        break;
                    
                    case 3:
                        DELETEFLIGHTS();
                        break;

                    case 4:
                        ArrayList<Flight> flights = ReadFlights();
                        if (flights.isEmpty()){
                            System.out.println("There are no flights at this time");
                        }
                        for(int i = 0; i < flights.size(); i++){
                            System.out.println(i+1 + ") " + flights.get(i));
                        }
                        break;

                    case 5:
                        quit = true;
                        break;

                    default:
                        System.out.println("Please Select a number 1-8");
                }
                
            }while(!quit);
        }
        catch(Exception e){
            System.out.println("here");
            System.out.println(e);
        }
    }

    public static void ADDFLIGHTS(){
        try{
            Boolean quit = false;
            Scanner sc = new Scanner(System.in);
            Integer choice = 0;
            Airport origin = new Airport();
            Airport destination = new Airport();
            Boolean origin_quit = false;
            Boolean arrival_quit = false;
            LocalDate date = LocalDate.now(); 
            LocalTime time = LocalTime.now();
            LocalDateTime dateTime = LocalDateTime.now();

            do{
                Integer choice1 = AirportMenuLoop();
                if(choice1 == 1){
                    origin_quit = PickAirportFromList("Origin/Departure", origin);
                    if(origin_quit) quit = true;
                    else{
                        Integer choice2 = AirportMenuLoop();
                        if (choice2 == 1){
                            arrival_quit = PickAirportFromList("Arrival/Destination", destination);
                            if(arrival_quit) quit = true;
                            else{
                                //Route route = new Route(origin, destination);
                                Integer datePick = DateMenuLoop();
                                if (datePick == 1){
                                    date = PickDateFromList();
                                    Integer pickTime = TimeMenuLoop();
                                    if (pickTime == 1){
                                        time = PickTimeFromList();
                                    }
                                    else if(pickTime == 2){
                                        time = AddTime();
                                    }
                                    dateTime = date.atTime(time);
                                    System.out.print("Enter Flight ID: ");
                                    Integer flight_id = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter Reserved Seats: ");
                                    Integer reserved_seats = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter Price: ");
                                    Double seat_price = sc.nextDouble();
                                    sc.nextLine();
                                    Flight flight = new Flight(flight_id, origin, destination, dateTime.toString(), reserved_seats, seat_price);
                                    AddFlights(flight);
                                    quit = true;

                                }
                                else if (datePick == 2){
                                    date = AddDate();
                                    Integer pickTime = TimeMenuLoop();
                                    if (pickTime == 1){
                                        time = PickTimeFromList();
                                    }
                                    else if(pickTime == 2){
                                        time = AddTime();
                                    }
                                    dateTime = date.atTime(time);
                                    System.out.print("Enter Flight ID: ");
                                    Integer flight_id = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter Reserved Seats: ");
                                    Integer reserved_seats = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter Price: ");
                                    Double seat_price = sc.nextDouble();
                                    sc.nextLine();
                                    Flight flight = new Flight(flight_id, origin, destination, dateTime.toString(), reserved_seats, seat_price);
                                    AddFlights(flight);
                                    quit = true;
                                }
                                quit = true;
                                
                            }
                        }

                        else if (choice2 == 2){
                            while(!AddAirport(destination)); // can't cancel this
                            Route route = new Route(origin, destination);
                            Integer datePick = DateMenuLoop();
                            if (datePick == 1){
                                date = PickDateFromList();
                                Integer pickTime = TimeMenuLoop();
                                if (pickTime == 1){
                                    time = PickTimeFromList();
                                }
                                else if(pickTime == 2){
                                    time = AddTime();
                                }
                                dateTime = date.atTime(time);
                                System.out.print("Enter Flight ID: ");
                                Integer flight_id = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Reserved Seats: ");
                                Integer reserved_seats = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Price: ");
                                Double seat_price = sc.nextDouble();
                                sc.nextLine();
                                Flight flight = new Flight(flight_id, origin, destination, dateTime.toString(), reserved_seats, seat_price);
                                AddFlights(flight);    
                                quit = true;

                            }
                            else if (datePick == 2){
                                date = AddDate();
                                Integer pickTime = TimeMenuLoop();
                                if (pickTime == 1){
                                    time = PickTimeFromList();
                                }
                                else if(pickTime == 2){
                                    time = AddTime();
                                }
                                dateTime = date.atTime(time);
                                System.out.print("Enter Flight ID: ");
                                Integer flight_id = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Reserved Seats: ");
                                Integer reserved_seats = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Price: ");
                                Double seat_price = sc.nextDouble();
                                sc.nextLine();
                                Flight flight = new Flight(flight_id, origin, destination, dateTime.toString(), reserved_seats, seat_price);
                                AddFlights(flight);
                                quit = true;
                            }
                            quit = true;
                        }

                        else if(choice2 == 3){
                            quit = true;
                        }
                    }
                    
                }
                else if(choice1 == 2){
                    while(!AddAirport(origin)); // can't cancel this, you shoudnt always make up new airports
                    Integer choice2 = AirportMenuLoop(); // you can cancel this
                    if(choice2 == 1){
                        arrival_quit = PickAirportFromList("Arrival/Destination", destination);
                        if (arrival_quit) quit = true;
                        else{
                            //Route route = new Route(origin, destination);
                            Integer datePick = DateMenuLoop();
                            if (datePick == 1){
                                Integer pickTime = TimeMenuLoop();
                                if (pickTime == 1){
                                    time = PickTimeFromList();
                                }
                                else if(pickTime == 2){
                                    time = AddTime();
                                }
                                dateTime = date.atTime(time);
                                System.out.print("Enter Flight ID: ");
                                Integer flight_id = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Reserved Seats: ");
                                Integer reserved_seats = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Price: ");
                                Double seat_price = sc.nextDouble();
                                sc.nextLine();
                                Flight flight = new Flight(flight_id, origin, destination, dateTime.toString(), reserved_seats, seat_price);
                                AddFlights(flight);    
                                quit = true;
                            }
                            else if (datePick == 2){
                                date = AddDate();
                            }
                            quit = true;
                        }
                    }

                    else if (choice2 == 2){
                        while(!AddAirport(destination)); //can't cancel this
                        Route route = new Route(origin, destination);
                        Integer datePick = DateMenuLoop();
                        if (datePick == 1){
                            date = PickDateFromList();
                        }
                        else if (datePick == 2){
                            date = AddDate();
                            Integer pickTime = TimeMenuLoop();
                            if (pickTime == 1){
                                time = PickTimeFromList();
                            }
                            else if(pickTime == 2){
                                time = AddTime();
                            }
                            dateTime = date.atTime(time);
                            System.out.print("Enter Flight ID: ");
                            Integer flight_id = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Reserved Seats: ");
                            Integer reserved_seats = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Price: ");
                            Double seat_price = sc.nextDouble();
                            sc.nextLine();
                            Flight flight = new Flight(flight_id, origin, destination, dateTime.toString(), reserved_seats, seat_price);
                            AddFlights(flight);
                            quit = true;
                        }
                        quit = true;

                    }

                    else if (choice == 3){
                        quit = true;
                    }
                }

                else if(choice1 == 3){
                    quit = true;
                }
            }while(!quit);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static Boolean PickAirportFromList(String time, Airport airport){
        Boolean done = false;
        try{
            Boolean quit = false;
            Scanner sc = new Scanner(System.in);
            Integer choice = 0;
            ArrayList<Airport> airports = ReadAirports();
            
            try{
                do{
                    System.out.println("Pick " + time + " Airport");
                    for(int i = 0; i < airports.size(); i++){
                        System.out.print(i+1 + ") " + airports.get(i) + "\n");
                    }
                    System.out.printf("%d) Cancel\n", airports.size()+1);
                    System.out.print("> ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    System.out.println();

                    if (choice <= 0 || choice > airports.size()+1){
                        System.out.printf("Pick a number between 1 - %d\n", airports.size());
                    }
                    else if (choice == airports.size()+1){
                        done = true;
                        quit = true;
                    }
                    else{
                        airport.iata_id = airports.get(choice-1).iata_id;
                        airport.city = airports.get(choice-1).city;
                        quit = true;
                    }

                }while(!quit);
            }
            catch(InputMismatchException e){
                System.out.println("Pick a number this time");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return done;
    }

    public static Boolean AddAirport(Airport airport) throws SQLException{
        String iata_id = "";
        String city = "";
        Scanner sc = new Scanner(System.in);
        Statement stmt = con.createStatement();
        Boolean success = true;
        try{
            System.out.println("Add an Airport");
            System.out.print("City Name: ");
            city = sc.nextLine(); // not going to bother to check if its a real city;
            //System.out.println();
            System.out.print("IATA: ");
            iata_id = sc.nextLine().trim();
            //System.out.println();
            if (iata_id.length() != 3){
                System.out.println("IATA not three letters\n");
            }
            else{
                ResultSet rs = stmt.executeQuery("select * from utopia_airlines.airports where iata_id = '" + iata_id + "'");
                if (rs.next()){
                    System.out.printf("\n%s already exists\n\n", rs.getString(1));
                    success = false;
                }
                else{
                    AddAirports(iata_id, city);
                    airport.iata_id = iata_id;
                    airport.city = city;
                }
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
        return success;
    }
    
    public static Integer AirportMenuLoop(){
        Integer choice = 0;
        try{
            Boolean quit = false;
            Scanner sc = new Scanner(System.in);
            
            do{
                System.out.println("1) Pick an Existing Airport");
                System.out.println("2) Add an Airport");
                System.out.println("3) Cancel");
                System.out.print("> ");
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println();

                if (choice < 1 || choice > 3){
                    System.out.println("Pick a number between 1 and 3");
                }
                else{
                    quit = true;
                }
            }while(!quit);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return choice;
    }


    public static Integer DateMenuLoop(){
        Integer choice = 0;
        try{
            Boolean quit = false;
            Scanner sc = new Scanner(System.in);
            
            do{
                System.out.println("1) Pick a Date");
                System.out.println("2) Add a Date");
                System.out.println("3) Cancel");
                System.out.print("> ");
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println();

                if (choice < 1 || choice > 3){
                    System.out.println("Pick a number between 1 and 3");
                }
                else{
                    quit = true;
                }
            }while(!quit);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return choice;
    }


    public static LocalDate PickDateFromList(){
        LocalDate now = LocalDate.now();
        LocalDate date = null;
        Integer daysPassed = 0;
        Boolean quit = false;
        String input;
        Integer dateInput = 0;
        try{
            Scanner sc = new Scanner(System.in);
            do{
                daysPassed(now, daysPassed);
                System.out.println("Select a Date.\nHit n for more dates");
                System.out.print("> ");
                input = sc.nextLine();
                if (input.length() == 1 && input.equalsIgnoreCase("n")){
                    daysPassed += 10;
                }
                else if (input.length() == 1 && input.equalsIgnoreCase("q")){
                    quit = true;
                }
                else{
                    try{
                        dateInput = Integer.parseInt(input);
                        if (dateInput >= 1 && dateInput <= 10){
                            if (daysPassed < 10){
                                date = now.plusDays(dateInput-1);
                                System.out.println(date.toString());
                                quit = true;
                            }
                            else{
                                // Broken
                                //System.out.println("here");
                                date = now.plusDays(daysPassed + dateInput-1);
                                System.out.println(date.toString());
                                quit = true;
                            }
                            
                        }
                    }
                    catch (Exception e){
                        System.out.println("Try again.");
                    }
                }
                

            }while(!quit);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return date;
    }

    public static void daysPassed(LocalDate date, Integer count){
        Integer daysPassed = count+10;
        Integer userCount = 1;
        for(;count<daysPassed;count++){
            System.out.println(userCount + ") " + date.plusDays(count).toString());
            userCount++;
        }

    }

    public static LocalDate AddDate(){
        LocalDate date = null;
        YearMonth now = YearMonth.now();
        Integer year = 0;
        Integer month = 0;
        Integer day = 0;
        Scanner sc = new Scanner(System.in);
        Boolean quit = false;
        try{
            do{
                System.out.print("Enter Year: ");
                year = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Month: ");
                month = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter day:");
                day = sc.nextInt();
                sc.nextLine();
                if (year <  now.getYear() || month < now.getMonthValue() || !YearMonth.of(year, month).isValidDay(day)){
                    System.out.println("\nTry again");
                }
                else{
                    date = LocalDate.of(year, month, day);
                    quit = true;
                }
            }while(!quit);
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return date;

    }

    public static Integer TimeMenuLoop(){
        Integer choice = 0;
        try{
            Boolean quit = false;
            Scanner sc = new Scanner(System.in);
            
            do{
                System.out.println("1) Pick a Time");
                System.out.println("2) Add a Time");
                System.out.println("3) Cancel");
                System.out.print("> ");
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println();

                if (choice < 1 || choice > 3){
                    System.out.println("Pick a number between 1 and 3");
                }
                else{
                    quit = true;
                }
            }while(!quit);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return choice;
    }

    // trying to speed some things up with how data looks, aka not pretty
    public static LocalTime PickTimeFromList(){
        LocalTime now = LocalTime.now().plusHours(1).truncatedTo(ChronoUnit.HOURS);
        LocalTime time = null;
        Boolean quit = false;
        Scanner sc = new Scanner(System.in);
        Integer input = 0;
        try{
            do{
                for(int i = 0; i < 96; i++){
                    System.out.println(i+1 + ") " + now.plusMinutes(i*15));
                }
                System.out.print("> ");
                input = sc.nextInt();
                sc.nextLine();
                if (input >= 1 && input <= 96){
                    time = now.plusMinutes(15*(input-1));
                    quit = true;
                }
                else{
                    System.out.println("Try Again");
                }
            }while(!quit);

            
        }
        catch(Exception e){
            System.out.println(e);
        }
        

        return time;        
    }

    public static LocalTime AddTime(){
        LocalTime now = LocalTime.now().plusHours(1).truncatedTo(ChronoUnit.HOURS);
        LocalTime time = null;
        Boolean quit = false;
        Scanner sc = new Scanner(System.in);
        Integer hour = 0;
        Integer minutes = 0;

        try{
            do{
                System.out.print("Enter Hour: ");
                hour = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Minutes");
                minutes = sc.nextInt();
                sc.nextLine();
                if (hour >= 0 && hour <= 23 && minutes >= 0 && minutes <= 59){
                    time = LocalTime.of(hour, minutes);
                    quit = true;
                }
            }while(!quit);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return time;
    }


    public static void UPDATEFLIGHTS(){
        Boolean quit = false;
        Scanner sc = new Scanner(System.in);
        Integer input = 0;
        try{
            System.out.println("Pick a flight to update");
            ArrayList<Flight> flights = ReadFlights();
            if(flights.isEmpty()){
                System.out.println("No flights at this time");
            }
            else{
                do{
                    for(int i = 0; i < flights.size(); i++){
                        System.out.println(i+1 + ") " + flights.get(i).toString());
                    }
                    System.out.print("> ");
                    input = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    if (input >= 1 && input <= flights.size()){
                        Flight oldFlight = flights.get(input-1);
                        System.out.print("Enter flight id: ");
                        Integer f_id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter origin iata: ");
                        String o_iata = sc.nextLine();
                        System.out.print("Enter origin city: ");
                        String o_city = sc.nextLine();
                        System.out.print("Enter destination iata: ");
                        String d_iata = sc.nextLine();
                        System.out.print("Enter destination city: ");
                        String d_city = sc.nextLine();
                        System.out.print("Enter time: ");
                        String time = sc.nextLine();
                        System.out.print("Enter reserved seats: ");
                        Integer seats = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter price: ");
                        Double price = sc.nextDouble();
                        sc.nextLine();
                        
                        Flight newFlight = new Flight(f_id, new Airport(o_iata, o_city), new Airport(d_iata, d_city), time, seats, price);

                        UpdateFlights(oldFlight, newFlight);
                        quit = true;
                    }

                }while(!quit);
                

            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void DELETEFLIGHTS(){
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);
        try{
            do{
                System.out.println("Pick a Flight to Delete");
                ArrayList<Flight> flights = ReadFlights();
                if (flights.isEmpty()){
                    System.out.println("No flights to delete");
                    quit = true;
                }
                else{
                    for(int i = 0; i < flights.size(); i++){
                        System.out.println(i+1 + ") " + flights.get(i).toString());
                    }
                    System.out.println();
                    
                    System.out.print("> ");
                    input = sc.nextInt();
                    sc.nextLine();

                    if (input >= 1 && input <= flights.size()){
                        DeleteFlights(flights.get(input-1));
                        ArrayList<Passenger> passengers = getPassengers(flights.get(input-1));
                        for(Passenger i : passengers){
                            DeletePassenger(i);
                        }
                        quit = true;
                    }
                    else{
                        System.out.println("Try Again");
                    }

                }
            }while(!quit);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void SEATS() {
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);

        try{
            do{
                System.out.println("Pick an Option");
                System.out.println("1) Add Seats to Flights");
                System.out.println("2) Update Seats in Flights");
                System.out.println("3) Delete Seats in Flights");
                System.out.println("4) Read Seats on Flights");
                System.out.println("5) Go Back");
                System.out.print("> ");
                input = sc.nextInt();
                sc.nextLine();
                if (input >= 1 && input <= 5){
                    switch (input){
                        case 1:
                            CHANGESEATS();
                            break;

                        case 2:
                            CHANGESEATS();
                            break;

                        case 3:
                            CHANGESEATS();
                            break;

                        case 4:
                            ArrayList<Flight> flights = ReadFlights();
                            if (flights.isEmpty()){
                                System.out.println("No Flights at this time");
                            }
                            else{
                                for(int i = 0; i < flights.size(); i++){
                                    System.out.println(i + 1 + ") " + flights.get(i).toString());
                                }
                            }
                            break;

                        case 5:
                            quit = true;

                        default:
                            System.out.println("Try Again!");
                    }
                }
            }while(!quit);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void CHANGESEATS(){
        Boolean quit = true;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);

        try{
            do{
                ArrayList<Flight> flights = ReadFlights();
                if (flights.isEmpty()){
                    System.out.println("No flights at this time");
                    quit = true;
                }
                else{
                    System.out.println("Pick a flight to change seat count");
                    for(int i = 0; i < flights.size(); i++){
                        System.out.println(i+1 + ") " + flights.get(i).toString());
                    }
                    System.out.print("> ");
                    input = sc.nextInt();
                    sc.nextLine();

                    if (input >= 1 && input <= flights.size()){
                        Flight oldFlight = flights.get(input-1);
                        System.out.print("Enter seats to change to: ");
                        input = sc.nextInt();
                        sc.nextLine();
                        
                        Flight newFlight = new Flight(oldFlight.flight_id, oldFlight.origin, oldFlight.destination, oldFlight.depart_time, input, oldFlight.seat_price);
                        UpdateFlights(oldFlight, newFlight);
                        input = 1;
                        quit = true;
                    }
                    else{
                        System.out.println("Try Again\n");
                    }
                }

            }while(!quit);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void PASSENGERS() {
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);

        try{
            do{
                System.out.println("Pick an Option");
                System.out.println("1) Add Passengers to Flight");
                System.out.println("2) Update Passenger in Flight");
                System.out.println("3) Delete Passenger in Flight");
                System.out.println("4) Read Passengers on Flight");
                System.out.println("5) Go Back");
                System.out.print("> ");
                input = sc.nextInt();
                sc.nextLine();
                if (input >= 1 && input <= 4){
                    switch (input){
                        case 1:
                            CHANGEPASSENGERS(0);
                            break;

                        case 2:
                            CHANGEPASSENGERS(1);
                             break;

                        case 3:
                            CHANGEPASSENGERS(2);
                            break;

                        case 4:
                            READPASSENGERS();
                            break;
                    }
                }
                else if (input == 5){
                    quit = true;
                }
                else{
                    System.out.println("Try Again!\n");
                }
            }while(!quit);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void CHANGEPASSENGERS(Integer process){
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);
        try{
            do{
                if(process == 0){
                    ArrayList<Flight> flights = ReadFlights();
                    if (flights.isEmpty()){
                        System.out.println("No Flights at this time to change");
                        quit = true;
                    }
                    else{
                        System.out.println("Pick a flight to add passenger");
                        for(int i = 0; i < flights.size(); i++){
                            System.out.println(i+1 + ") " + flights.get(i).toString());
                        }
                        System.out.print("> ");
                        input = sc.nextInt();
                        sc.nextLine();

                        if (input >= 1 && input <= flights.size()){
                            Flight flight = flights.get(input-1);
                            if(flight.reserved_seats == 0){
                                System.out.print("Can't add anymore seats");
                            }
                            else{
                                System.out.print("Enter id: ");
                                Integer id = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter full name: ");
                                String full_name = sc.nextLine();
                                Passenger passenger = new Passenger(id, flight.flight_id, full_name);
                                AddPassengers(passenger);
                                Flight newFlight = new Flight(flight.flight_id, flight.origin, flight.destination, flight.depart_time, flight.reserved_seats - 1, flight.seat_price);
                                UpdateFlights(flight, newFlight);
                                quit = true;
                            }
                            

                        }
                        else{
                            System.out.println("Try Again");
                        }
                    }
                }

                else if (process == 1){
                    ArrayList<Flight> flights = ReadFlights();
                    if (flights.isEmpty()){
                        System.out.println("No Flights at this time to change");
                        quit = true;
                    }
                    else{
                        System.out.println("Pick a flight to update passenger");
                        for(int i = 0; i < flights.size(); i++){
                            System.out.println(i+1 + ") " + flights.get(i).toString());
                        }
                        System.out.print("> ");
                        input = sc.nextInt();
                        sc.nextLine();
                        if (input >= 1 && input <= flights.size()){
                            ArrayList<Passenger> passengers = getPassengers(flights.get(input-1));
                            if (passengers.isEmpty()){
                                System.out.println("No passengers in this flight");
                                quit = true;
                            }
                            else{
                                System.out.println("Pick passenger to update");
                                for(int i = 0; i < passengers.size(); i++){
                                    System.out.println(i+1 + ") " + passengers.get(i).toString());
                                }
                                System.out.println();
                                System.out.print("> ");
                                input = sc.nextInt();
                                sc.nextLine();
                                if (input >= 1 && input <= passengers.size()){
                                    Passenger oldPassenger = passengers.get(input - 1);
                                    System.out.print("Enter id: ");
                                    Integer id = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter booking_id: ");
                                    Integer booking_id = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter full_name: ");
                                    String full_name = sc.nextLine();
                                    Passenger newPassenger = new Passenger(id, booking_id, full_name);
                                    UpdatePassengers(oldPassenger, newPassenger);
                                    System.out.println();
                                    quit = true;
                                }
                                else{
                                    System.out.println("Try Again!");
                                    quit = true;
                                }
                                
                            }
                        }
                        else{
                            System.out.println("Try Again");
                        }
                    }
                    
                }

                else if (process == 2){
                    ArrayList<Flight> flights = ReadFlights();
                    if (flights.isEmpty()){
                        System.out.println("No Flights at this time to change");
                        quit = true;
                    }
                    else{
                        System.out.println("Pick a flight to delete passenger from");
                        for(int i = 0; i < flights.size(); i++){
                            System.out.println(i+1 + ") " + flights.get(i).toString());
                        }
                        System.out.print("> ");
                        input = sc.nextInt();
                        sc.nextLine();
                        if (input >= 1 && input <= flights.size()){
                            ArrayList<Passenger> passengers = getPassengers(flights.get(input-1));
                            if (passengers.isEmpty()){
                                System.out.println("No passengers in this flight");
                                quit = true;
                            }
                            else{
                                System.out.println("Pick passenger to delete");
                                for(int i = 0; i < passengers.size(); i++){
                                    System.out.println(i+1 + ") " + passengers.get(i).toString());
                                }
                                System.out.println();
                                System.out.print("> ");
                                input = sc.nextInt();
                                sc.nextLine();
                                if (input >= 1 && input <= passengers.size()){
                                    Passenger passenger = passengers.get(input - 1);
                                    DeletePassenger(passenger);
                                    // update flight_ logs not implemented
                                    quit = true;
                                }
                                else{
                                    System.out.println("Try Again!");
                                    quit = true;
                                }
                                
                            }
                        }
                        else{
                            System.out.println("Try Again");
                        }
                    }

                }
            }while(!quit);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void READPASSENGERS(){
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);
        try{
            do{
                ArrayList<Flight> flights = ReadFlights();
                if (flights.isEmpty()){
                    System.out.println("No Flights at this time to change");
                    quit = true;
                }
                else{
                    for(int i = 0; i < flights.size(); i++){
                        System.out.println(i+1 + ") " + flights.get(i).toString());
                    }
                    System.out.println();
                    System.out.println("Pick Flight to view passengers");
                    System.out.print("> ");
                    input = sc.nextInt();
                    sc.nextLine();
                    Flight flight = flights.get(input-1);
                    ArrayList<Passenger> passengers = getPassengers(flight);
                    if (passengers.isEmpty()){
                        System.out.println("There are no passengers on this flight");
                        quit = true;
                    }
                    else{
                        for(int i = 0; i < passengers.size(); i++){
                            System.out.println(i+1 + ") " + passengers.get(i).toString());
                        }
                        System.out.println();
                        quit = true;
                    }
                    
                }
            }while(!quit);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void AIRPORTS() {
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);

        try{
            do{
                System.out.println("Pick an Option");
                System.out.println("1) Add an airports");
                System.out.println("2) Update an Airport");
                System.out.println("3) Delete an Airport");
                System.out.println("4) Read all Airports");
                System.out.println("5) Go Back");
                System.out.print("> ");
                input = sc.nextInt();
                sc.nextLine();

                switch (input){
                    case 1:
                        ADDAIRPORTS();
                        break;

                    case 2:
                        UPDATEAIRPORTS();
                        break;

                    case 3:
                        DELETEAIRPORTS();
                        break;

                    case 4:
                        ArrayList<Airport> airports = ReadAirports();
                        if (airports.isEmpty()){
                            System.out.println("There are no Airports to List");
                        }
                        else{
                            for(int i = 0; i < airports.size(); i++){
                                System.out.println(i+1 + ") " + airports.get(i).toString());
                            }
                        }
                        break;

                    case 5:
                        quit = true;
                        break;

                    default:
                        System.out.println("Try Again!");
                }
            }while(!quit);

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void ADDAIRPORTS(){
        try{
            AddAirport(new Airport());
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void UPDATEAIRPORTS(){
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);
        try{
            ArrayList<Airport> airports = ReadAirports();
            if (airports.isEmpty()){
                System.out.println("There are no Airports to Update");
            }
            else{
                System.out.println("Pick an Airport to update");
                for(int i = 0; i < airports.size(); i++){
                    System.out.println(i+1 + ") " + airports.get(i).toString());
                }
                System.out.println();
                System.out.print("> ");
                input = sc.nextInt();
                sc.nextLine();
                if (input >= 1 && input <= airports.size()){
                    System.out.println("Enter New Airport information");
                    System.out.print("City Name: ");
                    String city = sc.nextLine(); // not going to bother to check if its a real city;
                    //System.out.println();
                    System.out.print("IATA: ");
                    String iata_id = sc.nextLine().trim();
                    //System.out.println();
                    if (iata_id.length() != 3){
                        System.out.println("IATA not three letters\n");
                    }
                    else{
                        Airport oldAirport = airports.get(input-1);
                        Airport newAirport = new Airport(iata_id, city);
                        UpdateAirports(oldAirport, newAirport);
                    }
                }
                else{
                    System.out.println("Try Again!");
                }
                
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public static void DELETEAIRPORTS(){
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);
        try{
            ArrayList<Airport> airports = ReadAirports();
            if (airports.isEmpty()){
                System.out.println("There are no Airports to Update");
            }
            else{
                System.out.println("Pick an Airport to update");
                for(int i = 0; i < airports.size(); i++){
                    System.out.println(i+1 + ") " + airports.get(i).toString());
                }
                System.out.println();
                System.out.print("> ");
                input = sc.nextInt();
                sc.nextLine();
                if (input >= 1 && input <= airports.size()){
                    Airport airport = airports.get(input - 1);
                    DeleteAirports(airport);
                }
                else{
                    System.out.println("Try Again!");
                }
            }


        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    public static void EMPLOYEES() {
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);

        try{
            do{
                System.out.println("Pick an Option");
                System.out.println("1) Add an Employee");
                System.out.println("2) Update an Employee");
                System.out.println("3) Delete an Employee");
                System.out.println("4) Read Employees");
                System.out.println("5) Go Back");
                System.out.print("> ");
                input = sc.nextInt();
                sc.nextLine();

                if (input >= 1 && input <= 5){
                    switch (input){
                        case 1:
                            ADDEMPLOYEES();
                            break;

                        case 2:
                            UPDATEEMPLOYEES();
                            break;
                        
                        case 3:
                            DELETEEMPLOYEES();
                            break;

                        case 4:
                            ArrayList<Employee> employees = ReadEmployees();
                            if (employees.isEmpty()){
                                System.out.println("There are no Employees at this time");
                            }
                            else{
                                for(int i = 0; i < employees.size(); i++){
                                    System.out.println(i+1 + ") " + employees.get(i).toString());
                                }
                            }
                            break;

                        case 5:
                            quit = true;
                            break;

                        default:
                            System.out.println("Try Again!");
                    }
                }
                else{
                    System.out.println("Try Again");
                }

            }while(!quit);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void ADDEMPLOYEES(){
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);

        try{
            System.out.print("Enter id: ");
            Integer id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter role id: ");
            Integer role_id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter first name: ");
            String f_name = sc.nextLine();
            System.out.print("Enter last name: ");
            String l_name = sc.nextLine();
            Employee employee = new Employee(id, role_id, f_name, l_name);
            AddEmployees(employee);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void UPDATEEMPLOYEES(){
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);

        try{
            ArrayList<Employee> employees = ReadEmployees();
            if (employees.isEmpty()){
                System.out.println("There are no employees a this time");
            }
            else{
                System.out.println("Pick an employee to update");
                for(int i = 0; i < employees.size(); i++){
                    System.out.println(i+1 + ") " + employees.get(i).toString());
                }
                System.out.println();
                
                System.out.print("> ");
                input = sc.nextInt();
                if(input >= 1 && input <= employees.size()){
                    Employee oldEmployee = employees.get(input - 1);

                    System.out.print("Enter id: ");
                    Integer id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter role id: ");
                    Integer role_id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter first name: ");
                    String f_name = sc.nextLine();
                    System.out.print("Enter last name: ");
                    String l_name = sc.nextLine();
                    Employee newEmployee = new Employee(id, role_id, f_name, l_name);
                    UpdateEmployees(oldEmployee, newEmployee);
                }
                else{
                    System.out.println("Try Again!");
                }
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void DELETEEMPLOYEES(){
        Boolean quit = false;
        Integer input = 0;
        Scanner sc = new Scanner(System.in);

        try{
            ArrayList<Employee> employees = ReadEmployees();
            if (employees.isEmpty()){
                System.out.println("There are no employees a this time");
            }
            else{
                System.out.println("Pick an employee to delete");
                for(int i = 0; i < employees.size(); i++){
                    System.out.println(i+1 + ") " + employees.get(i).toString());
                }
                System.out.println();
                
                System.out.print("> ");
                input = sc.nextInt();
                if(input >= 1 && input <= employees.size()){
                    Employee employee = employees.get(input - 1);
                    DeleteEmployees(employee);
                }
                else{
                    System.out.println("Try Again!");
                }
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
