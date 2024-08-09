# Quiz App

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


