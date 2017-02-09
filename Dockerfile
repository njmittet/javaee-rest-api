FROM njmittet/wildfly-mysql:10.1.0.Final

COPY docker/wildfly/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
COPY target/javaee-rest-api.war /opt/jboss/wildfly/standalone/deployments/javaee-rest-api.war

