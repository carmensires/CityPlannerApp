# City Planner App
## Introduction
The project consists of the development of a city planner app where the user could obtain an optimal route to visit the city according to the best rated places as reported by Yelp opinions. 
The APIs that are going to be used are the Yelp API and Google Maps API.

## Description
First of all, the user will have to introduce the city she/he wants to visit, the number of days she/he will be there and the number of places to visit. This information will be used to create the optimal route,  giving to the user a detailed itinerary with the distribution and order of the places she/he will visit.
For example, if the user wants to visit Chicago in 3 days, visiting 15 places, the app will perform the following tasks:
* Get the 15 places best rated in TripAdvisor/Yelp
* Calculate the route that would minimize the total time required to visit all those places. 
* Divide the total time in equal parts, creating a different route for each day. 
* Return the user the 3 calculated routes, with the option to get more information about the places that she/he will visit, for example, price and opening hours.
