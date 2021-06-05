# booknet
[![MIT License](https://img.shields.io/pypi/l/aiogram.svg?style=flat-square)](https://opensource.org/licenses/MIT)
[![Hits-of-Code](https://hitsofcode.com/github/smithros/booknet)](https://hitsofcode.com/view/github/smithros/booknet)
[![Commit activity](https://img.shields.io/github/commit-activity/y/smithros/booknet)](https://github.com/smithros/booknet/graphs/commit-activity)
<br>
This project supposed to be my bachelor course work. <br>
It's a free online library of books that will allow users to read books, write review on them, rate them, search books, create own books collections with favourite, read and so on.
It also proposes different announcements, notifications, achievements about books.<br>

Link: [booknet](https://booknet-diploma.herokuapp.com/#/).

Inspired by [this project](https://github.com/smithros/nc-training-center).
## Stack

- [Spring Framework](https://spring.io/)
- [Angular](https://angular.io/)
- [PostgreSQL](https://www.postgresql.org/)
- [Hibernate](https://hibernate.org/)

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 4](https://maven.apache.org)
- [NodeJS](https://nodejs.org)
- [NPM](https://www.npmjs.com/)
- [Docker](https://www.docker.com/)

To build the project simply run this command in the root directory: 
```shell
mvn clean install 
```
## Running the application locally

This project is a Maven multimodule project with two submodules: 
 - backend
 - frontend

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the `main` method in the `BookNetApplication` class from your IDE 
and go to `http://localhost:8080`. 

Alternatively you can use the 
[Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

To run Angular application on your local machine 
you can go to the Angular root directory and run:

```shell
ng serve --open
```
This will automatically open `http://localhost:4200` in your browser.

## Docker
To run the app in container in detached mode use next command:
```shell
docker compose up -d
```

## Deploy

For application deployment free cloud application platform [Heroku](https://dashboard.heroku.com) is used.
