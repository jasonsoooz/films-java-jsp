# films-java-jsp (Java / JSP)

Example web application that displays list of films.

Simple web app that uses Spring Boot to serve up films on back end.  For coding java on back end & JSP front end.

JSP is old tech but still have them at work, so need to keep in touch.

## Prerequisites
* Java Runtime Engine (JRE) 8 & above
* Gradle
* Spring Boot and Spring framework
* JUnit 5 tests

Run the app:
```
./gradlew bootrun
```

Access the app:
```
- Get list of films
http://localhost:8018/demo/films

- Health check ping app
http://localhost:8018/demo/ping

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
* Make 8018 port https
* Improve actuator health check (eg add ping response, etc)
* Add jsp web pages
* Add update functionality
* Disallow adding same film (business key = year, title, director)
* Add database? (eg mysql)