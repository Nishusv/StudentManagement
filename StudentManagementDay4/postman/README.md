# Test the REST API with Postman

## Start the backend first
From the project root, open a terminal:

```bash
cd backend
mvn spring-boot:run
```

The API runs at `http://localhost:8080`. The default profile uses an in-memory H2 database and automatically inserts three sample students on startup if the table is empty.

## Import the collection
1. Open Postman.
2. Click **Import**.
3. Select `postman/Student-Management-API.postman_collection.json`.
4. Open the imported **Student Management API - Day 4 Demo** collection.
5. Send **Login - get JWT token** first. The collection test script saves the JWT automatically.
6. Send **List all students** to see the three preloaded records.
7. Try **Get student by ID**, then **Create student**, **Update student**, and **Delete created student** in that order.
8. Send **List students without token** to verify the protected endpoint returns `401 Unauthorized`.

## Demo credentials
- Email: `admin@demo.com`
- Password: `Admin123!`

## Endpoints
| Method | Endpoint | Purpose | Authentication |
|---|---|---|---|
| POST | `/api/auth/login` | Obtain JWT | Not required |
| GET | `/api/students` | List students | Bearer token |
| GET | `/api/students/{id}` | Get one student | Bearer token |
| POST | `/api/students` | Create student | Bearer token |
| PUT | `/api/students/{id}` | Update student | Bearer token |
| DELETE | `/api/students/{id}` | Delete student | Bearer token |

## Student JSON example
```json
{
  "name": "Meera Nair",
  "email": "meera.nair@example.com",
  "course": "Full Stack Java",
  "marks": 89.5
}
```

Marks must be between 0 and 100. Email addresses must be unique. The collection uses collection variables `baseUrl`, `token`, `studentId`, and `createdStudentId`; edit `baseUrl` if your backend uses a different host or port.

**Note:** This is a teaching demo. Its fixed credentials and development JWT secret are not suitable for production.
