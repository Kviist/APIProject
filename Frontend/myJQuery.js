function getAuth(){
    $.ajax({
        method: "GET",
        url: "https://accounts.spotify.com/authorize/?client_id=16e7908b90354bc0b374c7b8bc2ef44d&response_type=code&redirect_uri=file:///Users/kviist/GitHub/APIProject/Frontend/index.html",
        headers: {"Accept": "application/json"},
    }).done(function(response){console.log(response)});
}

function fetchASong(songID){
    getAuth();
    $.ajax({
        method: "GET",
        url: "https://api.spotify.com/v1/tracks/3B7udSGy2PfgoCniMSb523?access_token=BQBLyFaqMxhXrowWDMoYicCM6vSFz7-O8Sf4xtw0qDOFhnu6mQle7XGVFkRObRvNBKegi1nUnZNEFgkFQLdl9EVjdUIL1UF_CasP_PdPqJl0UKWuAnZ9ziDPYQWdi5_b3sQl3NuT_lm49V8lrg",
        headers: {"Accept": "application/json"},
     }).done(function(response){console.log(response);});
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
    console.log("TEST");
    if(event.keyCode == 13){
        window.location.href = 'ListPage.html';
    }
});
    
});


