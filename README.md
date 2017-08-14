# microGameOfLifeGridWorker

## What is it ?
A worker to solve Game of Life worlds.
It listens on the message queue for any message request.
We can start as many as we want because this service is stateless.

## How to start the microGameOfLifeGridWorker application
1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/micro-gameOflife-gridworker-1.0.jar`
3. The worker will connect to the message queue available at host `messagequeue` port `5672`

## Use with Docker
1. Go to the `docker/` directory
2. Run the script `build.sh <VERSION>`, replace `<VERSION>` with version number
3. Start the container with `docker run --name mgol-gridworker -d mgol-gridworker:<VERSION>`

## Stack
- Java
- microGameOfLifeMessageQueue
- Maven
