# infs3634_ass2_z5038262
Brief description of every feature: -

Sidebar/Navigation Bar

This will help the user easily navigate throughout the different pages in the entire app; it can be accessed by pressing the hamburger menu icon on the top left. This makes a huge chunk of the code extend MenuActivity instead of Fragment in order to make everything function well and dynamically.

Pokemon List

The first option on the sidebar and the first page that can be seen after the app is launched. Contains a list of all of the First Gen (151) Pokémon along with a few extras in a numerically ascending order. All Pokémon are searchable through a search bar on top. Every Pokémon name is accompanied by a serial number and an image to assist the user in identifying the Pokémon. When a Pokémon is clicked, the user will be redirected to a page specifically designed for that Pokémon, containing detailed information and with images and a GIF of the Pokémon in motion. The data is gathered from webView, PokéAPI and a csv file. The GIFS are provided in the resources folder and are displayed using webView to facilitate fast loading and build of the app.

Abilities

The second option on the menu redirects the user to a page that contains a list of Abilities all of Gen1 Pokèmon posses with a brief description for every ability. This can help the users whilst they’re playing Pokémon games on Nintendo or Pokèmon Go.

PokéRap

The third option on the menu redirects the user to a page that plays the PokéRap video of all of the Gen 1 Pokémon. This was designed with kids in mind as kids often listen to the PokéRap to memorise the names of all of the Pokémon.

Tips

The fourth option on the menu redirects the user to a page that displays 3 random tips for both Nintendo Pokémon games and Pokémon Go every time the page is opened.

PokéRadar

The fifth option on the menu redirects the user to a map that user can use go see Pokémon spawns near their area. It uses geotags to determine the user’s location. Please close the website pop-up that comes in the beginning in order to access the map.

Terms and Conditions

The last option on the menu redirects the user to a page that displays the terms and conditions for using the app.

 

PokéMap

This is the only feature in the app that is hidden/non-functional. Since PokéRadar used only webViews and geotags which facilitates quicker build and makes the app more dynamic, this bit of code is to show the user the understanding for a more traditional approach towards developing maps and how to go about them.

*Please note - The device needs to be location and internet enabled in order for the app to function.

Bibliography: -

Code references-

1)           sjudd. 2015. Glide. [ONLINE] Available at: https://github.com/bumptech/glide. [Accessed 4 October 2016].

2)           veekun. 2015. veekun / pokedex. [ONLINE] Available at: https://github.com/veekun/pokedex/tree/master/pokedex/data/csv. [Accessed 4 October 2016].

Video References-

1)     TutorialBaba. 2016. Android App Development Tutorial Playlist. [ONLINE] Available at:https://www.youtube.com/watch?v=lApbZO7J7ZI&list=PLHs_NFdr_LaHmEh7hV-wPyS-gKnAVPzBU

. [Accessed 4 October 2016].


Other References: -

1)              FastPokeMap. 2016. FastPokeMap. [ONLINE] Available at: https://fastpokemap.se. [Accessed 4 October 2016].

2)              PokeDB. 2016. PokeDB - Download Them All. [ONLINE] Available at: http://www.getjar.com/categories/productivity-apps/more/PokeDb-897139. [Accessed 4 October 2016].

3)              RESTful API. [ONLINE] Available at: http://pokeapi.co. [Accessed 4 October 2016].
