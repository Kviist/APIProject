function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

$(document).ready(function(){
    var url = window.location.href;
    var code = getParameterByName("code", url);
    var state = getParameterByName("state", url);
    
    $('.code-and-state').append("code:" + code + "state:" + state);
});