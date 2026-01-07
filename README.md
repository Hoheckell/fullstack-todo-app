# Fullstack Todo List - Spring Boot 3 & Angular 18

Este projeto é uma demonstração de arquitetura moderna, escalável e resiliente, integrando um backend robusto em **Java 17 / Spring Boot 3.4** com um frontend reativo em **Angular 18**. A solução é totalmente conteinerizada e utiliza **PostgreSQL** para persistência de dados.

## 🏗 Arquitetura do Sistema

A aplicação foi desenhada seguindo princípios de alta disponibilidade e separação de responsabilidades (SoC):

```mermaid
graph TD
    User((Usuário)) -->|Porta 80| Angular[Angular 18 Frontend]
    Angular -->|REST API :8081| Spring[Spring Boot API]
    Spring -->|JPA/Hibernate 6| Postgres[(PostgreSQL)]
    
    subgraph Docker Infrastructure
        Angular
        Spring
        Postgres
    end