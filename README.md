# Project Description

This project is implemented for **Takeaway** as an assignment project. It's a Spring Boot project which developed by using Java language.

## Build & Run

This project uses Mysql as database server and Kafka as messaging queue. You can follow the next instructions to run the application.

### Running the application

You can run the Spring Boot application and you will have the service up & running.<br>
**Note** : I do not know the concepts of docker and containers thing, so I have implemented the solution
using local mysql db and kafka with the configuration as mentioned in the boiler plate code


```
Eclipse -> Run As -> Spring Boot Application

OR

mvn clean package spring-boot:repackage
java -jar target/eventService-0.0.1-SNAPSHOT.jar
```

For the above step to work, you should have a running mysql server at `localhost:3306` and kafka at `localhost:9092`

## REST APIs

### Get Employee Events by ID

Gets all events for employee in an ascending order. 
* Each event represents messages consumed from kafka and stored in database.
* Each event has the metadata which includes original source id, creation date and event type.
* Each event has data payload which determines the last state of original source.

URL path: ```GET events/employees/{id}```


Response Model: 
```json
[
       {
            "id": "21e563f7-5476-42ed-970d-a79a82d3fd58",
            "eventType": "EMPLOYEE_CREATED",
            "sourceId": "90f0cc5d-d8b5-47a6-be74-0d9fd62a8e49",
            "createdAt": "2020-08-22T09:05:23Z",
            "payload": {
                "name": "Test Name",
                "birthday": "1992-10-10T00:00:00.000+0000",
                "email": "test@email.com",
                "departmentName": "Engineering"
            }
        }
]
```

## Postman Collections

You can find the postman collection for each REST API call at the following link : 

```
https://www.getpostman.com/collections/c3f4b649dad2fea0b7c9
```

## Author

Amol Ekande