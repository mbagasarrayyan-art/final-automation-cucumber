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
```

### Run Web tests only
```bash
./gradlew clean webTest
```

## Reports (Generated Locally)

After running tests, Cucumber reports will be generated:

### API Reports
- HTML: `build/reports/cucumber/api/cucumber.html`
- JSON: `build/reports/cucumber/api/cucumber.json`

### Web Reports
- HTML: `build/reports/cucumber/web/cucumber.html`
- JSON: `build/reports/cucumber/web/cucumber.json`

## CI (GitHub Actions)
Workflow runs:
- Manual trigger (workflow_dispatch)
- On every Pull Request

Workflow file:
- `.github/workflows/automation-tests.yml`
