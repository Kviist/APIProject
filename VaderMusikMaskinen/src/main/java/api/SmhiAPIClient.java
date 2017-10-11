package api;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import dataclasses.SmhiData;


public class SmhiAPIClient {

    static String URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point";
    private String lon;
    private String lat;

    public SmhiAPIClient(String lon, String lat){
        Client client = Client.create();
        this.lon=lon;
        this.lat=lat;
        WebResource resource = client.resource(URL + "/lon/" + lon + "/lat/" + lat + "/data.json"); // Request to SMHI
        JsonObject response = jsonArrayBuilder(resource.get(String.class)); // Response from SHMI
        createSmhiDataclass(response);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String prettyJson = gson.toJson(response);
//        System.out.println(prettyJson + "\n");
    }


    /**
     * Takes a String formatted as Json and converts it into a JSON object
     * @param jsonString
     * @return
     */
    public JsonObject jsonArrayBuilder(String jsonString){
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(jsonString).getAsJsonObject();
        return jsonObj;

    }


    /**
     * Method for extracting data from the response from SMHI and sending it to a class for containing said data.
     * Data extracted is the following: Temperature in C, WindSpeed in m/s, ThunderProbability int 0-100, Precipitation category int 0-6.
     * @param jsonObj
     */
    private void createSmhiDataclass(JsonObject jsonObj){
                //Kod för att leta sig ner till JSON-objekten lat och lng.
        String[] weatherValues = new String[4];
        int k = 0;

        JsonArray jsonArray = jsonObj.getAsJsonArray("timeSeries");
        jsonObj = jsonArray.get(0).getAsJsonObject();
        jsonArray = jsonObj.getAsJsonArray("parameters");
        for(int i = 0; i < jsonArray.size(); i++) {
            jsonObj = jsonArray.get(i).getAsJsonObject();
            String name = jsonObj.get("name").getAsString();
            for(WeatherData w : WeatherData.values()) {
                if (name.equals(w.toString())) {
                    weatherValues[k]=(jsonObj.get("values").getAsString());
                    k++;
                }
            }
        }
        new SmhiData(weatherValues);
    }

    public enum WeatherData {
        T("t"),PCAT("pcat"),TSTM("tstm"),WS("ws");

        private final String text;

        WeatherData(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
    public static void main(String[] args){
        SmhiAPIClient client = new SmhiAPIClient("13", "54");
    }



}


