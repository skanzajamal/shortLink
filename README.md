# shortLink

Java application which encodes a URL to a shortened URL and also decodes shortened URL to its original URL.

# Prerequisites
-Docker
-JDK 11
-intelliJ or any IDE

## Docker commands
step1 - Pulling redis image from docker hub

$ docker pull redis

step2 - Running the container

$ docker run -d --name=redis -p 6379:6379 redis

in your IDE press debug or run button, then the application will start on port 8081 and url http://localhost:/8081

redirect to http://localhost:8081/swagger-ui/index.html# for api docs

