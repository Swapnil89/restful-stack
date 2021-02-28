# restful-stack
This application demonstrates Stack operations such as PUSH, PEEK and POP using REST API

## Key Features
* Uses Linked List Stack implementation
* Support for configurable Stack size parameter using properties file
* Support for generic Stack element (currently initialized as Integer stack for demonstration)
* Thread safe Stack operations
* Swagger UI for Stack operations
* Unit test for Stack operations
* Thread safe unit test
* Controller API integration test

## Prerequisite
    Maven 3
    JDK 11
    curl

## Install

    mvn clean install

## Run the app

    java -jar target/restful-stack-0.0.1-SNAPSHOT.jar

## Run the tests

    mvn test

# REST API

The REST API to perform Stack operations such as PUSH, PEEK and POP is described below.

## PUSH

### Request

`POST /v1/api/stack`

    curl -i -H 'Accept: application/json' -d '{"data":1}' http://localhost:8081/v1/api/stack

### Response

    HTTP/1.1 201 Created
    Date: Sun, 28 Feb 2021 05:00:48 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: /v1/api/stack
    Content-Length: 98

    { "responseCode": "CREATED", "execDt": "2021-02-28T05:00:48.543+00:00","message": "","data": "1"}

## PEEK

### Request

`GET /v1/api/stack/`

    curl -i -H 'Accept: application/json' http://localhost:8081/v1/api/stack

### Response

    HTTP/1.1 200 OK
    Date: Sun, 28 Feb 2021 05:03:48 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 94

    { "responseCode": "OK", "execDt": "2021-02-28T05:03:48.695+00:00","message": "","data": "1" }

## POP

### Request

`DELETE /v1/api/stack`

    curl -i -H 'Accept: application/json' -X DELETE http://localhost:8081/v1/api/stack

### Response

    HTTP/1.1 200 OK
    Date: Sun, 28 Feb 2021 05:03:48 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 94

    { "responseCode": "OK", "execDt": "2021-02-28T05:03:48.695+00:00","message": "","data": "1" }

