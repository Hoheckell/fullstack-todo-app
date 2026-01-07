# Fullstack Todo List - Spring Boot 3 & Angular 18

Este projeto é uma demonstração de arquitetura moderna utilizando **Java 17**, **Spring Boot 3.4**, **Angular 18 (Signals)** e **Docker**.

## 🏗 Arquitetura
A aplicação segue os princípios de sistemas distribuídos e resilientes:
- **Backend:** Spring Boot com JPA/Hibernate 6 implementando **Soft Delete** via `@SQLRestriction`.
- **Frontend:** Angular Standalone Components com gerenciamento de estado via **Signals**.
- **Database:** PostgreSQL 15.
- **Orquestração:** Docker Compose com Healthchecks para garantir a ordem de inicialização.

## 🚀 Como Executar
Certifique-se de ter o Docker e o Docker Compose instalados. No terminal, execute:

```bash
docker compose up --build

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