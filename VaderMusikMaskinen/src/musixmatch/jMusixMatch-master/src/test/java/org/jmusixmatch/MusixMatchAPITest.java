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
    
    public static void main(String[] args) {
    	int trackID;
    	String artistName, trackName;
    	MusixMatchAPITest api = new MusixMatchAPITest();
    	trackName = JOptionPane.showInputDialog("Skriv in låtnamn: ");
    	artistName = JOptionPane.showInputDialog("Skriv in artistnamn: ");
    	trackID = api.searchForSongReturnTrackID(trackName, artistName);
    	System.out.println(api.getSongWithTrackID(trackID));
    	System.out.println(api.getArtistWithTrackID(trackID));
    	System.out.println(api.getAlbumWithTrackID(trackID));
    	System.out.println(api.getLyricsWithTrackID(trackID));
    }
}
