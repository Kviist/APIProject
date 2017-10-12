package server;

import api.MusixMatchAPIClient;
import spark.Response;
import spark.Spark.*;

import static spark.Spark.*;


public class ServerMain {

	final String weatherData = "/weatherdatasets/:location";
	private ServerController controller;

	public ServerMain(){
		port(7313);
		controller =  new ServerController();

		System.out.println("Server Started: Listening on port 7313");

		before((request, response) ->
				response.header("Access-Control-Allow-Origin", "*"));

		get(weatherData, (request, response) -> {
			String location = request.params(":location");
			String res = controller.getWeather(location);
			System.out.println();


			return res;
		});
	}






	public static void main(String[] args) {
		new ServerMain();


	}
}
