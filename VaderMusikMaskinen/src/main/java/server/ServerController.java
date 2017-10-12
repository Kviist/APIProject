package server;

import api.*;
import dataclasses.*;

public class ServerController {

	
	public ServerController() {

	}


	public String getWeather(String city){
		SmhiAPIClient smhi;
		String res ="";
		Coordinates coordinates = GoogleMapsAPIClient.requestCoordinates(city);
	try {
//
//		if (Double.parseDouble(coordinates.getLon()) > 2.25 && Double.parseDouble(coordinates.getLon()) < 25) {
//
//			if (Double.parseDouble(coordinates.getLat()) > 53 && Double.parseDouble(coordinates.getLat()) < 70) ;
//			{
				smhi = new SmhiAPIClient((coordinates.getLon()), coordinates.getLat());
				res = smhi.getSmhidata().toString();

//			}
//		}
	}catch(Exception e){
		e.printStackTrace();
		return "GICK ENTE DIN JEVLA FIDDAJEVEL KUG HORA";
	}

		return res;
	}

	public String getLyrics(){
		return "";
	}

	public static void main(String[] args){
		ServerController cont = new ServerController();
		cont.getWeather("Malmö");
	}

}
