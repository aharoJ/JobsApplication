# JobHub

## Project Overview
This project demonstrates the transition from a monolithic architecture to a robust microservices-based system. The application serves as a job application platform with dedicated services handling Company, Job, and Review functionalities. The new architecture improves scalability, maintainability, and overall system performance.

## Architecture
The system is split into three independent microservices:
- **Company Service:** Manages company-related data and operations.
- **Job Service:** Handles job postings, job details, and application processes.
- **Review Service:** Manages user reviews and feedback for job postings and companies.

Each microservice is built using Spring Boot and communicates via RESTful APIs. The services are containerized and orchestrated using Docker Compose for seamless deployment and scalability.

## Technologies Used
- **Programming Language:** Java
- **Framework:** Spring Boot
- **Build Tool:** Maven
- **Containerization:** Docker, Docker Compose
- **Testing:** JUnit (Unit and Integration Testing)
- **Version Control:** Git

## Setup & Installation
### Prerequisites
- Java Development Kit (JDK)
- Maven
- Docker & Docker Compose

### Building the Project
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to each service directory (company, job, review) and build the project using Maven:
   ```bash
   mvn clean install
   ```

### Running the Application
1. **Microservices:**
   - Use Docker Compose to start all services:
     ```bash
     docker-compose up --build
     ```
   - This command builds and runs the containers, orchestrating the microservices locally.

2. **Monolithic Version:**
   - Navigate to the `monolithic` directory and run the application:
     ```bash
     mvn spring-boot:run
     ```

## Testing & Deployment
- **Testing:** 
  - Unit and integration tests are provided in each microservice. Run tests using:
    ```bash
    mvn test
    ```
- **Deployment:** 
  - Containerized deployment is facilitated via Docker. The Docker Compose file is configured to manage inter-service communication and environment configuration.

## Project Structure
The repository is organized into separate modules for both the microservices and the monolithic version:

```
.
├── company
│   ├── HELP.md
│   ├── mvnw
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── io/aharoj/company
│   │   │           ├── CompanyApplication.java
│   │   │           ├── controller/CompanyController.java
│   │   │           ├── model/Company.java
│   │   │           ├── repository/CompanyRepository.java
│   │   │           └── service/CompanyService.java
│   │   └── test
│   │       └── java
│   │           └── io/aharoj/companyms/CompanymsApplicationTests.java
├── job
│   ├── HELP.md
│   ├── mvnw
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── io/aharoj/job
│   │   │           ├── JobApplication.java
│   │   │           ├── controller/JobController.java
│   │   │           ├── dto/JobWithCompanyDTO.java
│   │   │           ├── model/Job.java
│   │   │           ├── repository/JobRepository.java
│   │   │           └── service/JobService.java
│   │   └── test
│   │       └── java
│   │           └── io/aharoj/jobms/JobmsApplicationTests.java
├── review
│   ├── HELP.md
│   ├── mvnw
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── io/aharoj/review
│   │   │           ├── ReviewApplication.java
│   │   │           ├── controller/ReviewController.java
│   │   │           ├── model/Review.java
│   │   │           ├── repository/ReviewRepository.java
│   │   │           └── service/ReviewService.java
│   │   └── test
│   │       └── java
│   │           └── io/aharoj/reviewms/ReviewmsApplicationTests.java
├── docker-compose.yml
├── microservice_notes.md
├── README.md
└── run.sh
```
