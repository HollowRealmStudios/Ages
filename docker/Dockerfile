FROM maven:3.8.6-amazoncorretto-8

ADD . /project
WORKDIR /project

RUN mvn clean package

#TODO add entrypoint -> Somehow show docker window on host? -> Maybe X11 passthrough?