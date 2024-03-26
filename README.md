# Board Games

## Running the project 

### 1. Via command line (needs maven):

In the directory which contains the relevant pom file, use the mvn command + build life cycle, phase or goal as parameter.
Maven executes all life cycle phases until the specified one.

To trigger the jar packaging:
```bash
mvn clean install 
```

To run the project:
```bash
java -jar target/game-0.0.1-SNAPSHOT.jar
```
Guide for installing Maven:
https://phoenixnap.com/kb/install-maven-windows

### 2. If you don't have Maven installed, you can run the application via IDE:

In IntelliJ IDEA, in the Main Class (game/src/main/kotlin/com.univesp.game/GameApplication.kt)

Click play button, and then 'Run GameApplication.kt'

Or use the shortcut: Ctrl+Shift+F10

## Postman Collection
The Postman Collection can be obtained in /doc folder.

## Open API (Swagger)
The Swagger UI page is available at http://server:port/swagger-ui.html 

The OpenAPI description is available at the following url for json format: http://server:port/v3/api-docs