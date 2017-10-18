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

function getAWeather(location){
     $.ajax({
        method: "GET",
        url: "http://127.0.0.1:7313/weatherdatasets/" + location,
    }).done(function(response){
         $('.current-weather').text(response + '<br>');
     });
    
}

$(document).ready(function(){
    $('.list-of-songs').fadeIn(1000).removeClass('hidden'); 
    

});