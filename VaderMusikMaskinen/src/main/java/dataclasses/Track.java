package dataclasses;

/**
 * Created by kviist on 2017-10-17.
 */
public class Track{
    private String name;
    private String[] artists;
    private String id;

    public void setName(String name){
        this.name = name;
    }

    public void setArtists(String[] artists){
        this.artists = artists;
    }

    public void setid(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public String[] getArtists(){
        return artists;
    }
}
