import java.time.LocalDateTime;

public class Flight{
    Integer flight_id = 0;
    Airport origin;
    Airport destination;
    String depart_time = LocalDateTime.now().toString();
    Integer reserved_seats = 100;
    Double seat_price = 50.00;
    String classType = "Flight";
    Flight(Integer flight_id, Airport origin, Airport destination, String depart_time, Integer reserved_seats, double seat_price){
        this.flight_id = flight_id;
        this.origin = origin;
        this.destination = destination;
        this.depart_time = depart_time;
        this.reserved_seats = reserved_seats;
        this.seat_price = seat_price;
    }
    
    @Override
    public String toString() {
        return String.format("id:%d  %s -> %s @%s for $%f with %d seats available", flight_id, origin.toString(), destination.toString(), depart_time, seat_price, reserved_seats);
    }

    public String toStringConsumer(){
        return String.format("%s -> %s", origin.toString(), destination.toString());
    }
}