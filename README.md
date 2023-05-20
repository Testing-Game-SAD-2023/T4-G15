# SAD-Project-T4 Introduction

Repository for the Software Architecture Design (SAD) course project.

Our progress and current iteration objectives can be seen [here](https://trello.com/b/fC4JYB2y/task-4) on our trello board for the project.

In the doc Directory we have all the UML documentation files:
- The Directory **First Iteration** contains all of the files related to the Inception Phase
- The Directory **Second Iteration** contains all the files created from 01/05 to 09/05 2023

# Architecture Outline

The Web Server GameRepository is a [Multi-layered architecture](https://www.oreilly.com/library/view/software-architecture-patterns/9781491971437/ch01.html).

The Controller Layer contains different handlers. These handlers call the requested use cases implemented in the Service Layer.
The Data Access Layer contains all the classes required for database queries (DAO and Repository patterns) and all the entities described in the Class Diagram.

Other Design Patterns utilized are the [Repository Pattern](https://learn.microsoft.com/en-us/dotnet/architecture/microservices/microservice-ddd-cqrs-patterns/infrastructure-persistence-layer-design) and **Facade Pattern**.

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/main/doc/Third%20Iteration/DiagramsPNGs/Architecture%20Diagram%20Spike%201.png" width="50%">
</p>

# Service Layer

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/main/doc/Third%20Iteration/DiagramsPNGs/Service%20Layer%20Class%20Diagram.png" width="50%">
</p>

# Repository Diagram (DAL)

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/main/doc/Third%20Iteration/DiagramsPNGs/Repositories%20%20Class%20Diagram.png" width="50%">
</p>

# Entity Diagram (DAL)

The following diagram describes the entities contained in the Data Access Layer, **at the moment**.

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/main/doc/Second%20Iteration/DiagramsPNGs/Class%20Diagram%20Games%20Repository.png" width="70%">
</p>
