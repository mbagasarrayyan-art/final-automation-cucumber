# final-automation-cucumber (API + Web)

Automation test framework using:
- Java
- Gradle
- Cucumber (Gherkin) + JUnit Platform
- Rest Assured (API)
- Selenium WebDriver (Web UI)
- GitHub Actions (CI)

## Project Structure
- `src/test/java/com/bagas/finalproject/api`  
  API automation (client, steps, runner)
- `src/test/java/com/bagas/finalproject/web`  
  Web UI automation (pages, steps, runner)
- `src/test/resources/features/api`  
  API feature files tagged `@api`
- `src/test/resources/features/web`  
  Web feature files tagged `@web`

## How to Run (Local)

### Run API tests only
```bash
./gradlew clean apiTest
