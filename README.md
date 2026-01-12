# FINAL AUTOMATION TEST – WEB & API

Project ini merupakan final project automation testing yang dibuat menggunakan Java dan Gradle.  
Automation test mencakup Web UI testing dan API testing dalam satu repository sebagai portfolio QA Automation.

---

## Tools yang Digunakan
Java, Gradle, Selenium WebDriver, Cucumber (Gherkin), Rest Assured, dan GitHub Actions.

---

## Target Pengujian
Web UI menggunakan website https://www.saucedemo.com/  
API menggunakan public API https://dummyapi.io/docs

---

## Skenario Automation Test

### Web Automation Test
Pengujian web mencakup:
- Login berhasil
- Login gagal (negative test)
- Menambahkan product ke cart
- Menghapus product dari cart
- End to End checkout sampai berhasil

### API Automation Test
Pengujian API mencakup:
- Get list user
- Create user
- Update user
- Delete user
- Get list tag
- Negative test dengan user ID tidak valid

---

## Cara Menjalankan Automation Test

### Menjalankan Web Test (Headless)
Untuk menjalankan web automation test dalam mode headless:

Windows (PowerShell):
$env:HEADLESS="true"  
./gradlew clean webTest  

Mac / Linux:
HEADLESS=true ./gradlew clean webTest  

### Menjalankan API Test
./gradlew clean apiTest

---

## Report
Project ini menggunakan Cucumber HTML Report.

Setelah test dijalankan, report dapat diakses melalui browser pada:
- Web Test: build/reports/cucumber/web/cucumber.html
- API Test: build/reports/cucumber/api/cucumber.html

---

## GitHub Actions
Project ini menggunakan GitHub Actions sebagai pipeline automation.

Pipeline akan berjalan saat:
- Push ke branch main
- Pull Request ke branch main
- Manual trigger

Pipeline menjalankan web automation test dan API automation test secara otomatis.

---

## Catatan
Web automation dijalankan dalam mode headless pada CI.  
Project ini dibuat sebagai portfolio automation testing.
