##  WeatherPrediction (Spring Boot Microservice + React)

## Run Instruction
https://github.com/deepKnath/WeatherPrediction/blob/main/Run_Instructions_README.md

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

## API Endpoints

1> Get 3-day weather forecast
**`GET /weatherForecast?city=London`**

### `Payload`
{
    "city": "London",
    "forecast": [
        {
            "date": "2025-07-22",
            "tempHigh": 298.38,
            "tempLow": 289.33,
            "message": "Use sunscreen lotion"
        },
        {
            "date": "2025-07-23",
            "tempHigh": 296.19,
            "tempLow": 288.61,
            "message": "Carry umbrella"
        },
        {
            "date": "2025-07-24",
            "tempHigh": 292.57,
            "tempLow": 287.96,
            "message": "Carry umbrella"
        }
    ],
    "_links": {
        "self": {
            "href": "http://localhost:8080/weatherForecast?city=London"
        }
    }
}

2> Toggle offline mode
**`POST /weatherForecast/toggle?offline=false`**

### `Payload`
Mode set to: ONLINE

3> Check offline mode
**`GET /weatherForecast/mode`**

### `Payload`
Current mode: ONLINE

## Sequence Diagram
https://viewer.diagrams.net/index.html?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=Weather%20Prediction%20by%20Deep&layers=1&nav=1&title=WeatherPrediction.drawio&dark=auto#R%3Cmxfile%3E%3Cdiagram%20name%3D%22Page-1%22%20id%3D%22oI2dmRshh7b_3A8m25Nl%22%3E7Zrdj%2BI2EMD%2Fmkjtw0kkJgEe%2BexdtSfR3Vb37E1mwVonzjoOLP3rO06chGCg9Hp8tKx4IJ6Mv2Z%2BnhksHDKO33%2BRNF1%2BFRFwx%2BtE7w6ZOJ7ndgMfv7RkU0r8vhEsJIuMUiN4Yn%2BCEXaMNGcRZC1FJQRXLG0LQ5EkEKqWjEop1m21F8Hbs6Z0AZbgKaTcln5jkVqW0r7Xa%2BSfgS2W1cxuMCjfxLRSNjvJljQS6y0RmTpkLIVQ5VP8PgaujVfZpew3O%2FC2XpiERJ3S4fNqun6I%2BLAfvS3f3PFvahAsPplRVpTnZsN%2FZCDNgtWmsgKuPdWPecwf2AtwlmBrlIJkMSjUJxNuxPNGNlovmYKnlIa66xr5QBn6SVHU031cbEdSpL9TuQC9h06hwDlNM%2FZczK0lEsJcZmwFj5CVfGipyJWeb1z7XQtTIRVKMiVxDi0Emqk1ZPg4SmA9jdC31a56I8cL3nJt%2FhHUL2qRQ4Zb7%2Fmz2OrdKHnjHaUDA6xAKoZUHeiKG1xB1OrbaSlIkSeRpdGb4JOxCR40PWjhTZwM3g9i4tbw4akFgd6SG1QxHSpcN%2B3muoHf9Y1s2QK%2FG5hTZ07coh66gRIfDJf%2FgFHPYvQb4OECOROIBjp4mKZoGMcjrmbgp5lExiCJfv7A%2BH4xJp3v4zgIOmfCmPwtxvMv2xiPaPj6QfF9U%2BwH3RujuGtR%2FGsmkiclpC6kvNmX5NNXiIWZ6QPbu8S2d3PYBha2lKtjiM4kjTWfEc2WEBnQUoFEgJyu0A6ZwcWCD8ERr8gUF7IYlYyKD76pt%2BppvcoYpPMDLN5vG9wdDCyLD4K%2BbfGuf66qrWdZ3PFHYxouQS%2BBKcefWPbH%2FSvLgokoAsUL43xHRDlbJNgMISmDRXVChuZFzKJIj703ipxk9cMwHak8bNOTPab3zsV6%2F5jlY5Zl%2B0xfoW%2FCcgv7fdbbNbUSWsrpM%2FC5yJhiYq9nHnYUag9Zvjx8jH6446pD4%2FsnHpozOW5gOe4R3nKdeXBNZa1YXGGU5aLlwl2XPAulRNzKTJjM2lmoyjhkUmeXOpOYzGmyRtnCgnSob1X08FyEr7U79BzHYxeuVeQyhCMGqC54qnB69Beh7UcJnCrM8a1J93mp6Ir7oJsthSK4Z1sjz7Vg64dxl7RjrLkDajxejtj4v17a9yNRXYNtMTFeAtrd64T6REf6ao0q%2Bj%2BEwTsRhu41YAhcvw1Db3ABGOzbukdQuUxugoZWytCNOVUYyvV0ejXk7MB0bzl6BG6%2FBYzXu0T0sK%2FOJixLebHympirJpSrU3NqmDlQf5875wQtaoh3iTBj31Q1dQjudrcgufP8Q64Bhr%2BTf7oXKUbsy586%2F9wADlcPJeSWE5C%2Fk4C6F6lYfIsYfVUINSYdVhcvdxxCbqKE9Yl7ASDsi7imIklg%2FVGO%2FJfKEb9P%2Fg0y2Gz%2B%2FlCqN38iIdO%2FAA%3D%3D%3C%2Fdiagram%3E%3C%2Fmxfile%3E#%7B%22pageId%22%3A%22oI2dmRshh7b_3A8m25Nl%22%7D

## Swagger API Documentation
Open in browser:
  http://localhost:8080/swagger-ui/index.html

Includes:
- Endpoint documentation
- Sample input/output
- Error codes
- HATEOAS links

## Offline Mode
- When toggle set to Offline mode, then it will fetch the dummy data

## Design Patterns Used
- Factory	  -> Create weather alerts dynamically
- Strategy	-> Format different alert messages
- Singleton	-> Reuse fallback JSON reader

## Error Handling
- 400	Invalid city input
- 404	City not found
- 500	Internal server error

 Error Payload
 {
    "error": "city not found",
    "status": 404
}

## Author
Name: Deep Kumar
