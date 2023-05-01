## To Do Application

This is simple spring boot based TODO backend application which provides below API's

    1) Get All To Do Items          
    2) Get To Do Item by id
    2) Create To Do Item            
    3) Delete To Do Item            
    4) Updated to do item          

## Development

The application is built on spring boot framework which also use H2 in-memory database.

Lombok must be supported by your IDE. For this, in IntelliJ install the Lombok plugin and enable annotation processing - [learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

After starting the application it is accessible under `localhost:8080`.

## Build
The application can be built using the following command:

```
mvnw clean package
```

The application can then be started with the following command -:

```
java  -jar ./target/ToDoApp-0.0.1-SNAPSHOT.jar
```

## Access 

The application can be accessed via swagger as well with link http://localhost:8080/swagger-ui/index.html


## Documentation  

The API Uses cases along wiith sample input output mentioned in the doc - [here](https://github.com/Sanket-Murugkar/ToDoApp/blob/79153b3bd7c5d7a1595f6361755344641f1211dc/docs/API_USE_CASES)

The postman API collection is also added  [here](https://github.com/Sanket-Murugkar/ToDoApp/blob/79153b3bd7c5d7a1595f6361755344641f1211dc/docs/TODO.postman_collection.json)

##Testing

The Junit test cases added in the test pckage.
Integration test Also added [here](https://github.com/Sanket-Murugkar/ToDoApp/blob/cd612f483c1a89fce74aab3082590393445cf297/src/test/java/com/demo/todoapp/controller/ToDoControllerITTest.java)
