package ru.praktikum.stellarburgers.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverInitializer {
    private  static final String YANDEX_DRIVER_PATH = String.format("%s/%s",
            System.getenv("WebDriver"), "chromedriver-yandex");
    private  static final String YANDEX_BROWSER_PATH =
            "C:\\Users\\Anatoliy\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";

    public static WebDriver createWebDriver(){
        String browser = System.getProperty("browser");
        if (browser == null){
            return createChromeDriver();
        }
        switch (browser){
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }
    private static WebDriver createChromeDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        return new ChromeDriver(chromeOptions);
    }
    private static WebDriver createYandexDriver(){
        System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);
        ChromeOptions yandexOptions =new ChromeOptions();
        yandexOptions.setBinary(YANDEX_BROWSER_PATH);
        return new ChromeDriver(yandexOptions);
    }
}
