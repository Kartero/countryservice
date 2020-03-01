# Countryservice
Country service REST API demo

On local environment run .\mvnw spring-boot:run

## REST APIs

localhost:8080/counties returns country list JSON
localhost:8080/countries/{name} returns country details JSON

## HTML Routes
localhost:8080/ returns single page application which uses REST API through native JS

## Other
Developed and tested
- Maven 3.6.3
- Java 13.0.2
- Few tests are written but because of some configuration issue, mvn test won't run them properly. 
- Tests can be run directly from IntelliJ IDE 