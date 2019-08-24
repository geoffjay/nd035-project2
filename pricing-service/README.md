# Pricing Service

The Pricing Service is a microservice that simulates a backend that
would store and retrieve the price of a vehicle given a vehicle id as
input.

## Features

- REST WebService integrated with Spring Boot

## Instructions

#### TODOs

- [x] Convert the Pricing Service to be a microservice.
- [ ] Add an additional test to check whether the application appropriately generates a price for a given vehicle ID

#### Run the code

To run this service you execute:

```
$ mvn clean package
```

```
$ java -jar target/pricing-service-0.0.1-SNAPSHOT.jar
```

## Testing

```sh
curl -XGET http://localhost:8082/services/price?vehicleId=1
```
