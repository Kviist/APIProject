//Method that fetched a playlist from a specific user and with a specific playlist id. Displays the result in the list of songs
function getAPlaylist(user, playListId){
    $.ajax({

    }).done(function(data){
        var trackObject = data['tracks'];
        var tracks = trackObject.items;
        list = $('.songs');
        console.log(list.length);

        for(var i = 0; i < tracks.length; i++){
           list.append('<li>' + tracks[i]['track']['name'] + '</li>');
        }
    });

    };

//Fetches the weather on a specific location from the API provided by VäderMusiMaskinen and displays it in the current-weather field
function getAWeather(location){
     $.ajax({
        method: "GET",
        url: "http://www.vädermusikmaskinen.party:7313/v1/weatherdatasets/" + location,
    }).done(function(response){
         $('.current-weather').text(response + '<br>');
     });

}

// Fades in the componens that displays the songs
$(document).ready(function(){
    $('.list-of-songs').fadeIn(1000).removeClass('hidden');
});
