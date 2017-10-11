package server;

import api.*;
import dataclasses.*;

public class ServerController {
	
	public ServerController(String city) {
		Coordinates coordinates = GoogleMapsAPIClient.requestCoordinates(city);
		SmhiAPIClient smhi = new SmhiAPIClient(coordinates.getLon(), coordinates.getLat());
	}
	
	public static void main(String[] args) {
		ServerController controller = new ServerController("");
	}
}
