package api;

import org.jmusixmatch.*;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

import java.util.List;

import javax.swing.JOptionPane;

/**
 * Class that comunicates with the MusixMatch API and fetches data from it such as lyrics and trackID
 */
public class MusixMatchAPIClient {

   
	private String trackName;
	private String artistName;
	private String apiKey = "83510f47786b9682ce316929191a00ff";
	private MusixMatch musixMatch = new MusixMatch(apiKey);
	
	/**
	 * Method used to translate a song name and a artist name to a MusixMatch trackID and automatically sets song to Never gonna give you up by Rick Astley
	 * @param song - name of the song
	 * @param artist - name of the artist
	 * @return - MusixMatch ID
	 */
	public int searchForSongReturnTrackID(String song, String artist) {
		String trackName = song;
        String artistName = artist;

		try {
			Track track = musixMatch.getMatchingTrack(trackName, artistName);
			TrackData data = track.getTrack();
			
			return data.getTrackId();
		} catch (MusixMatchException e) {
			try {
				Track track = musixMatch.getMatchingTrack("Never gonna give you", "Rick Astly");
				TrackData data = track.getTrack();

				return data.getTrackId();
			} catch(MusixMatchException e2){
				return 0;
			}
		}

    }
    
	/**
	 * Method used to get song name with MusixMatch ID
	 * @param trackID - MusixMatch ID
	 * @return - Name of the song
	 */
    public String getSongWithTrackID(int trackID) {
    	try {
			Track track = musixMatch.getTrack(trackID);
			TrackData data = track.getTrack();
			
			return data.getTrackName();
		} catch (MusixMatchException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
	 * Method used to get artist name with MusixMatch ID
	 * @param trackID - MusixMatch ID
	 * @return - Name of the artist
	 */
    public String getArtistWithTrackID(int trackID) {
    	try {
			Track track = musixMatch.getTrack(trackID);
			TrackData data = track.getTrack();
			
			return data.getArtistName();
		} catch (MusixMatchException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
	 * Method used to get album name with MusixMatch ID
	 * @param trackID - MusixMatch ID
	 * @return - Name of the album
	 */
    public String getAlbumWithTrackID(int trackID) {
    	try {
			Track track = musixMatch.getTrack(trackID);
			TrackData data = track.getTrack();

	        return data.getAlbumName();
		} catch (MusixMatchException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
	 * Method used to get lyrics with MusixMatch ID
	 * @param trackID - MusixMatch ID
	 * @return - lyrics of the song
	 */
    public String getLyricsWithTrackID(int trackID) {
    	try {
    		Lyrics lyrics = musixMatch.getLyrics(trackID);
    	    String temp = ("\n" + lyrics.getLyricsBody());

    	    return temp;
		} catch (MusixMatchException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
	 * Method used to print out Lyrics, track name, artist name for a specfic song formated for HTML page with br tags
	 * @param trackID - MusixMatch ID
	 * @return - String formatted with br tags with artist name, song name and lyrics
	 */
    public String toString(int trackID) { 	
    	try {
			Track track = musixMatch.getTrack(trackID);
			TrackData data = track.getTrack();
			Lyrics lyrics = musixMatch.getLyrics(trackID);
			String temp = (data.getTrackName());
			temp += ("<br>" + data.getArtistName());
			temp += ("<br>" + data.getAlbumName() + "<p>");
			String[] split = lyrics.getLyricsBody().split("\n");
			for(int i=0; i<split.length; i++) {
				temp += (split[i] + "<br>");
			}
			
    	    return temp; 
		} catch (MusixMatchException e) {
			try {
				Track track = musixMatch.getMatchingTrack("Never gonna give you", "Rick Astly");
				TrackData data = track.getTrack();
				Lyrics lyrics = musixMatch.getLyrics(trackID);
				String temp = (data.getTrackName());
				temp += ("<br>" + data.getArtistName());
				temp += ("<br>" + data.getAlbumName() + "<p>");
				String[] split = lyrics.getLyricsBody().split("\n");
				for(int i=0; i<split.length; i++) {
					temp += (split[i] + "<br>");
				}
				return temp;
			} catch(MusixMatchException e2){
				return "";
			}
		}
    }
    
    public static void main(String[] args) {
    	int trackID;
    	String artistName, trackName;
    	MusixMatchAPIClient api = new MusixMatchAPIClient();
    	trackName = JOptionPane.showInputDialog("Skriv in låtnamn: ");
    	artistName = JOptionPane.showInputDialog("Skriv in artistnamn: ");
    	trackID = api.searchForSongReturnTrackID(trackName, artistName);
    	/* System.out.println(api.getSongWithTrackID(trackID));
    	System.out.println(api.getArtistWithTrackID(trackID));
    	System.out.println(api.getAlbumWithTrackID(trackID));
    	System.out.println(api.getLyricsWithTrackID(trackID));
    	*/

    	System.out.print(api.toString(trackID));
    }
}
