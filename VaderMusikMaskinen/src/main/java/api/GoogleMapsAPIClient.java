package api;


import java.io.*;
import java.net.*;

import com.google.gson.*;

import dataclasses.Coordinates;

public class GoogleMapsAPIClient {
	
	public GoogleMapsAPIClient() {
		
	}
	
	public static Coordinates requestCoordinates(String city) {
		try {
		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+ city +"&key=AIzaSyDrA1RHHqNSi7BVZZofVtWDXrBGVSgpGn0");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("content-type", "application/json; charset=UTF-8");
        
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                conn.getInputStream()));
        String inputLine;
        StringBuilder jsonBuilder = new StringBuilder();

        while ((inputLine = in.readLine()) != null) 
            jsonBuilder.append(inputLine);
        in.close();
        
        String jsonString = jsonBuilder.substring(0);
        
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(jsonString).getAsJsonObject();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(jsonObj);
        System.out.println(prettyJson + "\n");
        
        //Kod för att leta sig ner till JSON-objekten lat och lng.
        JsonArray jsonArray = jsonObj.getAsJsonArray("results");
        jsonObj = jsonArray.get(0).getAsJsonObject(); 
        jsonObj = jsonObj.get("geometry").getAsJsonObject();
        jsonObj = jsonObj.get("location").getAsJsonObject();
        String lat = jsonObj.get("lat").getAsString();
        String lon = jsonObj.get("lng").getAsString();
        
        return new Coordinates(lat, lon);
		} catch (Exception e) {
			System.out.println("Invalid input to GoogleMaps: returned Malmö coordinates");
			return new Coordinates("55", "13");
		}
	}
	
	public static void main(String[] args) throws Exception {
		Coordinates coords = requestCoordinates("Malmö");
		System.out.println(coords.getLat() + ", " + coords.getLon());
    }
}
