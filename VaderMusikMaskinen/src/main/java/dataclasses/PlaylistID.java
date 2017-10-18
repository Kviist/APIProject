package dataclasses;

import java.util.HashMap;

/**
 * Created by kviist on 2017-10-16.
 */
public class PlaylistID {
    private   HashMap<String, String> playlistIds;

    public PlaylistID(){
        playlistIds = new HashMap<>();

        playlistIds.put("KLART","37i9dQZF1DXdPec7aLTmlC");
        playlistIds.put("HALVMULET","37i9dQZF1DWZCYmSEd1QSS");
        playlistIds.put("MOLNIGT","37i9dQZF1DWUNIrSzKgQbP");
        playlistIds.put("DIMMA","37i9dQZF1DWWuOw4E4LGde");
        playlistIds.put("ÅSKA","37i9dQZF1DWWJOmJ7nRx0C");
        playlistIds.put("REGN","37i9dQZF1DX9NmDLwNQnXE");
        playlistIds.put("KRAFTIGTREGN","37i9dQZF1DX4aYNO8X5RpR");
        playlistIds.put("SNÖBLANDATREGN","37i9dQZF1DX2mFmJUZg4Mp");
        playlistIds.put("SLASK","37i9dQZF1DXbcPC6Vvqudd");
        playlistIds.put("GANSKAJULIGT","37i9dQZF1DXbPHTEEyQ6Hv");
        playlistIds.put("ULTRAJULIGT","37i9dQZF1DXbPHTEEyQ6Hv");
    }

    public  String getId(String weather){
        return playlistIds.get(weather);
    }
}
