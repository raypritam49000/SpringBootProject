# define base docker image
FROM openjdk:11
LABEL maintainer="com.ray.pritam"
ADD target/user-rest-api-demo.jar user-rest-api-demo.jar
ENTRYPOINT ["java","-jar","user-rest-api-demo.jar"]