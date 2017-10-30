//Fades in the components that are displayed in the page that describes how the site works
$(document).ready(function(){
    
      setTimeout(function(){
        $('.main-nav').fadeIn(500).removeClass('hidden'); 
    }, 500);
    
     setTimeout(function(){
        $('.description').fadeIn(500).removeClass('hidden');
    }, 500);
    
});