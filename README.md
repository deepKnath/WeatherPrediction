##  WeatherPrediction (Spring Boot Microservice + React)

## Problem Statement

Develop, test, and deploy a **Java full stack microservice** that provides the **next 3 days' high and low temperatures** for a given city, along with safety predictions such as:
- "Carry umbrella" – if rain is predicted.
- "Use sunscreen lotion" – if temperature > 40°C.
- "It’s too windy, watch out!" – if wind speed > 10 mph.
- "Don’t step out! A Storm is brewing!" – if thunderstorms are forecast.


## Tech Stack

| Layer          | Tech                         |
|----------------|------------------------------|
| Frontend       | React (TypeScript)           |
| Backend        | Spring Boot                  |
| API Integration| REST API                     |
| Build Tools    | Maven, Docker                |
| Deployment     | CircleCI + Terraform         |
| Documentation  | Swagger (OpenAPI)            |

## Functional Requirements

- 3-day weather forecast by city  
- Offline fallback support  
- Alert messages for weather conditions  
- React-based responsive UI  
- Secure API key via env variables  
- Swagger-enabled REST API with HATEOAS  
- Dockerized backend & frontend  
- CI/CD pipeline via CircleCI  
- Terraform provisioning scripts

## API Endpoint

**`GET /weatherForecast?city=London`**

# `Payload`
{
  "city": "London",
  "forecast": [
    {
      "date": "2025-07-21",
      "tempHigh": 42,
      "tempLow": 28,
      "message": "Use sunscreen lotion"
    },
    ...
  ],
  "_links": {
    "self": {
      "href": "http://localhost:8080/weatherForecast?city=London"
    }
  }
}


## Sequence Diagram

## Swagger API Documentation
Open in browser:
  http://localhost:8080/swagger-ui/index.html

Includes:
- Endpoint documentation
- Sample input/output
- Error codes
- HATEOAS links

## Offline Mode
- When REST API fails, fallback to a local fallback.json

## Design Patterns Used
- Factory	  -> Create weather alerts dynamically
- Strategy	-> Format different alert messages
- Singleton	-> Reuse fallback JSON reader

## Error Handling
- 400	Invalid city input
- 404	City not found
- 500	Internal server error

## Author
Name: Deep Kumar
