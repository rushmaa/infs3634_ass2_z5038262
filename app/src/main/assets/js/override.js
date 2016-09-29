(function(){
    for (var i = 0; i < document.body.children.length; i++) {
       var child =  document.body.children[i];
       if (child.getAttribute('class') != 'container pokedex') {
            child.style='display:none';
       }
    }

    document.getElementsByClassName('notch-collectibles-large')[0].style="display:none";

    // remove more
    var pokedexDOM = document.getElementsByClassName('pokedex')[0];

    // remove others
    pokedexDOM.children[0].style.paddingTop=0;
    for (var i = 0; i < pokedexDOM.children.length; i++) {
      if (i > 3 || i == 0) {
        pokedexDOM.children[i].style='display:none';
      }
    }

    // remove all the a href
    a_tags = document.getElementsByTagName('a');
    for (var i = 0; i < a_tags.length; i++) {
      a_tags[i].href="";
    }
})()
