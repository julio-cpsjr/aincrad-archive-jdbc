# 📚 Library Management System in Java (JDBC – Version 2.0)


<img src="cardinal/img/cardinal.png" height="300" width="300"></img>

The Cardinal Library System has evolved to Version 2.0.

This version was developed in **pure Java with JDBC integration**, focusing on strengthening backend fundamentals through relational database modeling and real business rule implementation.

The goal of this project is to move beyond in-memory data structures and implement real database persistence, preparing the foundation for future REST APIs and cloud-based applications.

---

## 🎯 Objective

Master core backend development concepts, including:

- Object-Oriented Programming (OOP)
- Relational database modeling
- JDBC integration
- Business rule enforcement
- Layered architecture design
- Transaction handling

---

## ✅ Features

- Book registration
- User registration
- Book loan creation
- Book return processing
- Loan history tracking
- Validation of business rules:
    - Prevent loan if the book is unavailable
    - Prevent loan if the user is blocked
- Automatic update of book availability
- Loan status management (ACTIVE / FINISHED / LATE)
- Database auto-initialization on application startup
- Exception handling and input validation

---

## 🧠 Concepts Practiced

- Object-Oriented Programming (OOP)
- Separation of concerns (Model, Repository, Service, UI)
- JDBC connection management
- PreparedStatement usage
- ResultSet mapping
- Foreign key relationships
- Enum persistence in database
- Business rule orchestration in service layer
- Transaction control
- Exception handling

---

## 🛠️ Technologies Used

- Java (JDK 17 or higher recommended)
- JDBC (SQLite driver)
- Relational Database (SQLite)
- Layered Architecture
- Console-based interface

---

## 🗄️ Database Structure

### book
- id
- title
- author
- isbn
- category
- available (boolean)

### user
- id
- name
- email
- status (ACTIVE / BLOCKED)

### loan
- id
- book_id (FK)
- user_id (FK)
- loan_date
- due_date
- return_date
- status (ACTIVE / FINISHED / LATE)

Relationships:

User (1) —— (N) Loan (N) —— (1) Book

---

## 🚀 Evolution from Version 1.0

Version 1.0:
- In-memory storage using Collections
- Focus on OOP fundamentals

Version 2.0:
- Real database persistence
- Foreign key relationships
- Transaction handling
- Stronger business rule enforcement
- Clear architectural separation

This progression demonstrates technical growth and backend maturity.

---

## 🔮 Next Steps

- Implement unit tests (JUnit)
- Add logging system
- Introduce fine calculation for late returns
- Build REST API using Spring Boot
- Add authentication and authorization
- Deploy to cloud environment

---

## 👨‍💻 Author

Julio Cesar
