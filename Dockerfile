FROM tomcat:8.0.51-jre8-alpine
WORKDIR $CATALINA_HOME
COPY /build/libs/auth.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
