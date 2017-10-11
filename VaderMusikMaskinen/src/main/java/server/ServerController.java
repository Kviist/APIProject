package main.java.server;

import main.java.api.*;
import main.java.dataclasses.*;

public class ServerController {
	
	public ServerController(String city) {
		Coordinates coordinates = GoogleMapsAPIClient.requestCoordinates(city);
		if(Double.parseDouble(coordinates.getLon()) > 2.25 && Double.parseDouble(coordinates.getLon()) < 25) {
			if(Double.parseDouble(coordinates.getLat()) > 53 && Double.parseDouble(coordinates.getLat()) < 70); {
				SmhiAPIClient smhi = new SmhiAPIClient(coordinates.getLon(), coordinates.getLat());
			}
		}
	}
	
	public static void main(String[] args) {
		ServerController controller = new ServerController("Malmö");
	}
}
