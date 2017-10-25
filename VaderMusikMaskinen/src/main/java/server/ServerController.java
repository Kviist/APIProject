package server;

import api.*;
import com.wrapper.spotify.models.Playlist;
import dataclasses.*;
import org.jmusixmatch.MusixMatchException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Petter Månsson 2107-10-12
 * Controls the flow of data within the application
 */

public class ServerController {
	private final String[] weatherTranslator =  {"KLART","KLART","HALVMULET","HALVMULET","MOLNIGT","MOLNIGT","DIMMA",
											"REGN","REGN","KRAFTIGT REGN","ÅSKA","SNÖBLANDAT REGN","SNÖBLANDAT REGN","SLASK",
											"GANSKA JULIGT", "GANSKA JULIGT","ULTRA JULIGT","REGN","REGN","KRAFTIGT REGN","ÅSKA",
											"SNÖBLANDAT REGN","SNÖBLANDAT REGN","SLASK","GANSKA JULIGT","ULTRA JULIGT"};
	private SpotifyData spotifyData;
	private SpotifyApiClient spotifyClient;
	private PlaylistID pid;
	private MusixMatchAPIClient musixClient;

	public ServerController() {
		spotifyClient = new SpotifyApiClient();
		pid =  new PlaylistID();
		musixClient = new MusixMatchAPIClient();
	}

	//Private method for translating weather symbol to a string
	private String weatherTranslate(String weatherSymbol, Boolean drizzle) {
		int i = Integer.parseInt(weatherSymbol);

		String translatedWeather = "";
		if(drizzle){
			translatedWeather = "Drizzle";
		}else{
			translatedWeather = weatherTranslator[i-1];
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

		if (Double.parseDouble(coordinates.getLon()) > 2.25 && Double.parseDouble(coordinates.getLon()) < 25) {
			if (Double.parseDouble(coordinates.getLat()) > 53 && Double.parseDouble(coordinates.getLat()) < 70) ;
			{
				smhi = new SmhiAPIClient((coordinates.getLon()), coordinates.getLat());
				res = weatherTranslate(smhi.getSmhidata().getWsymb(), smhi.getSmhidata().getDrizzle()) ;

			}
		}else{
			return "ERROR PARSING CHOSEN LOCATION";
		}

		return res;
	}

	public String getLyricsWithSongNameAndAristName(String songName, String artistName){
		try {
			int trackID = musixClient.searchForSongReturnTrackID(songName, artistName);
			return musixClient.toString(trackID);
		} catch(Exception e){
			return "Lyric is not avalible";
		}

	}

	public Playlist getPlaylistName(String playListId){

		return spotifyData.getPlaylistName();
	}

	public void fetchPlaylistByWeather(String weather){
		spotifyData = new SpotifyData(spotifyClient.getPlaylist("spotify", pid.getId(weather)));
		System.out.println("Spellista hämtad");
	}

	public List<Track> getTracks(String weather){
		spotifyData = new SpotifyData(spotifyClient.getPlaylist("spotify", pid.getId(weather)));
		return spotifyData.getTracks();
	}



	public static void main(String[] args){
		ServerController cont = new ServerController();
		cont.getWeatherTranslation("Malmö");

	}

}
