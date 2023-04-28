package ru.praktikum.stellarburgers.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.stellarburgers.constants.Browser;

public class DriverInitializer {
    public  static WebDriver getDriver(Browser browser){
        switch (browser){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case YANDEX:
                System.setProperty("webdriver.chrome.driver",
                        "src/test/resources/yandexdriver-23.3.0.2247-win/yandexdriver.exe");
                return new ChromeDriver();

            default:
                throw new EnumConstantNotPresentException(Browser.class, "BROWSER");
        }
    }
}
