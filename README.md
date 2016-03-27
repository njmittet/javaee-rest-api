javaee-rest-api
===============

Simple Java EE 7 REST API application for testing purposes. Use the Dockerfile
to create a Docker container for the applicaton.

Build
-----

The Application must be built before the docker image:

    mvn clean install

Build the Docker image:

    docker build -t javaee-proxy .

Run
---

Run in Docker:

    docker run -it --rm -P --name javaee-rest-api javaee-rest-api

Run standalone:

    java -jar target/javaee-rest-api.jar
