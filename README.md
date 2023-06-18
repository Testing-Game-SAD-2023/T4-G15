# SAD-Project-T4 Introduction

Repository for the Software Architecture Design (SAD) course project.

Our progress and current iteration objectives can be seen [here](https://trello.com/b/fC4JYB2y/task-4) on our trello board for the project.

*THE FINAL REPORT IS AVAILABLE [HERE](https://github.com/Testing-Game-SAD-2023/T4-G15/blob/Version-1.0/doc/Fourth%20Iteration/RelazioneFinaleSAD_T4_G15.pdf)*

In the doc Directory we have all the UML documentation files:
- The Directory **First Iteration** contains all of the files related to the Inception Phase
- The Directory **Second Iteration** contains all the files created from 01/05 to 09/05 2023

# Architecture Outline

The Web Server GameRepository is a [Multi-layered architecture](https://www.oreilly.com/library/view/software-architecture-patterns/9781491971437/ch01.html).

The Controller Layer contains different handlers. These handlers call the requested use cases implemented in the Service Layer.
The Data Access Layer contains all the classes required for database queries (DAO and Repository patterns) and all the entities described in the Class Diagram.

Other Design Patterns utilized are the [Repository Pattern](https://learn.microsoft.com/en-us/dotnet/architecture/microservices/microservice-ddd-cqrs-patterns/infrastructure-persistence-layer-design) and **Facade Pattern**.

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/Version-1.0/doc/Fourth%20Iteration/PNGsDesignUML/Architecture%20Diagram.png" width="80%">
</p>

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/Version-1.0/doc/Fourth%20Iteration/PNGsDesignUML/Design%20Architecture%20GS%20Component%20Diagram.png" width="80%">
</p>

## Game Repository API

The following document describes all the services* exhibited by our [architecture interface](https://github.com/Testing-Game-SAD-2023/T4-G15/blob/Version-1.0/doc/Fourth%20Iteration/RelazioneFinaleSAD_T4_G15.pdf).

*the services exhibited are the only functions implemented at the moment, more functions can be implemented on request. Open an issue on Github to request it.
## Controller Layer

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/Version-1.0/doc/Fourth%20Iteration/PNGsDesignUML/Class%20Diagram%20Controller%20Layer.png" width="60%">
</p>

## Service Layer

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/Version-1.0/doc/Fourth%20Iteration/PNGsDesignUML/Service%20Layer%20Class%20Diagram.png" width="60%">
</p>

## Repository Diagram (DAL)

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/Version-1.0/doc/Fourth%20Iteration/PNGsDesignUML/Repositories%20%20Class%20Diagram.png" width="60%">
</p>

## Entity Diagram (DAL)

The following diagram describes the entities contained in the Data Access Layer, **at the moment**.

<p align="center" width="100%">
<img src="https://github.com/Testing-Game-SAD-2023/T4-G15/blob/Version-1.0/doc/Fourth%20Iteration/PNGsDesignUML/Class%20Diagram%20Games%20Repository.png" width="80%">
</p>
