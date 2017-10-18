package server;

import api.MusixMatchAPIClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.wrapper.spotify.models.Playlist;
import dataclasses.Track;
import spark.Response;
import spark.Spark.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static spark.Spark.*;


public class ServerMain {

	final String weatherData = "/weatherdatasets/:location";
	final String playlistName ="/playlistName/:weather";
	final String tracks ="/tracks/:weather";
	final String lyrics ="/lyrics/:songArtistName";
	private ServerController controller;
	private Gson gson;

	public ServerMain(){
		port(7313);
		controller =  new ServerController();
		gson = new Gson();
		
		System.out.println("Server Started: Listening on port 7313");

		before((request, response) ->
				response.header("Access-Control-Allow-Origin", "*"));

		get(weatherData, (request, response) -> {
			String location = request.params(":location");
			String res = controller.getWeatherTranslation(location);
			return res;
		});

		get(weatherData, (request, response) -> {
			String location = request.params(":location");
			String res = controller.getWeatherTranslation(location);
			System.out.println();

			return res;
		});

		get(playlistName, (request, response) -> {
			String weather = request.params(":weather");
			controller.fetchPlaylistByWeather(weather);
			Playlist res = controller.getPlaylistName(weather);
			Type type = new TypeToken<Playlist>() {}.getType();
			String json = gson.toJson(res, type);
			System.out.println(json);
			return json;

		});

		get(tracks, (request, response) -> {
			String weather = request.params(":weather");
			controller.fetchPlaylistByWeather(weather);
			List<Track> res = controller.getTracks(weather);
			Type type = new TypeToken<LinkedList<Track>>() {}.getType();
			String json = gson.toJson(res, type);
			System.out.println(json);
			return json;
		});
		
		get(lyrics, (request, response) -> {
			String songArtistName = request.params(":songArtistName");
			songArtistName = songArtistName.trim();
			String[] splited = songArtistName.split("--");
			String songName = splited[0];
			String artistName = splited[1];
			String temp = (controller.getLyricsWithSongNameAndAristName(songName, artistName));
			return temp;
		});
	}

	public static void main(String[] args) {
		new ServerMain();


	}
}
