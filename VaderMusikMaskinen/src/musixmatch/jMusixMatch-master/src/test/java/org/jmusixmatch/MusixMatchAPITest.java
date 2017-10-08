package org.jmusixmatch;

import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import javax.swing.JOptionPane;

public class MusixMatchAPITest {

   
	private String trackName;
	private String artistName;
	private String apiKey = "83510f47786b9682ce316929191a00ff";
	private MusixMatch musixMatch = new MusixMatch(apiKey);
	
	public int searchForSongReturnTrackID(String song, String artist) {
		String trackName = song;
        String artistName = artist;

		try {
			Track track = musixMatch.getMatchingTrack(trackName, artistName);
			TrackData data = track.getTrack();
			
			return data.getTrackId();
		} catch (MusixMatchException e) {
			e.printStackTrace();
		}
        return 0;
    }
    
    public void findSongWithTrackID(int trackID) {
    	try {
			Track track = musixMatch.getTrack(trackID);
			TrackData data = track.getTrack();
			
			System.out.println("Track Name: " + data.getTrackName());
			System.out.println("Artist Name: " + data.getArtistName());
	        System.out.println("Album Name: " + data.getAlbumName());
		} catch (MusixMatchException e) {
			e.printStackTrace();
		}
    }
    
    public void getLyricsWithTrackID(int trackID) {
    	try {
    		Lyrics lyrics = musixMatch.getLyrics(trackID);
    		
    	    System.out.println("\n" + lyrics.getLyricsBody());
		} catch (MusixMatchException e) {
			e.printStackTrace();
		}
    }
	
//    public void testAPI() throws MusixMatchException {
//
//        // Track Search [ Fuzzy ]
//        Track track = musixMatch.getMatchingTrack(trackName, artistName);
//        TrackData data = track.getTrack();
//
//        System.out.println("AlbumID : " + data.getAlbumId());
//        System.out.println("Album Name : " + data.getAlbumName());
//        System.out.println("Artist ID : " + data.getArtistId());
//        System.out.println("Album Name : " + data.getArtistName());
//        System.out.println("Track ID : " + data.getTrackId());
//       
//        int trackID = data.getTrackId();
//
//        Lyrics lyrics = musixMatch.getLyrics(trackID);
//
//        System.out.println("Lyrics ID       : " + lyrics.getLyricsId());
//        System.out.println("Lyrics Language : " + lyrics.getLyricsLang());
//        System.out.println("Lyrics Body     : " + lyrics.getLyricsBody());
//        System.out.println("Script-Tracking-URL : " + lyrics.getScriptTrackingURL());
//        System.out.println("Pixel-Tracking-URL : " + lyrics.getPixelTrackingURL());
//        System.out.println("Lyrics Copyright : " + lyrics.getLyricsCopyright());
//
//        // The following will search for tracks with matching artist_name 'Eminem'
//        List<Track> tracks = musixMatch.searchTracks("", "E-type", "", 10, 10, true);
//
//       for (Track trk : tracks) {
//           TrackData trkData = trk.getTrack();
//
//            System.out.println("AlbumID : " + trkData.getAlbumId());
//            System.out.println("Album Name : " + trkData.getAlbumName());
//            System.out.println("Artist ID : " + trkData.getArtistId());
//            System.out.println("Artist Name : " + trkData.getArtistName());
//            System.out.println("Track ID : " + trkData.getTrackId());
//            System.out.println();
//        }
//
//    }
    
    public static void main(String[] args) {
    	int trackID;
    	String artistName, trackName;
    	
    	MusixMatchAPITest api = new MusixMatchAPITest();
    	trackName = JOptionPane.showInputDialog("Skriv in låtnamn: ");
    	artistName = JOptionPane.showInputDialog("Skriv in artistnamn: ");
    	trackID = api.searchForSongReturnTrackID(trackName, artistName);
    	api.findSongWithTrackID(trackID);
    	api.getLyricsWithTrackID(trackID);
    }
}
