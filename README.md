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
http://localhost:8015/films
```

Run tests:
```
./gradlew test
```

### To do:
* Add jsp web pages
* Add update functionality
* Disallow adding same film (business key = year, title, director)
* Add database? (eg mysql)