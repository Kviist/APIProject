package dataclasses;

import api.SpotifyApiClient;
import com.wrapper.spotify.models.Playlist;
import com.wrapper.spotify.models.PlaylistTrack;
import com.wrapper.spotify.models.SimpleArtist;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kviist on 2017-10-16.
 */
public class SpotifyData {
    private Playlist playlist;

    public SpotifyData(Playlist playlist){
        this.playlist = playlist;
    }

    public Playlist getPlaylistName(){
        return playlist;
    }

    public List<Track> getTracks(){
        List<Track> trackList = new LinkedList<>();
        List<PlaylistTrack> tracks = playlist.getTracks().getItems();

        for(int i = 0; i < tracks.size(); i++){
            List<SimpleArtist> artists = tracks.get(i).getTrack().getArtists();
            String[] artistArray = new String[artists.size()];
            Track newTrack = new Track();
            newTrack.setName(tracks.get(i).getTrack().getName());
            newTrack.setid(tracks.get(i).getTrack().getId());
            for(int j = 0; j < artists.size(); j++){
                artistArray[j] = artists.get(j).getName();
            }

            newTrack.setArtists(artistArray);
            trackList.add(newTrack);
        }

        return trackList;
    }


}
