function getAPlaylist(user, playListId){
    $.ajax({
            method: "GET",
            url:"https://api.spotify.com/v1/users/"+user+"/playlists/" + playListId, 
            headers: {"Accept": "application/json", "Authorization": "Bearer BQBQtlWisFDuTatUWrH_Lzrh8iiu78ZG4pG7U6IEbNBKQFcvp63TPktABOw8mM9okpMvoIY6ag8uekAwOrTO7bckwoaKbV0oqabGSX7kGklWJACcEFjjSWUk__x9MiIoIeFl1OUBG63-aQBlAQ"}
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

$(document).ready(function(){
    $('.list-of-songs').fadeIn(1000).removeClass('hidden'); 
    getAPlaylist("spotify","37i9dQZF1DWVYZVgDjIR7c");
    
});