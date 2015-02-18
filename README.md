Idealab proof of concept with Spring Boot and Openshift. 

#### Run it
To build the application you can execute in command line this statement:  

```mvn clean package dbunit:operation spring-boot:run```

This will clean the project (delete target directory if exists), create H2 file database, populate it with some test data and run the Spring Boot application using embedded Tomcat.

#### Use it
If everything works fine then you should be able to navigate to ```http:\\localhost:8080\``` where you'll get the list of endpoints as a JSON Object. 

If you want to view list of roles then go to (using CURL or browser) ```http:\\localhost:8080\roles```. The login should appear. There are currently two users: *user* and *admin* (with no password). When you type *user* you should get access denied, but with *admin* you should get list of roles. To logout just navigate to  ```http:\\localhost:8080\logout```.

#### Tweak it
- this project uses Project Lombok library, so you should have IDE that supports it (Eclipse, IntelliJ IDEA). More information can be found here: http://projectlombok.org/. 
- the Java version is 7, but version 8 is supported too (however not by OpenShift)
- the application can be deployed to OpenShift (see ```.openshift``` directory)
