# films-java-jsp (Java / JSP)

Example web application that displays list of films.

Simple web app that uses Spring Boot to serve up films on back end.  For coding java on back end & JSP front end.

JSP is old tech but still have them at work, so need to keep in touch.

This example uses database = **mysql**.  
To use **in memory** database **h2**, check out **master** branch

## Prerequisites
* Java Runtime Engine (JRE) 12 & above
* Gradle
* Spring Boot 2 and Spring framework 5
* JUnit 5 tests

Run the app:
```
./gradlew bootrun

# To pass command line args to program
./gradlew bootrun -Pargs=example
```

Access the app:
```
- Get list of films
- login credentials: user / password
https://localhost:8018/demo/films

- Spring actuator urls
http://localhost:8015/demo/actuator

- Spring actuator health check
http://localhost:8015/demo/actuator/health 
```

Run tests:
```
./gradlew test
```

### To do:
* Add forgot password feature
* turn on csrf for delete
* Add clear button on form (make sure works for update)
* Replace delete method with delete rest request instead of post
* Change film service to database repository instead of in memory list
* Add selenium web tests
* fix SpringBootTest disabled tests failing with PKIX ssl errors
* Improve actuator health check (eg add ping response, etc)
* Disallow adding same film (business key = year, title, director)