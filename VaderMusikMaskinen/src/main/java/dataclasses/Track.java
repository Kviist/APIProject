package dataclasses;

/**
 * Class for representing a track in a playlist with it's useful information
 */
public class Track{
    private String name;
    private String[] artists;
    private String id;

    /**
     * Sets the name of the track
     * @param name - name of track
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the artists of the track
     * @param artists - the artists
     */
    public void setArtists(String[] artists){
        this.artists = artists;
    }

    /**
     * Set's the id of the tack, which is unique for every track
     * @param id
     */
    public void setid(String id){
        this.id = id;
    }

    /**
     * Method for fetching the track name
     * @return - the track name
     */
    public String getName(){
        return name;
    }

    /**
     * Method for retrieving the artists of the track
     * @return - the artists
     */
    public String[] getArtists(){
        return artists;
    }
}
