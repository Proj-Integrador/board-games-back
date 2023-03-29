FROM amazoncorretto:17.0.6-al2
COPY target/*.jar /home/app/
WORKDIR /home/app
RUN chmod 0777 /home/app
EXPOSE 8080
ENTRYPOINT java -jar $JAVA_OPTIONS *.jar $APP_ARGS