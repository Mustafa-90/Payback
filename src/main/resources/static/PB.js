//window.onload = function() {
//  let hamburgerButton = document.querySelector("#hamburger");
//  var pageNavigation = document.querySelector("#PageNavigation");
//  hamburgerButton.onclick = function() {
//    pageNavigation.classList.toggle("open");
//  };
//};

window.onload = function() {
    if(!window.location.hash) {
        window.location = window.location + '#loaded';
        window.location.reload();
    }
}
