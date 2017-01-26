FROM njmittet/alpine-wildfly:10.1.0.Final
COPY target/javaee-rest-api.war /opt/jboss/wildfly/standalone/deployments/javaee-rest-api.war
COPY docker/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml