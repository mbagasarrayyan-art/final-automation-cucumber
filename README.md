# Final Automation Test Framework

Automation test framework using **Selenium**, **Rest Assured**, **Cucumber**, **Gradle**, and **GitHub Actions**.

## Tech Stack
- Java
- Gradle
- Selenium WebDriver
- Rest Assured
- Cucumber (Gherkin)
- GitHub Actions

## How to Run Test

### Run API Tests
```bash
./gradlew apiTest
```

### Run Web UI Tests
```bash
./gradlew webTest
```

## Test Report
After execution, Cucumber reports will be generated at:

### API Reports
- HTML: `build/reports/cucumber/api/cucumber.html`
- JSON: `build/reports/cucumber/api/cucumber.json`

### Web Reports
- HTML: `build/reports/cucumber/web/cucumber.html`
- JSON: `build/reports/cucumber/web/cucumber.json`

## CI/CD
GitHub Actions workflow runs:
- Manual trigger
- On every Pull Request
