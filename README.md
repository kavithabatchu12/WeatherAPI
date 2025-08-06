# OpenWeatherMap API Test Automation Framework

This project contains an automated test framework for the OpenWeatherMap API, built with Java, Rest-Assured, and TestNG. It is designed to be a robust and maintainable solution for API test automation.

## Features
-   **API Test Coverage**: Validates various endpoints and scenarios of the OpenWeatherMap API.
-   **Data-Driven**: Configuration and API keys are managed in external properties files.
-   **Schema Validation**: Ensures the API response structure is correct using JSON Schema.
-   **Reusable API Client**: A centralized `ApiClient` class to manage request specifications.
-   **Clear Reporting**: Leverages TestNG for test execution and reporting.

## Technologies Used
-   **Language**: Java 11
-   **API Automation**: Rest-Assured
-   **Testing Framework**: TestNG
-   **Build Tool**: Apache Maven
-   **IDE**: Eclipse

## Prerequisites
-   Java Development Kit (JDK) 11 or higher.
-   Apache Maven installed and configured.
-   An active OpenWeatherMap API Key.

## How to Set Up and Run

**Step 1: Clone the Repository**
```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd <your-project-folder>
