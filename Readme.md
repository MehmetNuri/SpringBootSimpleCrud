Simple Crud Spring Boot
===

- **Java Version**          : 11
- **Spring Boot Version**   : 2.4.5 

In this repository, I have prepared a simple CRUD system that will be an example for you. 
I chose PostgreSQL as the database. 
Please  the edit the `application.yaml` file  to your own server settings. 
The route structure is as follows.

| Method | Url | Description
| -----| -----| -----|
|Get    |   http://localhost:8080/categories                    | Get All Categories
|Post   |   http://localhost:8080/categories                    | Create New Category
|Get    |   http://localhost:8080/categories/categoryId         | Get Category By Id
|Put    |   http://localhost:8080/categories/categoryId         | Update Category By Id
|Delete |   http://localhost:8080/categories/categoryId         | Delete Category By Id
|Get    |   http://localhost:8080/categories&sort=id            | Sort returns categories by id
|Get    |   http://localhost:8080/categories&sort=id,asc        | Sort returns categories by id with asc
|Get    |   http://localhost:8080/categories&sort=id,desc       | Sort returns categories by id with desc
|Get    |   http://localhost:8080/categories&sort=name          | Sort returns categories by name
|Get    |   http://localhost:8080/categories&sort=name,asc      | Sort returns categories by name with asc
|Get    |   http://localhost:8080/categories&sort=name,desc     | Sort returns categories by name with desc

#application.yml
```yaml
server:
  error:
    include-message: always
    include-binding-errors: always

spring:
  application:
    name: SimpleCrud
  jpa:
    hibernate:
      ddl-auto: update
  datasource:
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 5
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres

```




