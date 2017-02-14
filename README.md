javaee-rest-api
===============

Simple [Java EE 7](http://www.oracle.com/technetwork/java/javaee/index.html) [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) application with a database backend, created for testing purposes. 

Build
-----

Build the application:

    mvn clean install
    
Run
---

Run in Docker:

    docker-compose up -d --build

The above command builds and starts a MySQL image, prepopulated with sample data, as well as the Wildfly Java EE container image with the application.
