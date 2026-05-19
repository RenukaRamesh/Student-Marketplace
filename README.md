# 🎓 FreelanceHub — Student Freelance Marketplace

A full-stack web application connecting student clients with student freelancers for academic and technical projects.

## 🚀 Features

### Client
- Post projects with AI-powered description generation
- Receive and manage bids from freelancers
- AI bid ranking to find the best freelancer
- Accept bids and generate contracts
- Deadline calendar and project timeline
- Rate and review freelancers

### Freelancer
- Browse open projects with smart skill-match percentage
- Submit bids with proposals
- Track earnings (pending vs received)
- Project timeline and deadline calendar
- Leaderboard ranking by reviews

### Common
- Real-time messaging with video call (Jitsi)
- QR code profile sharing
- Dark mode support
- PWA (installable as mobile app)
- Achievement badges and profile strength meter
- Admin dashboard with analytics

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 21, Spring Boot 3.5, Spring Security |
| Frontend | Thymeleaf, Bootstrap 5, HTML/CSS/JS |
| Database | MySQL 8.0, Spring Data JPA, Hibernate |
| AI | Groq API (Llama 3.1) |
| Auth | BCrypt, Spring Security, Role-based Access |
| Other | Jitsi Meet API, QR Code, PWA, Chart.js |

---

## ⚙️ Setup Instructions

### Prerequisites
- Java 21
- MySQL 8.0
- Maven

### 1. Clone the repository
```bash
git clone https://github.com/RenukaRamesh/Student-Marketplace.git
cd FreelanceHub
```

### 2. Create MySQL database
```sql
CREATE DATABASE freelance_db;
```

### 3. Configure application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/freelance_db
spring.datasource.username=root
spring.datasource.password=your_password
groq.api.key=your_groq_api_key
```

### 4. Run the application
```bash
mvn spring-boot:run
```

### 5. Access
```
http://localhost:8081
```
---

## 👤 Default Accounts
### Admin Account
```
Email: admin@marketplace.com
Password: Admin@123
```
### Client Account
```
Email: client@marketplace.com
Password: Client@123
```
### Freelancer Account
```
Email: freelancer@marketplace.com
Password:Free@123
```
---
## 📸 Screenshots

### Login Page
<img width="800" height="600" alt="image" src="https://github.com/user-attachments/assets/6d702aa8-802b-4007-9519-217e1e986ac2" />

### Create Account
<img width="800" height="600" alt="image" src="https://github.com/user-attachments/assets/342d9cfb-df87-48aa-8ee5-e8d8d1af080f" />

### Admin Dashboard
<img width="800" height="600" alt="image" src="https://github.com/user-attachments/assets/f4501b23-6591-4f81-9721-cf8b0a691d7e" />

### Admin Actions
<img width="800" height="600" alt="image" src="https://github.com/user-attachments/assets/1466f8d0-eaa1-4a0a-b857-05371f060628" />

<img width="800" height="600" alt="image" src="https://github.com/user-attachments/assets/8aea3e09-3757-4b2b-9f35-837d48de82a5" />

<img width="800" height="600" alt="image" src="https://github.com/user-attachments/assets/af3b432c-aa68-418e-94d8-13379170ba13" />

### Client Dashboard

### Client Profile

### Freelancer Dashboard

### Freelancer Profile



## 🤖 AI Features

- **Description Generator** — Client posts a project title → AI writes a professional description

- **Bid Ranker** — Client views bids → AI ranks them best to worst with recommendations

**Powered by **Groq API**** (Llama 3.1 — free tier)

## 📹 Video Call Features


**Powered by **Jitsi Meet****
---

## QR Profiles

## Project Timeline

## Deadline Calender

## Darkmode Facility

## Contracts

## 👩‍💻 Developer

**RAMESH RENUKA**
3rd Year — Artificial Intelligence & Machine Learning
Saveetha Engineering College

---

## 📄 License
This project is for educational purposes.

















