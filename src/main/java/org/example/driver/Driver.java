package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver;

    private Driver() {
        String browserName = System.getProperty("browser");
        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome";
        }

        switch (browserName) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
            case "yandex":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                String binaryYandexDriverFile = "yandexdriver.exe";
                options.setBinary(binaryYandexDriverFile);
                driver = new ChromeDriver(options);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);

        }

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public static Driver getInstance() {
        return new Driver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
