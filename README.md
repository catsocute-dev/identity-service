# Identity Service (Spring Boot 3)

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.x-brightgreen)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-green)](#license)

An **Identity and Authentication Service** built with **Spring Boot 3** and **Java 21**.  
It provides **user management**, **role & permission management**, and **JWT-based authentication**.  
This service is designed to act as the **central identity provider** for microservices or monolithic applications.  

---

## 📑 Table of Contents
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [System Requirements](#-system-requirements)
- [Configuration](#-configuration)

---

## ✨ Features
- User registration and login
- JWT authentication with **HS512 signing**
- Token introspection & refresh tokens
- Role-based access control (RBAC)
- Permission-based fine-grained authorization
- RESTful APIs for managing **Users, Roles, Permissions**
- Built-in `admin` user auto-provisioning
- Secure, extensible architecture (can be used as an **Auth service in microservices**)

---

## 🚀 Tech Stack
- **Java 21**  
- **Spring Boot 3.5.x**  
- **Spring Security** (JWT Resource Server)  
- **Spring Data JPA**  
- **Nimbus JOSE JWT** for token signing (HS512)  
- **MySQL 8.x**  
- **Lombok** (boilerplate reduction)  
- **MapStruct** (DTO mapping)  

---

## 🖥️ System Requirements
- JDK **21**
- Maven **3.9+** (or wrapper `mvnw`/`mvnw.cmd`)
- MySQL **8.x**

---

## ⚙️ Configuration
Main config file: `src/main/resources/application.yaml`

```yaml
server:
  port: 8080
  servlet:
    context-path: /identity

spring:
  datasource:
    url: jdbc:mysql://localhost:3307/identity-service
    username: root
    password: <your_password>
  jpa:
    hibernate:
      ddl-auto: update

security:
  jwt:
    signer-key: <your_hs512_signer_key>
