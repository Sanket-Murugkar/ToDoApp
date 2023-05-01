## To Do Application

This is simple spring boot based TODO backend application which provides below API's

    1) Get To Do Items          
    2) Create To Do Item            
    3) Delete To Do Item            
    4) Updated to do item          

## Development

Update your local database connection in `application.yml` or create your own `application-local.yml` file to override settings for development.

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


