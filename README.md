# Library Management System (Java + MySQL)

## Overview
A console-based Library Management System developed using Java, MySQL, and JDBC. The project helps manage books, students, book issuance, and returns through a simple menu-driven interface.

## Features
- Admin Login Authentication
- Add New Books
- View Available Books
- Add Students
- Issue Books
- Return Books
- MySQL Database Integration
- Input Validation

## Technologies Used
- Java
- MySQL
- JDBC (MySQL Connector/J)
- Eclipse IDE

## Project Workflow

1. Created MySQL Database
2. Created Tables (Books, Students, Issued_Books)
3. Established JDBC Connection
4. Developed Java Model Classes
5. Implemented Library Services
6. Added Admin Login
7. Added Issue & Return Logic
8. Added Input Validation
9. Organized Code into Packages
10. Uploaded Project to GitHub

## Project Structure

```text
LibraryManagementSystem
│
├── src
│   │
│   ├── database
│   │   └── DBConnection.java
│   │
│   ├── model
│   │   ├── Book.java
│   │   └── Student.java
│   │
│   ├── service
│   │   └── LibraryService.java
│   │
│   └── main
│       ├── Main.java
│       └── AdminLogin.java
│
├── library_management.sql
│
└── README.md
```

## Database Tables

- books
- students
- issued_books

