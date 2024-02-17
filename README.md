# Quiz App

## Prerequisite

Before running the Quiz App, ensure that the following are installed:

- Java
- Docker
- Maven
- IntelliJ

## DB setup
Following command to run postgres and import required data into it

```docker-compose up db -d```

## Installation
Run the below command to build maven project

``mvn clean install -DskipTests``

## Run
Open QuizappApplication, right click and run 

## Verify
```
% curl localhost:8080/questions/all
Hi, these is all your questions
```


