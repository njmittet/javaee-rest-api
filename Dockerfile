FROM njmittet/alpine-wildfly:10.0.0.Final
COPY target/javaee-rest-api.war /opt/jboss/wildfly/standalone/deployments/javaee-rest-api.war
