# spring-jvm-application-events

:spring_boot_version: 2.0.5.RELEASE
:spring-boot: https://github.com/spring-projects/spring-boot
:toc:
:icons: font
:source-highlighter: prettify

This projects helps you build and deploy application which is capable of serving GraphQL APIs from a common end point developed using Spring Boot, MVC. 
This application serves the server side needs of client-server architecture put together to leverage elastic APIs using
----
GraphQL
Java 
SpringBoot 
Embedded MongoDB
Embedded H2
----

This project aims to demonstrate how to use the schema definition file (IDL) and build mutations and queries to serve graphql inside JVM. 
It also demontrates how to serve data data using embedded mongoDB and embedded H2 using Spring Data repositories.

== What you'll build
You'll build GraphQL API  usign GraphQl, with Java, Spring Boot, MongoDb and H2 DB. 
Along with GraphQL, this server is capable of serving REST APIs in same controller serving GraphQL.


== What you'll need

java_version: 1.8

The following maven dependencies will add GraphQL capabilities to existing Spring boot application

       <!--graphql dependencies-->
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-java</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-java-tools</artifactId>
            <version>3.2.0</version>
        </dependency>


Pleae check pom.xml for a list of all dependencies for other functionalities : 
Embedded MongoDb, Embedded H2.

== Learn what you can do with GraphQL

GraphQL is query language for APIs. To read more please got to:
https://graphql.org/



== Create an com.graphql.Application class
Here you create an `com.graphql.Application` class with the components:

`src/main/java/hello/Application.java`
[source,java]
----
include::complete/src/main/java/hello/Application.java[]
----

== Create GraphQL schema definition file
The IDL for this project is located in src.main/resources/persons.graphqls
This files is responsible for binding the operations to the request.

We can do it programmatically using java. 

== Create a com.graphql.query.Query
Here you will create a graphQL query which implements the query operations by aggregating data from SpringBoot repositories. 

----
The methods in this class are mapped with schema file
type Query {
    queryAllPeople : [Person]
    queryPersonById(id: String) : Person
    queryPersonWithDetailsById(id: String) : PersonInfo
}
----
Be carful while wiring the name and signaturs of this operations, else you might not see responses. 

== Create a com.graphql.mutation.Mutation
Here you will create a graphQL query which implements the query operations by aggregating data from SpringBoot repositories. 

----
The methods in this class are mapped with schema file
type Mutation {
  mutatePersonName(firstName: String!) : Person
  mutatePersonNameAge(firstName: String!, age: Int!) : Person
}
----
Be carful while wiring the name and signaturs of this operations, else you might not see responses. 



== Create a simple web application using com.graphql.controller.GraphQlEndpoint
`src/main/java/com/sbawa/graphql/GraphQLEndPoint.java`

The class is flagged as a `@RestController`, meaning it's ready for use by Spring MVC to handle web requests. 
`@RequestMapping` maps `/` to the `index()` method. When invoked from a browser or using curl on the command line, the method returns pure text. That's because `@RestController` combines `@Controller` and `@ResponseBody`, two annotations that results in web requests returning data rather than a view.


----
This single class is responsible to provide the endpoitn for all GraphQl needs for this application.
It is capable of serving variety of queries and mutation over a single endpoint
   
   @RequestMapping(value = "/graphql", method = RequestMethod.POST, produces = "text/plain")
   public String query(@RequestBody String request) {
      ...
   }


----
 
Also,this class is responsible to wire GrahQL specific features with rest endpoint.
This reads the schema file and registers the available queries and mutations to serve graphQl API needs. 


    @PostConstruct
    public void loadSchema() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:persons.graphqls");
        GraphQLSchema schema = SchemaParser.newParser()
                                .file(resource.getFilename())
                                .resolvers(query, mutation)
                                .build()
                                .makeExecutableSchema();
        this.graphQl = GraphQL.newGraphQL(schema).build();
    }

== Integration Tests
`com.sbawa.server.api.elastic.controller.GraphQLEndPointTest` 

This class contains integration tests for all the queries and mutations available with this endpoint. 
Its better to write test cases around max data offered by API rather than asserting subset.


== Run the application
To run the application, using Maven, execute:


[subs="attributes"]
----
mvn package && java -jar target/graphql-java-server-0.1.0.jar
----

You should see some output like this:
2018-11-19 14:50:22.646  INFO 3712 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Bean with name 'dataSource' has been autodetected for JMX exposure
2018-11-19 14:50:22.651  INFO 3712 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Located MBean 'dataSource': registering with JMX server as MBean [com.zaxxer.hikari:name=dataSource,type=HikariDataSource]
2018-11-19 14:50:22.697  INFO 3712 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2018-11-19 14:50:22.701  INFO 3712 --- [           main] com.graphql.Application                  : Started Application in 8.009 seconds (JVM running for 9.763)

....
Check out the service.

....

----
Call the following from any rest client!

http://localhost:8080/graphql
Request: 
query{
	queryPersonById(id:"101"){
		age
        firstName
        id
	}
}


You should see a response like: 

{
    "queryPersonById": {
        "age": 21,
        "firstName": "person1",
        "id": 101
    }
}

----

....


You will see a new set of RESTful end points added to the application. These are management services provided by Spring Boot.

....
2018-11-19 14:50:22.295  INFO 3712 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/graphql],methods=[POST],produces=[text/plain]}" onto public java.lang.String com.graphql.controller.GraphQLEndPoint.query(java.lang.String)

2018-11-19 14:50:22.297  INFO 3712 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/mongo/save/{id}],methods=[POST]}" onto public void com.graphql.controller.MongoController.save(java.lang.String)

2018-11-19 14:50:22.297  INFO 3712 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/mongo/repo],methods=[GET]}" onto public java.lang.Iterable<com.graphql.model.PersonDetails> com.graphql.controller.MongoController.findByRepo() throws java.io.IOException

----
 Summary
Congratulations on spinning up your first GraphQl APIinside JVM and wrapped in RestController.
----
