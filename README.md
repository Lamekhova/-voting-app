[![Build Status](https://travis-ci.com/Lamekhova/votingApp.svg?branch=master)](https://travis-ci.com/Lamekhova/votingApp)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3394631ee80e4de09f3f4ee25d672ae4)](https://www.codacy.com/app/Lamekhova/VotingApp?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Lamekhova/VotingApp&amp;utm_campaign=Badge_Grade)

Voting system for deciding where to have lunch
==================

Task: Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository.

P.S.: Make sure everything works with latest version that is on github

P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.
 
## Technologies

Java 11 Stream API, Maven, H2 database, Spring Security, Spring Boot, Spring Data JPA, Hibernate ORM, Hibernate Validator, Jackson, SLF4J, JUnit 5.

## Credentials

  User(role, email, password):
  * Admin, "adminOne@mail.ru", "adminPass1"
  * User, "userOne@mail.ru", "userPass1"

## API description

---

###  ONLY ADMIN ENDPOINTS

- #### Add new meal for restaurant with id 100005
    `curl -X POST http://localhost:8080/rest/admin/meals/restaurant/100005 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx' -H 'content-type: application/json' -d '{"name": "Fresh orange juice","price": 180.00}'`                                                                                                                                                                     
    
- #### Get all meals owned by restaurant with id 100005
    `curl -X GET http://localhost:8080/rest/admin/meals/restaurant/100005 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`
    
- #### Update meal with id 100015 owned by a restaurant with id 100005
    `curl -X PUT http://localhost:8080/rest/admin/meals/100015/restaurant/100005 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx' -H 'content-type: application/json' -d '{"name": "Pasta pesto with new sauce","price": 100.00}'`
                                                                                                                                                                              	
- #### Delete meal with id 100012 owned by a restaurant with id 100006
    `curl -X DELETE http://localhost:8080/rest/admin/meals/100015/restaurant/100005 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`

- #### Add new restaurant
    `curl -X POST http://localhost:8080/rest/admin/restaurants -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx' -H 'content-type: application/json' -d '{"name": "Barbaresco","address": "Carrer de Viladomat, 137, 08015 Barcelona"}'`

- #### Get all restaurants without meals
    `curl -X GET http://localhost:8080/rest/admin/restaurants -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`

- #### Update restaurant with id 100005
    `curl -X PUT http://localhost:8080/rest/admin/restaurants/100005 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx' -H 'content-type: application/json' -d '{"name": "La Paradeta","address": "Carrer Comercial, 7, 08003 Barcelona"}'`

- #### Delete restaurant with id 100006
    `curl -X DELETE http://localhost:8080/rest/admin/restaurants/100006 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`
    
- #### Add new user
    `curl -X POST http://localhost:8080/rest/admin/users -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx' -H 'content-type: application/json' -d '{"name": "Homer Jay Simpson","email": "homerjaysimpson@mail.ru","password": "userPass123","roles": ["ROLE_USER"]}'`

- #### Get all users
    `curl -X GET http://localhost:8080/rest/admin/users -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`

- #### Get user with id 100002
    `curl -X GET http://localhost:8080/rest/admin/users/100002 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`

- #### Get user with email userThree@mail.ru
    `curl -X GET 'http://localhost:8080/rest/admin/users/by?email=userThree%40mail.ru' -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`
    
- #### Update user with id 100003
    `curl -X PUT http://localhost:8080/rest/admin/users/100003 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx' -H 'content-type: application/json' -d '{"name": "Christopher Robin","email": "newemail@mail.ru","password": "userPass3","roles:["ROLE_USER"]}'`
    
- #### Delete user with id 100003
    `curl -X DELETE http://localhost:8080/rest/admin/users/100003 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`


### ONLY USER ENDPOINTS

- #### Get all user votes sorted by date
    `curl -X GET http://localhost:8080/rest/profile/votes -H 'authorization: Basic dXNlck9uZUBtYWlsLnJ1OnVzZXJQYXNzMQ=='`

- #### Vote for a restaurant (user can vote until 11 o'clock. For test you should set a different finish time in TimeUtil.class) 
    `curl -X POST 'http://localhost:8080/rest/profile/votes?restaurantId=100005' -H 'authorization: Basic dXNlck9uZUBtYWlsLnJ1OnVzZXJQYXNzMQ=='`

- #### Get user data
    `curl -X GET http://localhost:8080/rest/profile -H 'authorization: Basic dXNlck9uZUBtYWlsLnJ1OnVzZXJQYXNzMQ=='`

- #### Update user data 
    `curl -X PUT http://localhost:8080/rest/profile -H 'authorization: Basic dXNlck9uZUBtYWlsLnJ1OnVzZXJQYXNzMQ==' -H 'content-type: application/json' -d '{"name": "Nicolas Newman","email": "userOneNewEmail@mail.ru","password": "$2a$10$ILH/zic9ph7hnU6b2607aOb4KFDhONC83berQd962rMCSkOUyje.2"}'`


### SHARED ENDPOINTS

- #### Get restaurants with available meals for requested date, if the parameter date is not passed, it is equal to the current date.
    `curl -X GET http://localhost:8080/rest/restaurants/available?date= -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`
    
- #### Get voting result for requested date, if the parameter date is not passed, it is equal to the current date
    `curl -X GET http://localhost:8080/rest/votes/result?date= -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`
    
- #### Get restaurant with id 100005 without meals
    `curl -X GET http://localhost:8080/rest/restaurants/100005 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`

- #### Get meal with id 100014 by restaurant with id 100005
    `curl -X GET http://localhost:8080/rest/meals/100014/restaurant/100005 -H 'authorization: Basic YWRtaW5PbmVAbWFpbC5ydTphZG1pblBhc3Mx'`

## How to test  
          
There are 36 integration tests. Application uses H2 database so you don't need to install anything, H2 works in memory. 
You need to create a new environment variable with project's root. That's all, you can run tests.  
For controllers test you can use Git Bash or Postman. There are 22 endpoints.

 **NOTE.** Records in database has Today and Yesterday date so when you will test application, set request parameters to correct values.