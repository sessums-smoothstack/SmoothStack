public class Airport{
    String iata_id = null;
    String city = null;
    String classType = "Airport";

    Airport(){
        this.iata_id = "SAM";
        this.city = "Sessums";
    }

    Airport(String iata_id, String city){
        this.iata_id = iata_id;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", iata_id, city);
    }

    public String getIATA(){
        return iata_id;
    }

    public String getCity(){
        return city;
    }
}