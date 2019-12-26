FROM tomcat:9.0.30-jdk8-openjdk
WORKDIR $CATALINA_HOME
COPY /build/libs/auth.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
