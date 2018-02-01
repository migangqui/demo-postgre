# Spring-PostGIS Demo Project GEO
Spring + PostgreSQL/PostGIS project to test geo queries performance.

## Introduction
This project is prepared to test performance of geo queries with a [PostGIS](https://postgis.net/) database.
Include the following tools:

 - JPA
 - Hibernate Spatial
 - PostGIS DB
 - Spring Boot
 
## How to work
 To test queries with this project you must follow the next steps:
 1. Run an instance of PostGIS. You can easily do it with docker: 
 `docker run --name some-postgis -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres@123 -e POSTGRES_DB=springbootdb -d mdillon/postgis`
 2. Set options in application.yml to create objects in DB.
 data:
  quantity:
    total: 10000 # Total object to create
    found: 152   # Object to find in the geo query
  insert: true   # Decide if insert new objects in DB
  delete: true   # Decide if delete the object before create again

 3. Run as Java app the class `PostgreSqlDemoApplication.java`

## Internal process
Before run the performance test, the app will chek if geo index is created to field location. If not, it will be created to index geographical data.

    CREATE INDEX event_gix ON event USING GIST(location)

 
