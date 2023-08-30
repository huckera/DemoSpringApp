# Introduction

The purpose of this application is to manage collaborators of a non-profit organization.

## Used technologies

- SpringBoot
- SpringMVC 
- Thymeleaf (frontend)

## Available API endpoints

When the server is operational, API documentation is available at the following endpoints:
- /v3/api-docs: OpenAPI documentation in json format
- /v3/api-docs.yaml: OpenAPI documentation in yaml format
- /swagger-ui.html: OpenAPI documentation using Swagger-UI

# Deployment

## Prerequisites

### Database

1. Deploy a database of your choice. 

The following docker command can be used:

`docker run -p 33060:3306 --name <container-name> -e MYSQL_ROOT_PASSWORD=<password> -d mysql:latest`

In this case the database can be reached at localhost:33060.

- Create schema `alphacollab`

