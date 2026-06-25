# Local Development Setup

## Prerequisites
- Java 21
- PostgreSQL 16 (or compatible)
- IntelliJ IDEA
- Gradle 8.0+

## Setup Instructions

### 1. PostgreSQL Configuration
Start PostgreSQL on port 5434:
```bash
# Docker (recommended)
docker run -d \
  --name myhealth-postgres \
  -e POSTGRES_DB=myhealth \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5434:5432 \
  postgres:16-alpine
```

Or use existing PostgreSQL and create database:
```sql
CREATE DATABASE myhealth;
```

### 2. IntelliJ IDEA Setup

#### Open Project
1. Open IntelliJ IDEA
2. File → Open → Select `myHealth` directory
3. Wait for Gradle to sync

#### Configure Run Configuration
1. Run → Edit Configurations
2. Click `+` → Spring Boot
3. Configure:
   - **Name**: MyHealth Dev
   - **Main class**: `ee.spiderolga.myhealth.HealthAnalyticsApplication`
   - **Working directory**: `$MODULE_DIR$`
   - **JDK**: Java 21
   - **Active profiles**: (leave empty for default)

#### Environment Variables (Optional)
```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5434/myhealth
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
```

### 3. Run the Application

#### Option A: Via IntelliJ
1. Click Run button or press `Shift + F10`
2. Application starts on `http://localhost:8090`

#### Option B: Via Gradle
```bash
./gradlew bootRun
```

#### Option C: Build and Run
```bash
./gradlew build
java -jar build/libs/myHealth-1.0.0.jar
```

### 4. Verify Installation

#### Health Check
```bash
curl http://localhost:8090/api/actuator/health
```

Expected response:
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "PostgreSQL"
      }
    }
  }
}
```

#### API Endpoints
```bash
# Get all health records
curl http://localhost:8090/api/v1/health-records

# Create new record
curl -X POST http://localhost:8090/api/v1/health-records \
  -H "Content-Type: application/json" \
  -d '{
    "recordId": "REC001",
    "status": "ACTIVE",
    "startDate": "2024-01-01T00:00:00",
    "endDate": "2024-01-31T23:59:59"
  }'
```

### 5. Database Management

#### Access H2 Console (if using H2)
`http://localhost:8090/api/h2-console`

#### Flyway Migrations
Migrations are in `src/main/resources/db/migration/`
- Automatically run on application startup
- Check logs for migration status

### 6. Development Tips

#### Enable SQL Logging
In `application.yaml`, set:
```yaml
logging:
  level:
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
```

#### Watch Logs
```bash
tail -f logs/myhealth.log
```

#### Rebuild Project
```bash
./gradlew clean build
```

#### Stop PostgreSQL Docker Container
```bash
docker stop myhealth-postgres
docker rm myhealth-postgres
```

## Troubleshooting

### Port 8090 already in use
```bash
# Find and kill process on port 8090
lsof -ti:8090 | xargs kill -9

# Or change port in application.yaml
server:
  port: 8091
```

### PostgreSQL connection refused
- Verify PostgreSQL is running: `docker ps | grep postgres`
- Check port: `docker port myhealth-postgres`
- Verify credentials in `application.yaml`

### Gradle sync issues
- Run: `./gradlew --stop` to stop daemon
- Then: `./gradlew build`
- Or refresh in IntelliJ: View → Tool Windows → Gradle → Refresh

### Flyway migration errors
- Drop database and recreate:
```sql
DROP DATABASE myhealth;
CREATE DATABASE myhealth;
```
- Then restart application

## Next Steps

1. Configure IDE run configuration as described above
2. Start PostgreSQL container
3. Run application via IntelliJ
4. Test endpoints with curl or Postman
5. Begin development!
