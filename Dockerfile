FROM amazoncorretto:17
WORKDIR /app
CMD mkdir /app/examples/
ARG JAR_PATH
COPY $JAR_PATH /app/app.jar
ENTRYPOINT ["java" ,"-jar" ,"app.jar"]