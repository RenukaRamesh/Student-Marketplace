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
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/6d702aa8-802b-4007-9519-217e1e986ac2" />

### Create Account
<img width="800" height="1000" alt="image" src="https://github.com/user-attachments/assets/342d9cfb-df87-48aa-8ee5-e8d8d1af080f" />

### Admin Dashboard
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/f4501b23-6591-4f81-9721-cf8b0a691d7e" />

### Admin Actions
<img width="900" height="400" alt="image" src="https://github.com/user-attachments/assets/1466f8d0-eaa1-4a0a-b857-05371f060628" />

<img width="900" height="400" alt="image" src="https://github.com/user-attachments/assets/8aea3e09-3757-4b2b-9f35-837d48de82a5" />

<img width="900" height="400" alt="image" src="https://github.com/user-attachments/assets/af3b432c-aa68-418e-94d8-13379170ba13" />

### Client Dashboard
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/1d41e746-38fe-4523-9669-3ab9d2d8feda" />

### Client Profile
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/fe6d17e1-5739-4d0f-ad29-6e68d35a261e" />

### Freelancer Dashboard
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/6a24813d-5074-4fbd-b83f-8308ca3e412e" />

### Freelancer Profile
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/4081d92a-489e-4eaa-9bcb-e6182034e4a8" />

## 🤖 AI Features

- **Description Generator** — Client posts a project title → AI writes a professional description
<img width="900" height="800" alt="image" src="https://github.com/user-attachments/assets/e5192fca-bc48-46fe-9afb-77594050a68a" />

- **Bid Ranker** — Client views bids → AI ranks them best to worst with recommendations
<img width="900" height="800" alt="image" src="https://github.com/user-attachments/assets/62126161-a825-4aa0-86b5-2d72d73f9cf3" />

**Powered by **Groq API**** (Llama 3.1 — free tier)

## 📹 Video Call Features
<img width="900" height="700" alt="image" src="https://github.com/user-attachments/assets/2e5a8eba-bfcc-4813-b019-f61a7e3ddb3c" />

<img width="900" height="700" alt="image" src="https://github.com/user-attachments/assets/7f90527c-9c4e-4454-967e-02b0b75f1e61" />

**Powered by **Jitsi Meet****
---

## QR Profiles
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/c0feb09b-8655-473d-80e2-9ff8c71d7767" />

## Project Timeline
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/f32431e5-db2f-4600-98d9-9c13bf9779f8" />

## LeaderBoard
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/d39798d1-9947-45cc-a1ca-f3b4bb83c636" />

## Quick Replies
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/e3d60611-7543-454f-a3d6-e171ee4a5dc4" />

## Deadline Calender
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/91272003-1202-4354-b0fc-6a16a7728eb3" />

## Darkmode Facility
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/acc013a2-e12c-41a8-8c75-e455b0d4b92a" />
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/5fc9cd16-f05b-49ac-8b47-466353e44c15" />

## Contracts
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/ae95646f-ee42-4e24-acbc-cff52aff7ac4" />

## 👩‍💻 Developer

**RAMESH RENUKA**
3rd Year — Artificial Intelligence & Machine Learning
Saveetha Engineering College

---

## 📄 License
This project is for educational purposes.

















