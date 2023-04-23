Sakila Rest Api
<div align="center">
  <h3 align="center">Sakila Rest Api</h3>
  <p align="center">
    A powerful API service that provides various functions for developers
    <br />
    <a href="https://documenter.getpostman.com/view/14572081/2s93Y2T2vK"><strong>Explore API documentation »</strong></a>
    <br />
    <br />
  </p>
</div>
About The Project

Sakila Rest Api is a movie store provider that offers different services for developers. The project aims to provide REST and SOAP services for CRUD (Create, Read, Update, Delete) operations related to renting movies to customers.
Get Started

To use this repository, follow these steps:

    Download and install Tomcat 10 or any other container.
    Run Tomcat.
    Clone the repository:

sh

git clone https://github.com/aadelaahmed/SakilaAPI.git

    Modify Persistance.xml properties. Add the following properties:

xml

<property name="hibernate.connection.username" value="user"/>
<property name="hibernate.connection.password" value="password"/>

Note: The user should have privileges to access the Sakila table. It is recommended to create a standalone user for Sakila to avoid redundant table errors.

    Modify Pom.xml Tomcat Plugin. Add the following configuration:

xml

<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <username>Enter user name here</username>
        <password>Enter password here</password>
        <url>http://localhost:8080/manager/text</url>
        <path>/sakila</path>
    </configuration>
</plugin>

Note: Make sure that port 8080 is available to avoid connection errors with Tomcat.

    Now you are ready to deploy or redeploy using Tomcat plugin:

sh

//mvn deploy
mvn clean compile install tomcat7:deploy

//mvn redeploy
mvn clean compile install tomcat7:redeploy

//mvn undeploy
mvn clean compile install tomcat7:undeploy

Congratulations! You can now interact with our API resources.
Supported Resources

We support eleven resources, each with its own requests and responses. To learn more about these resources, refer to the API Documentation.

    Films
    Actors
    Countries
    Cities
    Categories
    Customers
    Languages
    Categories
    Payments
    Rentals
    Staffs

Technology Used

    Java
    Maven (https://maven-badges.herokuapp.com/maven-central/cz.jirutka.rsql/rsql-parser)
