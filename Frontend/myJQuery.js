function getAuth(){
    $.ajax({
        method: "GET",
        url: "https://accounts.spotify.com/authorize/?client_id=16e7908b90354bc0b374c7b8bc2ef44d&response_type=code&redirect_uri=file:///Users/kviist/GitHub/APIProject/Frontend/index.html",
        headers: {"Accept": "application/json"},
    }).done(function(response){console.log(response)});
}

function sendALocation(location){
     $.ajax({
        method: "GET",
        url: "http://127.0.0.1:7313/weatherdatasets/" + location,
    }).done(function(response){console.log(response)});
    
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
    }
});
    
});


