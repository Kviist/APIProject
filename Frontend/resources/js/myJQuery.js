var tracks;
function sendALocation(location){
     $.ajax({
        method: "GET",
        url: "http://127.0.0.1:7313/weatherdatasets/" + location,
    }).done(function(response){
        $('.current-weather').text(response),
        getPlaylistName(response),
        getTracks(response);
     });   
}

function getPlaylistName(weather){
     $.ajax({
        method: "GET",
        url: "http://127.0.0.1:7313/playlistName/" + weather,
    }).done(function(response){
         var playlist = JSON.parse(response);
         $('.current-playlist').text(playlist['name']);
         console.log('https://open.spotify.com/embed?uri=' + playlist['uri']);
         $('.player').attr('src','https://open.spotify.com/embed?uri=' + playlist['uri']);
     });
    
}
function getTracks(weather){
     $.ajax({
        method: "GET",
        url: "http://127.0.0.1:7313/tracks/" + weather,
    }).done(function(data){
         var list = JSON.parse(data);
         tracks = list;
         var frontEndList = $('.songs');
         
         for(var i = 0; i < list.length; i++){
             html = '<li id="song_' + i + '"><a href="#"><i>' + list[i]['name'] + '</i> -- ' + list[i]['artists'] + '</a></li>';
             frontEndList.append(html);
         }
        
         $('.songs li').click(function(e){
             console.log($(this).text());
             getLyrics($(this).text());
             
    });
     });
    
}
function getLyrics(songArtistName){
   $.ajax({
    method: "GET",
    url: "http://127.0.0.1:7313/lyrics/" + songArtistName,
    }).done(function(response){
        $('.lyrics').text("");
        $('.lyrics').append(response);
        
    });
}


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
        $('header').load('ListPage.html');
    }
    });
});


