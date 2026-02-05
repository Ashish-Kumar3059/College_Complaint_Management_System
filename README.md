# College_Complaint_Management_System
 College Complaint Management System

A web-based application that allows students to register college-related complaints and track their status online.  
The system helps administrators manage, update, and resolve complaints efficiently using a centralized database.

Built using Spring Boot, REST APIs, and MySQL for reliable backend performance.

---

## 🚀 Features

- Student complaint registration
- View complaint status in real-time
- Admin panel to manage and update complaints
- RESTful APIs for all operations
- Secure database storage using MySQL
- Clean and scalable backend architecture

---

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- REST APIs
- Maven
- Postman (for API testing)

---

## 📂 Project Structure
college-complaint-system/ │ ├── src/main/java/ │   └── com.project.complaint │       ├── controller │       ├── service │       ├── repository │       └── model │ ├── src/main/resources/ │   ├── application.properties │ ├── pom.xml └── README.md
Copy code

---

## ⚙️ Setup Instructions

### 1. Clone the Repository
bash
git clone https://github.com/your-username/college-complaint-system.git
2. Create MySQL Database
Copy code
Sql
CREATE DATABASE complaint_db;
3. Configure Database
Edit application.properties:
Copy code
Properties
spring.datasource.url=jdbc:mysql://localhost:3306/complaint_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4. Run the Project
Copy code
Bash
mvn spring-boot:run
or run directly from your IDE.

📡 API Endpoints (Sample)
Method
Endpoint
Description
POST
/complaints
Create complaint
GET
/complaints
View all complaints
GET
/complaints/{id}
View by ID
PUT
/complaints/{id}
Update status
DELETE
/complaints/{id}
Delete complaint

📊 Database Design
Main tables:
users
complaints
Each complaint stores:
Student name
Description
Category
Status
Date created
