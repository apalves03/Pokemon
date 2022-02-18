Android Pokemon
=================

* The application allows research on POKEAPI the pokemons and their main data.
* This search occurs through requests to the web service with the RETROFIT library.
* The MOCHI library is used to convert a string JSON into Kotlin objects.
* The pokemon image processing is performed by the library COIL.
* The pokemon list is displayed with PAGING, where it’s being used pages size 12.
* The project architecture is MVVM, with dependence injection HILT and various Android JETPACK libraries.
* There are automated tests to increase the quality of the application, where can be observed the following scenarios:
  * Screen navigation
  * GridLayout with Recyclerview
  * Screen with details of the Pokemon
  * Test for ViewModel


Architecture
-----------

![Architecture](screenshots/architecture.png "Architecture")


Screenshots (not dark)
-----------

![First access to the App](screenshots/not_dark_phone_pokemon_first_acess.png "First access to the app")
![First app access error](screenshots/not_dark_phone_pokemon_error_first_acess.png "First app access error")
![List of Pokemon](screenshots/not_dark_phone_pokemon_list.png "A list of pokemons")
![Pagination list of Pokemon](screenshots/not_dark_phone_pokemon_pagination_list.png "A pagination list of pokemons")
![Pokemon List Error](screenshots/not_dark_phone_pokemon_error_list.png "Pokemon list error")
![Pokemon detail](screenshots/not_dark_phone_pokemon_detail.png "etails for a specific Pokemon")


Screenshots (dark)
-----------

![First access to the App](screenshots/dark_phone_pokemon_first_acess.png "First access to the app")
![First app access error](screenshots/dark_phone_pokemon_error_first_acess.png "First app access error")
![List of Pokemon](screenshots/dark_phone_pokemon_list.png "A list of pokemons")
![Pagination list of Pokemon](screenshots/dark_phone_pokemon_pagination_list.png "A pagination list of pokemons")
![Pokemon List Error](screenshots/dark_phone_pokemon_error_list.png "Pokemon list error")
![Pokemon detail](screenshots/dark_phone_pokemon_detail.png "Details for a specific Pokemon")


Libraries Used
--------------
* Architecture - MVVM
  * Jetpack
  * Lifecycles
  * LiveData
  * Flow
  * Navigation
  * ViewModel
  * Paging
  * Binding expressions
  * Data binding
  * View binding
  * Binding Adapters
  * Unit Tests
  * Instrumentation Tests
  * espresso
  * junit
* UI
  * Fragment
  * Layout
  * Material Design
  * RecyclerView
  * FragmentContainerView
  * ConstraintLayout
  * MaterialCardView
* Third party and miscellaneous libraries
  * Retrofit
  * Moshi
  * Coil
  * Hilt
  * Kotlin Coroutines
  * PokeAPI
  

Upcoming features
-----------------
* Search Pokemon
* Animations
* Include library Room