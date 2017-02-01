FROM njmittet/alpine-wildfly:10.1.0.Final

RUN mkdir -p /opt/jboss/wildfly/modules/com/mysql/main
COPY target/javaee-rest-api.war /opt/jboss/wildfly/standalone/deployments/javaee-rest-api.war
COPY docker/wildfly/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
COPY docker/wildfly/module.xml /opt/jboss/wildfly/modules/com/mysql/main/module.xml

ENV CONNECTOR_J mysql-connector-java-5.1.40
RUN curl -O -L https://dev.mysql.com/get/Downloads/Connector-J/$CONNECTOR_J.zip \
 && unzip $CONNECTOR_J.zip \
 && mv $CONNECTOR_J/$CONNECTOR_J-bin.jar /opt/jboss/wildfly/modules/com/mysql/main/$CONNECTOR_J.jar \
 && rm -r $CONNECTOR_J && rm $CONNECTOR_J.zip
