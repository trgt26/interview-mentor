# Interview Mentor – Java Backend

> **Smart AI-Powered Interview & Learning Platform**

This is the backend service of **Interview Mentor**, a modern platform designed to help candidates prepare for software engineering interviews through AI-driven mock interviews, structured learning, peer-to-peer sessions, and competitive challenges.

---

## 🚀 Features

- **AI-Powered Mock Interviews** with real-time feedback  
- **Live Peer Interview Sessions** via WebSockets  
- **Structured Learning Modules** with AI tutoring  
- **Skill Assessments & Performance Tracking**  
- **1v1 Coding Contests and Logic Battles**  
- **Role-Based Access** for Learners, Admins, and Curators  
- **Secure Authentication** using Spring Security & JWT  

---


## 🛠 Tech Stack (Backend)

| Tool                  | Purpose                                           |
|-----------------------|---------------------------------------------------|
| **Java (Spring Boot)**| Core backend framework                            |
| **MySQL**| Relational database for persistent storage        |
| **WebSocket (STOMP)** | Real-time communication (interviews, contests)    |
| **Spring Security**   | Authentication & Authorization                    |
| **JWT**               | Token-based security                              |
| **Gemini API** | AI interviewer & tutor integration              |
| **Docker**            | Containerization                                  |
| **NGINX**             | Reverse proxy and routing                         |
| **GitHub Actions**    | CI/CD pipeline                                    |

---

## 🚀 Quick Start

### Prerequisites
- Java 21
- MariaDB/MySQL
- Gemini API Key

### Environment Setup

1. **Copy the environment template:**
   ```bash
   cp env.example .env
   ```

2. **Configure your environment variables in `.env`:**
   ```bash
   # AI Configuration
   GEMINI_API_KEY=your_gemini_api_key_here
   
   # Database Configuration
   DATABASE_URL=jdbc:mariadb://localhost:3306/interview_mentor?allowPublicKeyRetrieval=true&useSSL=false
   DATABASE_USERNAME=root
   DATABASE_PASSWORD=admin
   
   # JWT Configuration
   JWT_SECRET=your_jwt_secret_here
   ```

3. **Get your Gemini API Key:**
   - Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
   - Create a new API key
   - Add it to your `.env` file

4. **Start the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

### Development Notes
- The `.env` file is ignored by git to keep sensitive data secure
- Each developer should have their own `.env` file with their specific configuration
- Default values are provided in `application.properties` as fallbacks