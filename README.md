spring Boot Demo Project — Simple User CRUD



This repository contains a lightweight Spring Boot demo application that implements basic CRUD (Create, Read, Update, Delete) operations for managing users.  

It is designed as a simple learning/reference project for developers exploring Spring Boot and RESTful APIs.



---



## Features



The project provides a `UserController` that supports:



- **Create User**

- **Retrieve User** (single user or list of users)

- **Update User**

- **Delete User**



---



## Running the Project (Docker-Based Setup)



This project includes a `setup.sh` script which automatically:



- Builds and starts the **Spring Boot application container**

- Starts a **MySQL database container**

- Configures networking and environment variables between them



### 1. Give execution permission to the script



```bash

chmod -x setup.sh

./setup.sh
