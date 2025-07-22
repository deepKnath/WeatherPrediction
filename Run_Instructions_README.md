##  WeatherPrediction (Run Instructions)

## Prerequisites
- Java 21+
- Maven
- Docker
- Git

## Build & Run Locally (Without Docker)


```bash
cd backend/WeatherForecast/demo
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Access API:
```
GET method
http://localhost:8080/weatherForecast?city=London
```

---

## Run with Docker

### 1. Build Docker Image

```bash
cd backend/WeatherForecast/demo
docker build -t weather-forecast-app .
```

### 2. Run Docker Container

```bash
docker run -p 8080:8080 weather-forecast-app
```

---

## Rebuild After Changes

```bash
docker build -t weather-forecast-app .
```

---

## Swagger UI

View documentation at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Toggle Offline Mode

```http
POST method
http://localhost:8080/weatherForecast/mode?offline=true
```

Use `true` or `false` for offline mode.

---

## CircleCI Setup

1. `.circleci/config.yml` contains necessary setup details.
2. Push the repo to GitHub.
3. Connect it to CircleCI.
4. CircleCI will run build and tests on every push.

---

## ✅ Example API Response

```json
{
  "city": "London",
  "forecast": [
    {
      "date": "2025-07-22",
      "tempHigh": 42.3,
      "tempLow": 29.1,
      "message": "Use sunscreen lotion"
    }
  ]
}
```
