javaee-rest-api
===============

Simple [Java EE 7](http://www.oracle.com/technetwork/java/javaee/index.html) [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) application for testing purposes. Use the Dockerfile to run the application in a [Docker](https://www.docker.com/) container.

Build
-----

Build the application:

    mvn clean install

Create the Docker image:

    docker build -t javaee-proxy .

Run
---

Run in Docker:

    docker run -it --rm -P --name javaee-rest-api javaee-rest-api

Run in Wildfly:
~~~~
cp target/javaee-rest-api.war $WILDFLY/standalone/deployments
$WILDFLY/bin/standalone.sh
~~~~
