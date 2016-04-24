# gwt-bean-validators-example
Little example how to use bean validators in a GWT application (client and server side validation).
See live Demo on [https://www.knightsoft-net.de/validationexample/](https://www.knightsoft-net.de/validationexample/)

To run it local, you need **JDK >= 7** and **Maven >= 3.0.5**.
Checkout or download sources, go into the directory and compile it with

```bash
mvn clean package
```

The next step is to use the Spring Boot maven plugin to run the project :

```bash
mvn spring-boot:run
```

When the project is build successfully and running, you can access it at the URL : **[http://localhost:8080/validationexample/](http://localhost:8080/validationexample/)**

For login you can use the user/password combination test1/test1 or test2/test2, it's not secure, it's for demonstration only. The only secured page is a dummy content page...
