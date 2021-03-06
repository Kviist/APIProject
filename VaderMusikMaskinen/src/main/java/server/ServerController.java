package server;

import api.*;
import com.wrapper.spotify.models.Playlist;
import dataclasses.*;

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

	/**
	 * Method for fetching lyrics entering a song name and a artist name
	 * @param - songName: name of the song
	 * @param - artistName: name of the artist
	 * @return - String: MusixMatch trackID
	 */
	public String getLyricsWithSongNameAndAristName(String songName, String artistName){
		String[] liveSessions;
		String song = songName.toLowerCase();
		if(song.contains("live")|| song.contains("spotify") || song.contains("recorded")) {
			liveSessions = songName.split("-");
			songName = liveSessions[0];
		}
		try {
			int trackID = musixClient.searchForSongReturnTrackID(songName, artistName);
			return musixClient.toString(trackID);
		} catch(Exception e){
			return "Lyric is not avalible";
		}

	}

	/**
	 * Method for fetching a playlist
	 * @param playListId - id of the playlist from which the name should be fetched
	 * @return - returns the playlist
	 */
	public Playlist getPlaylist(String playListId){
		return spotifyData.getPlaylist();
	}

	/**
	 * Fetched a playlist based on a weather
	 * @param weather - the weather from which the playlist should be chosen
	 */
	public void fetchPlaylistByWeather(String weather){
		spotifyData = new SpotifyData(spotifyClient.getPlaylist("spotify", pid.getId(weather)));
		System.out.println("Spellista hämtad");
	}

	/**
	 * Fetches the tracks from a playlist, chosen by a specified weather
	 * @param weather - the weather which decides the playlist
	 * @return - List of tracks in the playlist
	 */
	public List<Track> getTracks(String weather){
		spotifyData = new SpotifyData(spotifyClient.getPlaylist("spotify", pid.getId(weather)));
		return spotifyData.getTracks();
	}



	public static void main(String[] args){
		ServerController cont = new ServerController();
		cont.getWeatherTranslation("Malmö");

	}

}
