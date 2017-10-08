package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kviist on 2017-10-08.
 */
public class SpotifyReqAPI {
    private Connection connection;

    public SpotifyReqAPI() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try{
            connection = DriverManager.getConnection("https://api.spotify.com");
            System.out.print(connection.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fetchASong(String songId){
        try{
           // Statement stmt = new connection.create
        } catch(Exception e){

        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        SpotifyReqAPI spAPI = new SpotifyReqAPI();
    }

}
