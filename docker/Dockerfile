FROM bellsoft/liberica-openjdk-debian:8

ADD . /done

EXPOSE 21

RUN apt update && apt upgrade -y && apt install maven git lftp -y && git clone https://github.com/HollowRealmStudios/Ages && cd Ages && mvn install && cd target && ls