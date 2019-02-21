## Tech task

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users

* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)

* Menu changes each day (admins do the updates)

* Users can vote on which restaurant they want to have lunch at

* Only one vote counted per user

* If user votes again the same day:
    If it is before 11:00 we asume that he changed his mind.
    If it is after 11:00 then it is too late, vote can't be changed

* Each restaurant provides new menu each day.
 
 ## Technologies
 
   Java 11 Stream API, Maven, H2 database, Spring Security, Spring Boot, Spring Data JPA, Hibernate ORM, Hibernate Validator, Jackson, SLF4J, JUnit 5.
 
 ## API description
 
* SHARED ENDPOINTS
 * `GET: /rest/restaurants/available?date=` get restaurants with available meals for requested date, if the parameter date is not passed, it is equal to the current date**. 
 * `GET: /rest/votes/result?date=` get voting result for requested date, if the parameter date is not passed, it is equal to the current date**.
 * `GET: /rest/restaurants/{restaurantId}` get restaurant with id {}.
 * `GET: /rest/meals/{mealId}/restaurant/{restaurantId}` get meal with id {} by restaurant with id {}.
   
* ONLY USER ENDPOINTS
 * `GET: /rest/profile/votes` get all user votes sorted by date.
 * `GET: /rest/profile` get user data. 
 * `PUT: /rest/profile` update user name, email or password. 
 * `POST: /rest/profile/votes?restaurantId=` vote for a restaurant. 
  
* ONLY ADMIN ENDPOINTS
 * `GET: /rest/admin/meals/restaurant/{restaurantId}` get all meals for restaurant with id {}.
 * `GET: /rest/admin/restaurants` get all restaurants.
 * `GET: /rest/admin/users` get all users.
 * `GET: /rest/admin/users/{userId}` get user with id {}.
 * `GET: /rest/admin/users?email=` get user with email {}.
 * `PUT: /rest/admin/meals/{mealId}/restaurant/{restaurantId}` update meal with id {} owned by a restaurant with id {}. 
 * `PUT: /rest/admin/restaurants/{restaurantId}` update restaurant with id {}.
 * `PUT: /rest/admin/users/{userId}` update user with id {}.
 * `POST: /rest/admin/restaurants` add new restaurant.
 * `POST: /rest/admin/meals/restaurant/{restaurantId}` add new meal for restaurant with id {}.
 * `POST: /rest/admin/users` add new user.
 * `DELETE: /rest/admin/meals/{mealId}/restaurant/{restaurantId}` delete meal with id {} by a restaurant with id {}.
 * `DELETE: /rest/admin/restaurants/{restaurantId}` delete restaurant with id {}.
 * `DELETE: /rest/admin/users/{userId}` delete user with id {}.
  
 ## How to test  
          
There are 36 tests for service layer. Application uses H2 database so you don't need to install anything, H2 works in memory. 
You need to create a new environment variable with project's root. That's all, you can run tests.  
For controllers test you should build application and go to url <http://localhost:8080/swagger-ui.html>. There are 22 endpoints. 
    
    To test controllers requests you should login as User or Admin
     
     Admin
     adminOne@mail.ru
     adminPass1
     
     User
     userOne@mail.ru
     userPass1
 
 **NOTE.** Records in database has Today and Yesterday date so when you will test application with SoapUI, set request parameters to correct values.