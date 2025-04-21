FROM amazoncorretto:21
ADD target/chat-application-exec.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
