# Medical Office Management System

This project is a web application designed to manage a medical office. The system supports various user accounts, including doctors, patients, and an administrator, and provides CRUD operations for patient records, appointments, and user account management.

## Features
##### User Accounts: Separate roles for doctors, patients, and administrators, each with specific permissions and functionalities.
##### Authentication: Secure login using gRPC, ensuring data security and privacy in compliance with medical data standards.
##### Appointment Management: Patients can book appointments with doctors based on availability; doctors can manage their schedule.

## CRUD Operations:
##### Patients can manage their personal information.
##### Doctors can view and update patient records (with permissions).
##### Admins have full control over managing users, including adding, editing, and removing accounts.
##### Frontend: Built with React.js for a responsive and user-friendly experience.
##### Backend: Developed with Spring Boot to provide reliable RESTful APIs and services.
##### Python Gateway: Facilitates communication between frontend and backend, acting as an intermediary to handle data processing tasks and service orchestration.

## Tech Stack
##### Frontend: React.js, Axios for API requests
##### Backend: Spring Boot (Java), gRPC for secure communications
##### Authentication: gRPC-based service to handle authentication, integrated with user roles and permissions
##### Python Gateway: Middleware for handling requests and data flow between frontend and backend
##### Database: PostgreSQL, MongoDB



