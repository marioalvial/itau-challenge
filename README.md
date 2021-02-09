# Itaú Back-end Challenge

[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)

## Table of content
- [Prerequisites](#prerequisites)
- [Running](#running)
- [Documentation](#documentation)
- [Testing](#testing)
- [Architecture](#architecture)
- [Built With](#built-with)

## Prerequisites

- **[Required]** [Docker](https://www.docker.com/): As this project is dockerized.
- **[Required]** [Docker-Compose](https://docs.docker.com/compose/): To run project with its dependencies
- **[Required]** [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html): To run gradle tasks


## Running

First, clone the project:

```shell
git clone git@github.com:marioalvial/itau-challenge.git
cd itau-challenge
```

Then execute the following command: 

```shell
make run
```

Then click in the following bottom in order to execute requests

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/0054b06ad0d2f36ff6f1)

## Documentation

### Postman Docs

This projects uses Postman Docs as API Reference Documentation. 

All API documentation is through the link below:

- [Documentation](https://documenter.getpostman.com/view/8923459/TW77f33f)

## Testing

```shell
make test
```

# Architecture

The project architecture is heavily based on Approach 3 of article: [https://proandroiddev.com/multiple-ways-of-defining-clean-architecture-layers-bbb70afa5d4a](https://proandroiddev.com/multiple-ways-of-defining-clean-architecture-layers-bbb70afa5d4a)
 
The approach is a package by feature combine with Clean Architecture

## Built With

- [Kotlin](https://kotlinlang.org/) - Programming language
- [IntelliJ](https://www.jetbrains.com/idea/) - IDE
- [Ktor](https://https://ktor.io/) - Web Framework
- [Gradle](https://gradle.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Containerization Platform
