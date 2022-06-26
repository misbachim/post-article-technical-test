## How to Run 

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/spring-boot-rest-example-0.5.0.war
or
        mvn spring-boot:run
```

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.kubro.postarticle.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```

### To view H2 datbase

The 'test' profile runs on H2 database. To view and query the database you can browse to http://localhost:8090/h2-console. Default username is 'sa' with password is 'password'.

### Postman Collection

You can import postman collection in root directory of this project, the file is named 'Post Article.postman_collection.json'.
