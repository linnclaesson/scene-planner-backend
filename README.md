# Scene-planner backend

This is the backend application for **Scenplaneraren**, a tool for organizing rehearsals of scenes and assigning roles to actors. It is built using Java Spring Boot and and communicates with a frontend built with React via a REST API, using a PostgreSQL database.

## Prerequisites

### Docker

We will use docker to set up our postgreSQL database.

1. Make sure you have docker installed. Check this in your terminal by writing:

```
docker --version
```

If you don't have docker installed, go to https://www.docker.com/get-started/ for further instructions.

2. When docker is installed, try checking your version again and it should confirm it in your terminal.

## Setup

1. Clone the project to your local machine in a suitable folder.

## Install all dependencies:

1.  In the terminal run:

        mvn clean install

## Start database

1.  Make sure you have docker running. If you have the Desktop version you need to make sure it is open.

2.  Start database by opening a new terminal and write:

        docker compose up -d

3.  Confirm it worked by looking if you have a running container with the project name in the Docker desktop.

## Start backend

Now you are ready to run the backend. Open a separate terminal from the one you are running the database in and write:

        ./mvnw spring-boot:run

Now it should be running and be ready to start the frontend application!
