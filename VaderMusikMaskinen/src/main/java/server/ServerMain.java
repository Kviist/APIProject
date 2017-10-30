package server;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.wrapper.spotify.models.Playlist;
import dataclasses.Track;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import static spark.Spark.*;

public class ServerMain {

	final String weatherData = "/v1/weatherdatasets/:location";
	final String playlists ="/v1/musiclibrary/playlists/:weather";
	final String tracks ="/v1/musiclibrary/tracks/:weather";
	final String lyrics ="/v1/musiclibrary/lyrics/song/*/artist/*";
	private ServerController controller;
	private Gson gson;

	public ServerMain(){
		ipAddress("192.168.0.45");
		port(7313);
		controller =  new ServerController();
		gson = new Gson();
		
		System.out.println("Server Started: Listening on port 7313");

		before((request, response) ->
				response.header("Access-Control-Allow-Origin", "*"));

		get(weatherData, (request, response) -> {
			String location = request.params(":location");
			String res = controller.getWeatherTranslation(location);
			if(res.equals("ERROR PARSING CHOSEN LOCATION"))
				response.status(400);
			response.status(200);
			return res;
		});

		get(playlists, (request, response) -> {
			String weather = request.params(":weather");
			controller.fetchPlaylistByWeather(weather);
			Playlist res = controller.getPlaylistName(weather);
			Type type = new TypeToken<Playlist>() {}.getType();
			String json = gson.toJson(res, type);
			response.status(200);
			return json;
		});

		get(tracks, (request, response) -> {
			String weather = request.params(":weather");
			controller.fetchPlaylistByWeather(weather);
			List<Track> res = controller.getTracks(weather);
			Type type = new TypeToken<LinkedList<Track>>() {}.getType();
			String json = gson.toJson(res, type);
			response.status(200);
			return json;
		});
		
		get(lyrics, (request, response) -> {
			String song = request.splat()[0];
			String artist = request.splat()[1];

			String temp = (controller.getLyricsWithSongNameAndAristName(song, artist));

			response.status(200);
			return temp;
		});
	}

	public static void main(String[] args) {
		new ServerMain();
	}
}
