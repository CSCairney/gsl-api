# Glasgow Social League API (GSL-API)

**GSL-API** is a RESTful API built for the Glasgow Social League application. It allows users to keep track of their matches in various games during sessions. The API is built using Spring Boot and is designed to be modular, scalable, and secure.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Running the Application](#running-the-application)
    - [Running Tests](#running-tests)
- [Deployment](#deployment)
- [Environment Variables](#environment-variables)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- **Java 22**: The primary programming language used for developing the API.
- **Spring Boot 3.3.2**: Framework used for building the API.
    - **Spring Data JPA**: For database access and management.
    - **Spring Security**: For securing the API.
    - **Spring Web**: For creating RESTful endpoints.
    - **Spring Validation**: For validating request payloads.
- **PostgreSQL**: The database used for persisting data.
- **Flyway**: For database migrations.
- **JWT (JSON Web Tokens)**: For managing authentication and authorization.
- **MapStruct**: For object mapping.
- **Lombok**: To reduce boilerplate code.
- **Docker**: Used for managing the development environment.
- **Heroku**: The cloud platform used for hosting the API.

## Features

- **User Authentication and Authorization**: Secured endpoints using JWT.
- **Match Tracking**: Allows users to record and track their game matches.
- **Database Migrations**: Managed by Flyway.
- **Modular Architecture**: Built using Spring Modulith for a clean and maintainable codebase.

## Getting Started

### Prerequisites

- **Java 22**: Make sure you have Java 22 installed on your machine.
- **Docker**: Required if you want to run the database locally using Docker.
- **Heroku CLI**: If you want to deploy or manage the application on Heroku.

### Running the Application

1. **Clone the repository**:

    ```bash
    git clone https://github.com/CSCairney/gsl-api.git
    cd gsl-api
    ```

2. **Set up environment variables**:

   Create a `.env` file in the root of the project and populate it with the required environment variables (see [Environment Variables](#environment-variables) section).

3. **Build and run the application**:

    ```bash
    ./gradlew bootRun
    ```

   Alternatively, you can create a fat JAR and run it:

    ```bash
    ./gradlew build
    java -jar build/libs/glasgow-social-league-0.1.1-SNAPSHOT.jar
    ```

### Running Tests

The application includes unit and integration tests. To run the tests:


./gradlew test

## Deployment

The API is hosted on Heroku. You can access the live API at:

[https://gsl-api.herokuapp.com](https://gsl-api.herokuapp.com)

To deploy the application to Heroku:

1. **Login to Heroku**:

    ```bash
    heroku login
    ```

2. **Create a new Heroku app**:

    ```bash
    heroku create gsl-api
    ```

3. **Deploy your code**:

    ```bash
    git push heroku master
    ```

4. **Set up environment variables**:

    Use the Heroku CLI to set environment variables:

    ```bash
    heroku config:set SPRING_DATASOURCE_URL=your_database_url
    heroku config:set SPRING_DATASOURCE_USERNAME=your_database_username
    heroku config:set SPRING_DATASOURCE_PASSWORD=your_database_password
    heroku config:set JWT_PUBLIC_KEY=your_jwt_public_key
    heroku config:set JWT_SECRET_KEY=your_jwt_secret_key
    ```

5. **Open your app**:

    ```bash
    heroku open
    ```

## Environment Variables

The application relies on the following environment variables:

```bash
- **SPRING_DATASOURCE_URL**: JDBC URL for the PostgreSQL database.
- **SPRING_DATASOURCE_USERNAME**: Username for the database.
- **SPRING_DATASOURCE_PASSWORD**: Password for the database.
- **JWT_PUBLIC_KEY**: Public key for verifying JWTs.
- **JWT_SECRET_KEY**: Secret key for signing JWTs.
```

## API Documentation

Detailed API documentation can be generated using Spring REST Docs. To generate the documentation:

```bash
./gradlew asciidoctor
