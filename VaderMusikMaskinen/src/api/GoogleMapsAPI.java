package api;

import static spark.Spark.*;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;

import dataclasses.Coordinates;

public class GoogleMapsAPI {
	
//	public static void requestCoordinates(String city) throws Exception {
//		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+ city +"&key=AIzaSyDrA1RHHqNSi7BVZZofVtWDXrBGVSgpGn0");
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//
//		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		String inputLine;
//		StringBuilder json = new StringBuilder();
//
//		while ((inputLine = in.readLine()) != null)
//			json.append(inputLine);
//		in.close();
//		System.out.println(json);
//	}
	
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
        Gson gson = new Gson();
        Coordinates coords = gson.fromJson(json, Coordinates.class);
        System.out.println(json);
        
//        double lat = coords.lat, lng = coords.lng;
        
        System.out.println(coords.lat + " och " + coords.lng);
    }
}
