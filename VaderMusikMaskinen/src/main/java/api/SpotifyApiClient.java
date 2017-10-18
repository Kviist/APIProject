package api;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.PlaylistRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Playlist;
import com.wrapper.spotify.models.PlaylistTrack;
import org.glassfish.jersey.client.oauth2.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.List;

/**
 * Created by kviist on 2017-10-08.
 */
public class SpotifyApiClient{
    private String URL = "https://api.spotify.com/";
    private Client client;
    private String acessToken;
    private final String clientID = "16e7908b90354bc0b374c7b8bc2ef44d";
    private final String clientSecret = "5cac807361fa40a7bedcf417c2d5c6e5";
    private final String redirectUri = "http://localhost:8888/callback";
    private Api api;
    private Playlist recievedPlaylist;

    public SpotifyApiClient(){
        api = Api.builder().clientId(clientID).clientSecret(clientSecret).build();
    }

    public Playlist getPlaylist(String user, String playListId){

        getAuth();
        PlaylistRequest reqPlaylist = api.getPlaylist(user, playListId).build();

        try{
            recievedPlaylist = reqPlaylist.get();
            /*
            System.out.println("Recieved playlist: " + recievedPlaylist.getName());
            System.out.println(recievedPlaylist.getDescription());
            System.out.println("Contains: " + recievedPlaylist.getTracks().getTotal());
            */

        } catch (WebApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(recievedPlaylist!=null) {
            return recievedPlaylist;
        } else{
            return new Playlist();
        }


    }



    public void getAuth(){
        ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();
        SettableFuture<ClientCredentials> responseFuture = request.getAsync();
        Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
            @Override
            public void onSuccess(ClientCredentials clientCredentials) {
                System.out.println("Accestoken recieved: " + clientCredentials.getAccessToken());
                System.out.println("Expires in: " + clientCredentials.getExpiresIn() + "s");

                api.setAccessToken(clientCredentials.getAccessToken());
                SpotifyApiClient.this.acessToken = clientCredentials.getAccessToken();
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Check invalid id or secret");
            }
        });
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        SpotifyApiClient spAPI = new SpotifyApiClient();
        spAPI.getAuth();
        spAPI.getPlaylist("spotify", "37i9dQZF1DWVYZVgDjIR7c");

    }

}

