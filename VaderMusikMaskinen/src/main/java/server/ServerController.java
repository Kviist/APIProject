package server;

import api.*;
import dataclasses.*;

/**
 * @author Petter Månsson 2107-10-12
 * Controls the flow of data within the application
 */

public class ServerController {
	
	private final String[] weatherTranslator =  {"KLART","KLART","HALVMULET","HALVMULET","MOLNIGT","MOLNIGT","DIMMA" +
											"REGN","REGN","KRAFTIGT REGN","ÅSKA","SNÖBLANDAT REGN","SNÖBLANDAT REGN","SLASK" +
											"GANSKA JULIGT", "GANSKA JULIGT","ULTRA JULIGT","REGN","REGN","KRAFTIGT REGN","ÅSKA" +
											"SNÖBLANDAT REGN","SNÖBLANDAT REGN","SLASK","GANSKA JULIGT","ULTRA JULIGT"};
	private MusixMatchAPIClient musixClient;
	
	public ServerController() {
		musixClient = new MusixMatchAPIClient();
	}

	//Private method for translating weather symbol to a string
	private String weatherTranslate(String weatherSymbol, Boolean drizzle) {
		System.out.println(weatherSymbol);
		int i = Integer.parseInt(weatherSymbol);

		String translatedWeather = "";
		if(drizzle){
			translatedWeather = "Drizzle";
		}else{
			translatedWeather = weatherTranslator[i];
		}
			return translatedWeather;
	}

	/**
	 * Returns what type of weather it is on a specified location within the hour.
	 * @param city
	 * @return
	 */

	public String getWeatherTranslation(String city){
		SmhiAPIClient smhi;
		String res ="";
		Coordinates coordinates = GoogleMapsAPIClient.requestCoordinates(city);
	try {

		if (Double.parseDouble(coordinates.getLon()) > 2.25 && Double.parseDouble(coordinates.getLon()) < 25) {
			if (Double.parseDouble(coordinates.getLat()) > 53 && Double.parseDouble(coordinates.getLat()) < 70) ;
			{
				smhi = new SmhiAPIClient((coordinates.getLon()), coordinates.getLat());
				System.out.println(smhi.getSmhidata().toString());
				res = weatherTranslate(smhi.getSmhidata().getWsymb(), smhi.getSmhidata().getDrizzle()) ;

			}
		}else{
			smhi = new SmhiAPIClient("13.027149", "55.588239"); //Error handeling incase unallowed location is entered. Default location Bagdad Falafel Malmö Sweden
			System.out.println(smhi.getSmhidata().toString());
			res = weatherTranslate(smhi.getSmhidata().getWsymb(), smhi.getSmhidata().getDrizzle()) ;

		}
	}catch(Exception e){
		e.printStackTrace();
		return "ERROR PARSING CHOSEN LOCATION";
	}
		return res;
	}

	public String getLyricsWithSongNameAndAristName(String songName, String artistName){
		int trackID = musixClient.searchForSongReturnTrackID(songName, artistName);
		return musixClient.toString(trackID);
	}



	public static void main(String[] args){
		ServerController cont = new ServerController();
		cont.getWeatherTranslation("Malmö");
	}

}
