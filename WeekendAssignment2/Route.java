public class Route{
    //Integer id = null;
    Airport origin_id = null;
    Airport destination_id = null;
    String classType = "Route";

    Route(Airport origin, Airport destination){
        origin_id = origin;
        destination_id = destination;
    }

    public String getOriginIATA(){
        return origin_id.getIATA();
    }

    public String getDestinationIATA(){
        return destination_id.getIATA();
    }

    public String getOriginCity(){
        return origin_id.getCity();
    }

    public String getDestinationCity(){
        return destination_id.getCity();
    }

}