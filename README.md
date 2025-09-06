📦 E-Shop Database & Entities

This project uses PostgreSQL as the main database. Below are the main tables and their corresponding JPA entity classes.

🗄️ Database Schema

users → Manages customer accounts

addresses → Stores user addresses (linked to users)

categories → Product categories

products → Products in the store (linked to categories)

product_images → Product gallery (linked to products)

orders → Customer orders (linked to users and addresses)

order_items → Items inside each order (linked to orders & products)

reviews → Customer reviews (linked to users & products)

payments → Payment records (linked to orders)

📌 JPA Entity Classes

Each table has a mapped entity in com.example.eshop.entity:

User → users

Address → addresses

Category → categories

Product → products

ProductImage → product_images

Order → orders

OrderItem → order_items

Review → reviews

Payment → payments

🔗 Relationships

User ↔ Address → One-to-Many

Category ↔ Product → One-to-Many

Product ↔ ProductImage → One-to-Many

Product ↔ Review → One-to-Many

Order ↔ OrderItem → One-to-Many

User ↔ Order → One-to-Many

Order ↔ Payment → One-to-One

⚙️ Setup

1) Create PostgreSQL database:
    1.1 CREATE DATABASE trainings;
    1.2 Restore from The Tables And Data From src/main/resources/Training.sql File.
2) Update application.yml with your DB credentials:
spring:
   datasource:
   url: jdbc:postgresql://localhost:5432/eshopdb
   username: your_username
   password: your_password
   driver-class-name: org.postgresql.Driver
   jpa:
   hibernate:
   ddl-auto: update
   show-sql: true
3) Access The Swagger from the browser: localhost:8080/swagger-ui/index.html