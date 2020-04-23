# DESCRIPTION:    Create the microGameOfLife GridWorker in a container
# AUTHOR:         Olivier Despesse <olived19@gmail.com>
# COMMENTS:
#   This file describes how to build the microGameOfLife GridWorker
#   in a container with all dependencies installed.
# USAGE:
#   # Build image
#   docker build -t mgol-gridworker:<VERSION> .
#
#   # Run service
#   docker run --name mgol-gridworker -d mgol-gridworker:<VERSION>
#

# Base docker image
FROM openjdk:11.0.7-jre-slim-buster
MAINTAINER Olivier Despesse <olived19@gmail.com>

COPY micro-gameoflife-gridworker-<VERSION>.jar /opt/gridworker/
WORKDIR /opt/gridworker

ENTRYPOINT ["java", "-jar", "micro-gameoflife-gridworker-<VERSION>.jar"]
