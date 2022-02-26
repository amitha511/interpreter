FROM amazoncorretto:17
WORKDIR /app
ARG JAR_PATH
COPY $JAR_PATH /app/app.jar
CMD java -jar app.jar