FROM tomcat : 8.5.30-jre8
WORKDIR $CATALINA_HOME
COPY /build/libs/auth.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
