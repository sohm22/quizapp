# Quiz App
![Build and Test](https://github.com/sohm22/quizapp/actions/workflows/mvn-build-and-test.yml/badge.svg)
![Build and Push Docker image](https://github.com/sohm22/quizapp/actions/workflows/docker-image-build-push.yml/badge.svg)
![Deploy to ec2](https://github.com/sohm22/quizapp/actions/workflows/deploy-on-ec2.yaml/badge.svg)
[![Website Status](https://img.shields.io/uptimerobot/status/m797665540-353416832ea5483c4992f0f6?label=QuizMaster%20Status)](https://quizmaster.techness.in/)

## Introduction
This Spring Boot application provides a comprehensive set of APIs for managing quizzes. Users can create quizzes, take quizzes, and evaluate their performance. Additionally, the application offers functionality to add new questions and categories. It also integrates AI capabilities, allowing users to generate entire quizzes by simply providing a prompt, making quiz creation more efficient and intelligent. This solution is designed to handle dynamic user inputs and offers a seamless experience for both quiz makers and participants.


## System Architecture Overview

The architecture of this application is designed for scalability, security, and efficient processing. Nginx is employed to terminate TLS/HTTPS, ensuring secure communication between clients and the server. The core of the application is built on a Spring Boot app server, handling business logic and API requests, while PostgreSQL is used for reliable data storage. Additionally, the system integrates with GROQ AI to enhance AI-driven capabilities, making it a powerful solution for dynamic and interactive experiences.

<img width="1057" alt="image" src="https://github.com/user-attachments/assets/2bff9238-a31c-47f0-acf3-491972400e5d">



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


