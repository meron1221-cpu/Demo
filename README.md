# 🚀 User Management System (Spring Boot + React)

## 🌟 Features
✅ **JWT Authentication** (Login/Logout)  
✅ **Admin Dashboard** (User CRUD Operations)  
✅ **Profile Management** (View & Update)  
✅ **Role-Based Access Control** (Admin/User)  
✅ **Responsive UI** with Loading States & Error Handling  
✅ **Secure API Endpoints** (Protected Routes)

## 🛠️ Tech Stack
| **Frontend** | **Backend** | **Database** |
|--------------|-------------|--------------|
| React | Spring Boot | MySQL |
| Axios | Spring Security | JPA/Hibernate |
| React Router | JWT | BCrypt Password Hashing |

## 📂 Project Structure
```
user-management/
├── backend/ (Spring Boot)
│   ├── src/main/java/
│   │   ├── config/ (Security, CORS)  
│   │   ├── controller/ (API Endpoints)  
│   │   ├── dto/ (Request/Response Models)  
│   │   ├── entity/ (User Model)  
│   │   ├── repository/ (JPA Repo)  
│   │   ├── service/ (Business Logic)  
│   ├── src/main/resources/application.properties  
│
├── frontend/ (React)  
│   ├── public/  
│   ├── src/  
│   │   ├── components/ (Login, Profile, UserMgmt)  
│   │   ├── service/ (API Calls)  
│   │   ├── App.js (Routing)  
│   ├── package.json  
```

## 🚀 Quick Start

### Backend Setup (Spring Boot)
1. Configure database in `application.properties`:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/userdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```
2. Run the application:
   ```bash
   cd backend
   mvn spring-boot:run
   ```

### Frontend Setup (React)
1. Install dependencies:
   ```bash
   cd frontend
   npm install
   ```
2. Start the development server:
   ```bash
   npm run dev
   ```

## 🔥 API Testing with Postman

### Authentication Endpoints
- **Register User**: `POST /auth/register`
- **Login**: `POST /auth/login`

### Protected Endpoints (Require JWT)
- **Get All Users**: `GET /admin/get-all-users`
- **Get User Profile**: `GET /adminuser/get-profile`
- **Update Profile**: `PUT /adminuser/update-profile`

## 🛡️ Security Best Practices
🔒 JWT stored securely  
🔒 BCrypt password hashing  
🔒 Role-based API protection  
🔒 CORS configured for frontend only

## contact
📧 Email: meronnisrane@gmail.com  


