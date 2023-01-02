FROM openjdk:11
MAINTAINER delmoralcristian@gmail.com

ENTRYPOINT ["sh","/opt/run.sh"]

ADD maven/${project.build.finalName}.jar /opt/superhero-1.0.0.jar
ADD run.sh /opt/run.sh