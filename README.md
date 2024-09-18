# Quiz App
![Build and Test](https://github.com/sohm22/quizapp/actions/workflows/mvn-build-and-test.yml/badge.svg)
![Build and Push Docker image](https://github.com/sohm22/quizapp/actions/workflows/docker-image-build-push.yml/badge.svg)
![Deploy to ec2](https://github.com/sohm22/quizapp/actions/workflows/deploy-on-ec2.yaml/badge.svg)
[![Website Status](https://img.shields.io/uptimerobot/status/m797665540-353416832ea5483c4992f0f6?label=QuizMaster%20Status)](https://quizmaster.techness.in/)


## Architecture 

<img width="1056" alt="image" src="https://github.com/user-attachments/assets/0e085729-5c76-4369-8e59-e8f0294e3f75">


## Prerequisite

Before running the Quiz App, ensure that the following are installed:

- Java-17
- Docker
- Maven
- IntelliJ

## DB setup
#TODO : write about .env file 

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
You should receive a proper response with questions (sample response)
```
[
	{
		"id": 1,
		"questionTitle": "What is a class in Java?",
		"option1": "A function",
		"option2": "An object",
		"option3": "A data structure",
		"option4": "A loop",
		"rightAnswer": null
	}
]
```


