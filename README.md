# Basic Transaction REST API
basic-transaction-api-v1

Hi! This is a basic Spring-Boot application where we are exposing a Transaction API.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them:

```
Maven
```

Optional

```
* Git
* Code editor: VisualStudio or Eclipse
* Docker
```

### Installing

A step by step series of examples that tell you how to get a development env running

Validate that Maven is correctly configured

```
mvn -v
```

Download or clone the project

```
Download: Click on download link
Clone: git clone https://github.com/gusleyva/gustavo-leyva-basic-transaction-api.git
```

Open a terminal window (bash/terminal) and move to the project

```
cd ~/workspace/gustavo-leyva-basic-transaction-api/
```

Do a maven clean install. Validate that you have an internet connection to download dependencies

```
mvn clean install
```
If everything is compiled correctly you will see something similar to:

```
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ basic-transaction-api-v1 ---
[INFO] Installing /workspace/SpringBoot/basic-transaction-api-v1/target/basic-transaction-api-v1-0.0.1-SNAPSHOT.jar to /.m2/repository/basic-transaction-api-v1/basic-transaction-api-v1/0.0.1-SNAPSHOT/basic-transaction-api-v1-0.0.1-SNAPSHOT.jar
[INFO] Installing /workspace/SpringBoot/basic-transaction-api-v1/pom.xml to /.m2/repository/basic-transaction-api-v1/basic-transaction-api-v1/0.0.1-SNAPSHOT/basic-transaction-api-v1-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

```
There are multiple forms to start the application

1. Spring boot

```
mvn spring-boot:run
```

2. Packaging and executing

```
mvn clean package
java -jar target/basic-transaction-api-v1-0.0.1-SNAPSHOT.jar
```

3. Docker

```
docker build -t basic-transaction-api-v1:1.0 .
docker run -d -p 8080:8080 -t basic-transaction-api-v1:1.0
```

Validate the application is up and running, paste the following url in your browser:

```
http://localhost:8080/
```
Validate all end points with api-docs

```
http://localhost:8080/swagger-ui.html#/
```
Example of existing end points

```
http://localhost:8080/api/v1/transactions/
http://localhost:8080/api/v1/transactions/{transaction_id}
http://localhost:8080/api/v1/transactions/user/{user_id
http://localhost:8080/api/v1/transactions/accumulated/{user_id}
http://localhost:8080/api/v1/transactions/weeklyreport/{user_id}
http://localhost:8080/api/v1/transactions/random
```
## Class Diagram

![Class diagram](UML_Class_Diagram.png)

## Built With

* [Spring-boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2](h2database.com/html/main.html) - In-memory database
* [Swagger](h2database.com/html/main.html) - Generate API-Docs

## Authors

* **[Gustavo Leyva](https://www.linkedin.com/in/gustavo-leyva-b9493846/)** - *Initial work*


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
