# Evolvion Accounts Service

![Accounts_Service](https://img.shields.io/badge/Accounts_Service%20Microservice-6DB33F?style=for-the-badge&logo=java&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) 
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white) 
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white) 
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

## Architecture Overview

The Evolvion Accounts Service is a microservice designed for managing financial records such as invoices, payments, and expenses. It follows a microservices architecture and is developed using Java Spring Boot. The service interacts with a MySQL database to perform CRUD operations on financial records.

## Key Features

- **Invoice Management**: Create, retrieve, and delete invoices.
- **Payment Management**: Create, retrieve, and delete payments.
- **Expense Management**: Create, retrieve, and delete expenses.
- **Exception Handling**: Comprehensive error handling with custom exceptions.
- **Input Validation**: Ensure data integrity with bean validation.
- **Logging**: Detailed logging of operations and exceptions for monitoring and debugging.

## 🛠️ Project Structure

```plaintext
Evolvion_Accounts_Service
├── controller
│   ├── ExpenseController
│   ├── InvoiceController
│   └── PaymentController
├── controller_advise
│   ├── Expense_Exceptions
│   ├── Invoice_Exceptions
│   ├── Payment_Exceptions
│   ├── ErrorResponse
│   └── GlobalExceptionHandler
├── model
│   ├── Core
│   ├── enums
│   └──
├── dto
│   ├── ExpenseDTO
│   ├── InvoiceDTO
│   └── PaymentDTO
├── mappers
│   ├── ExpenseMapper
│   ├── InvoiceMapper
│   └── PaymentMapper
├── repository
│   ├── ExpenseRepository
│   ├── InvoiceRepository
│   └── PaymentRepository
└── service
    ├── Implementation
    │   ├── ExpenseServiceImpl
    │   ├── InvoiceServiceImpl
    │   └── PaymentServiceImpl
    └── Interface
        ├── ExpenseService
        ├── InvoiceService
        └── PaymentService

```


## 🛠️ Services Overview

### Invoices

- **Get All Invoices**
  - `GET /api/invoices`
  - **Response**: `List<InvoiceDTO>`

- **Get Invoice by ID**
  - `GET /api/invoices/{id}`
  - **Response**: `InvoiceDTO`

- **Create New Invoice**
  - `POST /api/invoices`
  - **Request Body**: `InvoiceDTO`
  - **Response**: `InvoiceDTO`

- **Delete Invoice by ID**
  - `DELETE /api/invoices/{id}`
  - **Response**: `204 No Content`

### Payments

- **Get All Payments**
  - `GET /api/payments`
  - **Response**: `List<PaymentDTO>`

- **Get Payment by ID**
  - `GET /api/payments/{id}`
  - **Response**: `PaymentDTO`

- **Create New Payment**
  - `POST /api/payments`
  - **Request Body**: `PaymentDTO`
  - **Response**: `PaymentDTO`

- **Delete Payment by ID**
  - `DELETE /api/payments/{id}`
  - **Response**: `204 No Content`

### Expenses

- **Get All Expenses**
  - `GET /api/expenses`
  - **Response**: `List<ExpenseDTO>`

- **Get Expense by ID**
  - `GET /api/expenses/{id}`
  - **Response**: `ExpenseDTO`

- **Create New Expense**
  - `POST /api/expenses`
  - **Request Body**: `ExpenseDTO`
  - **Response**: `ExpenseDTO`

- **Delete Expense by ID**
  - `DELETE /api/expenses/{id}`
  - **Response**: `204 No Content`

## Package Descriptions

- **controller**: Contains REST controllers for handling HTTP requests and responses.
- **controller_advise**: Handles global exception management and custom error responses.
- **DTOs**: Data Transfer Objects for interacting with clients.
- **mappers**: Maps between entities and DTOs.
- **models**: Contains core entities and enums.
- **repository**: Interfaces for database interactions.
- **service**: Contains business logic and service implementations.
- **utils**: Utility classes, including validation logic.

## Exception Handling

The service uses custom exceptions to handle various error scenarios. Examples include:
- `InvoiceCreationException`
- `InvoiceNotFoundException`
- `InvoiceUpdateException`
- `ExpenseCreationException`
- `ExpenseNotFoundException`
- `ExpenseUpdateException`
- `PaymentCreationException`
- `PaymentNotFoundException`
- `PaymentUpdateException`

## Validation

The service uses Java Bean Validation  to validate input data. Validation annotations are applied to DTO fields to ensure data integrity.

## Logging

Logging is implemented using SLF4J. Logs capture key operations and exceptions to aid in monitoring and debugging.

## Contributing Guidelines

We welcome contributions to the Evolvion Accounts Service project! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them with clear messages.
4. Push your changes to your fork.
5. Submit a pull request to the main repository.
