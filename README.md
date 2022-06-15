# car-lease-service

**Please start the application as Spring boot application. This application supports login and registration.
In addition to that APIs provided for CRUD operation on CAR and CUSTOMER entity with role based access and 
there is also an API to calculate lease rate.** 

* > Used @Valid annotation in REST controller methods to do the input validation. 
* > Flyway is used to load the data into the database when the server starts.
* > h2 in memory database used to persist the data. 
* > Please access the database with this link - http://localhost:8080/h2-console/login.do
* > spring-boot-starter-security library added to secure the application. 
* > SLF4j is used for logging.
* > Google java format followed. 

## Improvements: Thought of doing lot of things , however due to time constraints, could not work on the below items.  

* > The entity object is directly used in the REST apis , should have been different DTOs created, and mapped to the entity class. 
* > Every input should be validated using @Valid.
* > Introduce different service layer for the lease rate calculation and invoke that from controller. 
* > Additional Testcases - Wiremock could have been used? 
* > Javadoc not present in every class/method. 
* > Could have used a database (postgres) in running in a docker container. 
* > Azure Cloud with CI/CD pipeline. 
* > Open Source vulenrability checks using nexusIQ. 
* > Sonar along with Jacoco maven plugin for code quality & code coverage. 
* > Fortify static code analyzer to identify security vulnerabilities in the source code early in the software development life cycle
* > Mutation Testing - pitest maven plugin.
* > com.tngtech.archunit -> archunit-junit5 to write architecture tests to ensure a particular layer is only accessed by a particular package. This way we can maintain segregation of layers within the application.

## APIs include -

### /api/car-lease/

    GET /cars - To get all cars
    POST /cars - Create a new car
    GET /cars/{carId} - To get a particular car
    PUT /cars/{carId} - Update a car
    DELETE /cars/{carId} - Delete a car

    GET /customers - To get all customers
    POST /customers - Create a new customer
    Request Payload: 
    {
        "id": 9,
        "name": "test9",
        "street": "STREET 1",
        "houseNumber": "HOUSE 1",
        "zipCode": "ZIP1",
        "place": "PLACE1",
        "emailAddress": "TEST1@EMAIL.COM",
        "phoneNumber": "1234567891",
        "createdAt": "2022-06-14T22:00:00.000+00:00",
        "updatedAt": null
    }
    GET /customers/{customerId} - To get a particular customer
    PUT /customers/{customerId} - Update a customer
    DELETE /customers/{customersId} - Delete a customer

    POST /calc-lease-rate - 

### /api/auth

    POST /signup - To signup a new user 
    Request Payload:
    {
        "username": "test1",
        "email": "test1@test1.com",
        "password": "test1password",
        "role": ["user","mod"]
    }
    
    POST /signin - To signin with the signed up user
    Request Payload: 
    {
        "username": "test1",
        "password": "test1password"
    }

    POST /signout - To clear the cookie
