import java.time.LocalDate;

public class Passenger{
    Integer id = null;
    Integer booking_id = null;
    String full_name = "Sam Sessums";
    String classType = "Passenger";

    Passenger(Integer id, Integer booking_id, String full_name){
        this.id = id;
        this.booking_id = booking_id;
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return String.format("id: %d booking_id:%d full_name:%s", id, booking_id, full_name);
    }
}