# Challenge-Week-XII-Races

Design of a Microservices Aplication developed with Spring Boot 3.1.3, Java 17 and Maven. This project uses HTTP operations (GET, POST, PUT and DELETE) to manage the Races and are distributed as ms-cars, ms-races and ms-history. In this project we have the possible actions to create Cars, Pilots, Tracks and Races, History are automatically generated 
when the race result is released.
In this project I chose to use MySQL and MongoDB as Databases as it would be a great option to meet the project's needs.

Races must have at least 3 cars and must not exceed 10 cars to be created. 
Due to the requirement of being able to have only one CRUD in ms-cars, it was decided to use the creation of a pilot together with the creation of a car, while the Track and Race entities are created separately, each having all four types of HTTP requests.

## Technologies
* Model Mapper 2.4.4
* Lombok
* Spring Boot DevTools
* Spring Web
* Spring Data JPA
* MySQL Driver SQL
* MongoDB
* Validation
* Spring Boot Starter Test
* OpenFeign
* RabbitMQ

## Installation

```git clone https://github.com/arturazambuja/Races-challenge-Week-XII```

```cd Races-challenge-Week-XII```

## Prerequisites
- Spring Boot 3.1.3
- Maven
- Java 17
- MySQL
- MongoDB
- IntelliJ IDEA
