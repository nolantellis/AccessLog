# Access Log Summary

## Asumption
* No Logging framework.
* File content are in valid format
* assert4j for testing with junit
* used gradle 6 to build
* Can use wrapper to build the project

## Requirements
* Java 8 or openjdk 11/13


## RUN
Go to Root project directory

## run command
java -cp build/libs/AccessLog.jar com.access.AccessLogParsingLauncher src/main/resources/programming-task-example-data.log

## Run Test
./gradlew test 
