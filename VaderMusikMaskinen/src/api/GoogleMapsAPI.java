package api;

import static spark.Spark.*;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dataclasses.Coordinates;



public class GoogleMapsAPI {
	
	public static void requestCoordinates(String city) throws Exception {
		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+ city +"&key=AIzaSyDrA1RHHqNSi7BVZZofVtWDXrBGVSgpGn0");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder jsonBuilder = new StringBuilder();

		while ((inputLine = in.readLine()) != null)
			jsonBuilder.append(inputLine);
		in.close();
		System.out.println(jsonBuilder);
		
		String jsonString = jsonBuilder.substring(0);
	}
	
	
	
	public static void main(String[] args) throws Exception {
//		requestCoordinates("Malmö");
		
        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyDrA1RHHqNSi7BVZZofVtWDXrBGVSgpGn0");
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
        
        String json = jsonBuilder.substring(0);

        System.out.println(json);
        
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(json).getAsJsonObject();
        JsonArray results = obj.getAsJsonArray("results");
        System.out.println(results);
        
        obj = results.get(0).getAsJsonObject();
        System.out.println(obj);
        
        obj = obj.get("geometry").getAsJsonObject();
        obj = obj.get("location").getAsJsonObject();
        String lat = obj.get("lat").getAsString();
        String lng = obj.get("lng").getAsString();
        System.out.println(lat + ", " + lng);
        
        Coordinates coords = new Coordinates();
        coords.lat = lat;
        coords.lng = lng;
        
//        for (int i = 0; i < results.size(); i++) {
//        	  JsonElement element = results.get(i);
//
//        	  //getting particular_key...
//        	  if(element.getAsString() == "lat");
//        	  String lat = element.getAsString();
//        	  
//        	  if(element.getAsString() == "lng");
//        	  String lng = element.getAsString();
//        	}
       
        
//        String lat = obj.get("geometry.location.lat").getAsString();
//        String lng = obj.get("geometry.location.lng").getAsString();
        
//        double lat = coords.lat, lng = coords.lng;
        
//        System.out.println(lat + " och " + lng);
    }
}
