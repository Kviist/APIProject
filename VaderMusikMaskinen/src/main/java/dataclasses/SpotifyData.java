package dataclasses;

import api.SpotifyApiClient;
import com.wrapper.spotify.models.Playlist;
import com.wrapper.spotify.models.PlaylistTrack;
import com.wrapper.spotify.models.SimpleArtist;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for representing the data in a spotify-playlist
 */
public class SpotifyData {
    private Playlist playlist;

    public SpotifyData(Playlist playlist){
        this.playlist = playlist;
    }

    /**
     * Fetches the playlist
     * @return - the playlist name
     */
    public Playlist getPlaylist(){
        return playlist;
    }

    /**
     * Fetches the tracks in a playlist
     * @return - list of the tracks in the playlist
     */
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
