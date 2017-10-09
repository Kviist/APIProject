package api;

import com.google.gson.*;
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
        JsonObject response = jsonArrayBuilder(resource.get(String.class));
        createSmhiDataclass(response);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String prettyJson = gson.toJson(response);
//        System.out.println(prettyJson + "\n");
    }

    public JsonObject jsonArrayBuilder(String jsonString){
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(jsonString).getAsJsonObject();
        return jsonObj;

    }



    private void createSmhiDataclass(JsonObject jsonObj){
                //Kod för att leta sig ner till JSON-objekten lat och lng.
        JsonArray jsonArray = jsonObj.getAsJsonArray("timeSeries");
        jsonObj = jsonArray.get(0).getAsJsonObject();
        jsonArray = jsonObj.getAsJsonArray("parameters");
        System.out.println(jsonObj);
//        jsonObj = jsonObj.get("geometry").getAsJsonObject();
//        jsonObj = jsonObj.get("location").getAsJsonObject();
//        String lat = jsonObj.get("lat").getAsString();
//        String lon = jsonObj.get("lng").getAsString();
    }

    public static void main(String[] args){
        SmhiAPIClient client = new SmhiAPIClient("13", "54");
    }



}


