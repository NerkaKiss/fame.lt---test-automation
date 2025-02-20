package pom.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--force-device-scale-factor=0.7"); // mazinimas - didinimas
        chromeOptions.addArguments("--lang=en");
//        chromeOptions.addArguments("--window-size=5000,3000");
//        chromeOptions.addArguments("--window-position=1000,0");
//        chromeOptions.addArguments("--headless=new"); // silent atidarymas
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver(chromeOptions));
        /**
         * Zoom solution for Mac
         */
//        driver.get("chrome://settings");
//        ((JavascriptExecutor) driver).executeScript("chrome.settingsPrivate.setDefaultZoom(0.5)");
    }

    public static void setFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver.set(new FirefoxDriver(firefoxOptions));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}
