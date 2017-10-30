var tracks;
var APIurl = "http://www.vädermusikmaskinen.party:7313/"
var searchAccepted = false;

//Send the location entered in the input field on the start page to the API provided by VäderMusikMaskinen. If the input was not a //accepted location a error message is displayed, otherwise the listPage loads
function sendALocation(location){
    searchAccepted = false;
     $.ajax({
        method: "GET",
        url: APIurl+"v1/weatherdatasets/" + location,
    }).done(function(response){
      console.log(response);
       if(response == "ERROR PARSING CHOSEN LOCATION"){
       $('#search').val("Ange en plats inom Sverige");
      }else{
        $('.current-weather').text(response),
        getPlaylistName(response),
        getTracks(response);
        $(document).ready(function(){
           $('header').load('ListPage.html');
        });
     }
     });



}

//Method for fetching a playlist based on a weather from the VäderMusikMaskinen API. Displays the playlistname in a field and updates //the music player to the fetched playlist
function getPlaylistName(weather){
     $.ajax({
        method: "GET",
        url: APIurl+"v1/musiclibrary/playlists/" + weather,
    }).done(function(response){
         var playlist = JSON.parse(response);
         $('.current-playlist').text(playlist['name']);
         console.log('https://open.spotify.com/embed?uri=' + playlist['uri']);
         $('.player').attr('src','https://open.spotify.com/embed?uri=' + playlist['uri']);
     });

}

//Fetches the tracks from a playlst specified by the current weather. Displays the tracks in the trackslist and adds a clickfunction //that fetches the lyrics to the song that was clicked
function getTracks(weather){
     $.ajax({
        method: "GET",
        url: APIurl+"v1/musiclibrary/tracks/" + weather,
    }).done(function(data){
         var list = JSON.parse(data);
         tracks = list;
         var frontEndList = $('.songs');

         for(var i = 0; i < list.length; i++){
             html = '<li id="song_' + i + '"><a href="#"><i>' + list[i]['name'] + '</i> -- ' + list[i]['artists'] + '</a></li>';
             frontEndList.append(html);
         }

         $('.songs li').click(function(e){
             var currentSong = $(this).text();
             console.log(currentSong);

             var splittedArray = currentSong.split('--');
             var songName = splittedArray[0];
             songName.trim();
             var songId = null;
             console.log(songName);

             for(var k = 0; k < tracks.length; k++){
                 var res = tracks[k]['name'].trim().valueOf().localeCompare(songName.trim().valueOf());
                 

                 if(res == 0){
                     console.log("IM IN")
                     songId = tracks[k]['id'];
                     getLyrics(tracks[k]['artists'], tracks[k]['name'])
                 }
             }
            

             console.log("SONGID: " + songId);

             if(songId != null){
                 $('.player').attr('src','https://open.spotify.com/embed?uri=spotify:track:' + songId);
             }

    });
     });

}

//Method for fetching the lyrics for a song specified by the artists and the songname and displays the lyrics in the lyrics section
function getLyrics(artist, songName){

   $.ajax({
    method: "GET",

    url: "http://127.0.0.1:7313/v1/musiclibrary/lyrics/song/"+songName+"/artist"+"/" + artist,

    url: APIurl+"v1/musiclibrary/lyrics/" + songArtistName,

    }).done(function(response){
        $('.lyrics').text("");
        $('.lyrics').append(response);

    });
}

// Fades in the components
$(document).ready(function(){

    setTimeout(function(){
        $('.main-nav').fadeIn(1000).removeClass('hidden');
    }, 500);

     setTimeout(function(){
        $('.inputfield').fadeIn(1000).removeClass('hidden');
    }, 1000);

      setTimeout(function(){
        $('.welcome-text').fadeIn(1000).removeClass('hidden');
    }, 1000);

    $('.inputfield').keyup(function(event){
    if(event.keyCode == 13){
        sendALocation($('#search').val());




    }
    });
});
