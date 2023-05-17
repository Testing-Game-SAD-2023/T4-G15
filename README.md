# SAD-Project-T4 Introduction

Repository for the Software Architecture Design course project (Laurea Magistrale Università degli studi di Napoli Federico II).
Our progress and current iteration objectives can be seen [here](https://trello.com/invite/sadtask4/ATTI54e0f32b2eb2ddf0aab52c944a6a26f4ACEF84C6) on our trello board for the project.

- The Directory **First Iteration** contains all of the files related to the Inception Phase
- The Directory **Second Iteration** contains all the files created from 01/05 to 09/05 2023

# General Architecture Outline

The WebServer GameRepository is a [Multi-layered architecture](https://www.oreilly.com/library/view/software-architecture-patterns/9781491971437/ch01.html).
The Controller Layer contains different handlers. These handlers call the required use cases implemented in the Service Layer.
The Data Access Layer contains all the classes required for database queries and the entities.
Other Design Patterns utilized are the [Repository Pattern](https://learn.microsoft.com/en-us/dotnet/architecture/microservices/microservice-ddd-cqrs-patterns/infrastructure-persistence-layer-design) and Facade Pattern.

![Model](https://github.com/micvita/SAD-Project-T4/blob/main/Third%20Iteration/Diagrams%20PNGs/Architecture%20Diagram%20Spike%201.png)

# Entity Diagram 

The following diagram describes the entities contained in the Data Access Layer, **at the moment**.

![Model](https://github.com/micvita/SAD-Project-T4/blob/main/Second%20Iteration/DiagramsPNGs/Class%20Diagram%20Games%20Repository.png)

