package dataclasses;

public class Coordinates {
	private String lat;
	private String lon;
	
	public Coordinates(String lat, String lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}
	
	@Override
	public String toString() {
		return lat + ", " + lon;
	}
}
