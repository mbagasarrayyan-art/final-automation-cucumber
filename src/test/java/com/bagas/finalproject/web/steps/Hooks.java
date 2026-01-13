package com.bagas.finalproject.web.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Hooks {
    public static WebDriver driver;

    @Before("@web")
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();


        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("safebrowsing.enabled", true); // tetap aman, tapi popup password manager off

        options.setExperimentalOption("prefs", prefs);


        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);


        String headless = System.getenv("HEADLESS");
        if (headless != null && headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1366,768");
        }


        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");


        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // kita pakai explicit wait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
    }

    @After("@web")
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
