# 🎓 FreelanceHub — Student Freelance Marketplace

A full-stack web application connecting student clients with student freelancers for academic and technical projects.

---

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
## 📸 Screenshots

### Login Page
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/2d671492-c0df-4dfa-af99-aee2ba3b5edf" />

### Client Dashboard
```
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/61646f74-d2fe-4581-a805-fbe3cc10cda1" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/00f54953-1997-49f7-bdff-1b01ad8a6d0b" />
```
### Client Profile
<img width="1108" height="706" alt="image" src="https://github.com/user-attachments/assets/290518cb-7374-4ef3-a188-3755f7e6dd9e" />

---
### Freelancer Dashboard
<img width="1285" height="797" alt="image" src="https://github.com/user-attachments/assets/6b17ffce-54dc-4881-b6df-6e1c70d3f3a6" />

<img width="1267" height="622" alt="image" src="https://github.com/user-attachments/assets/3205ca83-a7ae-4045-bbbd-cab66f2525e7" />

<img width="1277" height="836" alt="image" src="https://github.com/user-attachments/assets/9f9ae5ce-05e9-40d1-98dd-a568fbe0bf49" />

### Admin Dashboard

## 🤖 AI Features

- **Description Generator** — Client posts a project title → AI writes a professional description
  <img width="1332" height="907" alt="image" src="https://github.com/user-attachments/assets/796afa17-2c59-4831-bf48-5aa2282759e0" />

- **Bid Ranker** — Client views bids → AI ranks them best to worst with recommendations
  <img width="1327" height="837" alt="image" src="https://github.com/user-attachments/assets/9f0c3113-7d46-46cc-821c-f2194a96cb44" />

Powered by **Groq API** (Llama 3.1 — free tier)

## 📹 Video Call Features
<img width="1352" height="876" alt="image" src="https://github.com/user-attachments/assets/04189c46-399e-4420-b0e1-04da9c0f6e3a" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a3b9e9f8-85aa-408f-b5da-cb0bcaa9495d" />

Powered by *Jitsi Meet**
---

## QR Profile
<img width="1402" height="712" alt="image" src="https://github.com/user-attachments/assets/e13b35cc-7c51-4771-8113-b81b747f5ab6" />

## Project Timeline
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a814f945-7e83-45cc-877e-317e5e8c5f1a" />

## Deadline Calender
<img width="1153" height="913" alt="image" src="https://github.com/user-attachments/assets/93efd292-5a63-41c0-8164-ee70b090d8bb" />

## Contracts
<img width="876" height="826" alt="image" src="https://github.com/user-attachments/assets/7d289dc0-5b8f-4199-a773-9a8250cd1ece" />

## 👩‍💻 Developer

**RAMESH RENUKA**
3rd Year — Artificial Intelligence & Machine Learning
Saveetha Engineering College

---

## 📄 License
This project is for educational purposes.

















