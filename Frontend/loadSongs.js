function getAPlaylist(user, playListId){
    $.ajax({
            method: "GET",
            url:"https://api.spotify.com/v1/users/"+    user+"/playlists/" + playListId, 
            headers: {"Accept": "application/json", "Authorization": "Bearer BQCoOp4eIVj7a0jU8bstaDal4JYNZ1C-YbB9btHmatQ6M_oWGzG6ejerIHlgz9LPyGi5OUA3XSSddFwRoDqquc328X7rCk2uUR8clkft3rY07vhb_JrJECPJhfZyg8R3ZYQg14vv4Wn82MXzyw"}
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
    $('.player-div').fadeIn(1000).removeClass('hidden'); 
    getAPlaylist("spotify","37i9dQZF1DWVYZVgDjIR7c");
    
});