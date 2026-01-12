Final Automation Test – Web & API

Project ini adalah final project automation testing menggunakan Java dan Gradle.
Automation test mencakup Web UI dan API dalam satu repository.

Tools dan teknologi yang digunakan:
- Java
- Gradle
- Selenium WebDriver
- Cucumber (Gherkin)
- Rest Assured
- Allure Report
- GitHub Actions

Target pengujian:
- Web UI: https://www.saucedemo.com/
- API: https://dummyapi.io/docs

--------------------------------------------------

Struktur Project:

src/test/java
- api
  - client
  - runner
  - steps
- web
  - pages
  - runner
  - steps
  - utils

src/test/resources/features
- api
- web

--------------------------------------------------

Skenario Test yang Dibuat

Web Automation Test:
1. Login berhasil
2. Login gagal (negative test)
3. Add product ke cart
4. Remove product dari cart
5. End to End checkout sampai sukses

API Automation Test:
1. Get list user
2. Create user
3. Update user
4. Delete user
5. Get list tag
6. Negative test: get user dengan id tidak valid

--------------------------------------------------

Cara Menjalankan Test (Local)

Web Test (Headless):

Windows PowerShell:
$env:HEADLESS="true"
./gradlew clean webTest

Mac / Linux:
HEADLESS=true ./gradlew clean webTest

API Test:
./gradlew clean apiTest

--------------------------------------------------

Report

Cucumber Report:
- Web: build/reports/cucumber/web/cucumber.html
- API: build/reports/cucumber/api/cucumber.html

Allure Report:
Generate report:
./gradlew allureReport

Open report:
./gradlew allureServe

--------------------------------------------------

GitHub Actions

Project ini memiliki GitHub Actions workflow yang akan berjalan:
- Saat Pull Request ke branch main
- Saat push ke branch main
- Manual trigger

Workflow akan menjalankan:
- API automation test
- Web automation test (headless)

Report akan di-upload sebagai artifact dan dapat di-download melalui tab Actions di GitHub.

--------------------------------------------------

Catatan:
- Web test dijalankan dalam mode headless pada CI
- Project ini dibuat sebagai portfolio automation testing
