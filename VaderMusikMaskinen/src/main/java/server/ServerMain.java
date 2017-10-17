package server;

import api.MusixMatchAPIClient;
import spark.Response;
import spark.Spark.*;

import static spark.Spark.*;


public class ServerMain {

	final String weatherData = "/weatherdatasets/:location";
	private ServerController controller;
	private String lyrics;
	
	public ServerMain(){
		port(7313);
		controller =  new ServerController();

		System.out.println("Server Started: Listening on port 7313");

		before((request, response) ->
				response.header("Access-Control-Allow-Origin", "*"));

		get(weatherData, (request, response) -> {
			String location = request.params(":location");
			String res = controller.getWeatherTranslation(location);
			System.out.println();


			return res;
		});
		
		get(lyrics, (request, response) -> {
			String songName = request.params("songName");
			String artistName = request.params("artistName");
			String temp = (controller.getLyricsWithSongNameAndAristName(songName, artistName));
			return temp;
		});
	}

	public static void main(String[] args) {
		new ServerMain();


	}
}
