# Quiz App

## Prerequisite

Before running the Quiz App, ensure that the following are installed:

- Java-17
- Docker
- Maven
- IntelliJ

## DB setup
Following command to run postgres and import required data into it

```docker-compose up db -d```

## Installation
Run the below command to build maven project

``mvn clean install -DskipTests``

## Running the Application
To run the application, open QuizappApplication, right-click, and select "Run."

## Verification
Verify the setup by executing the following command:
```bash
% curl localhost:8080/questions/all
```
You should receive a response indicating that all questions are retrieved successfully:
```
Hi, these is all your questions
```


