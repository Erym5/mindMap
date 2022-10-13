# VERSION 1.0
# Author: Dhj
FROM openjdk:8-jre

WORKDIR /data/minmap

COPY . .

EXPOSE 4000

CMD java -jar mindMap-0.0.1-SNAPSHOT.jar