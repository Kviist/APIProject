package api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


public class SmhiAPIClient {

    static String URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point";
    private String lon;
    private String lat;

    public SmhiAPIClient(String lon, String lat){
        Client client = Client.create();
        this.lon=lon;
        this.lat=lat;
        WebResource resource = client.resource(URL + "/lon/" + lon + "/lat/" + lat + "/data.json");
        String response = resource.get(String.class);
        System.out.println(response);
    }

    public static void main(String[] args){
        SmhiAPIClient client = new SmhiAPIClient("13", "54");
    }



}


