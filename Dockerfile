FROM bitnami/tomcat:9.0
COPY target/calendar.war /opt/bitnami/tomcat/webapps_default