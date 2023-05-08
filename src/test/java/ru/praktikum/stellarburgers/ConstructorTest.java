package ru.praktikum.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.page_objects.MainPage;
import ru.praktikum.stellarburgers.utils.ConfigFileReader;
import ru.praktikum.stellarburgers.utils.DriverInitializer;

import java.time.Duration;


public class ConstructorTest {
    ConfigFileReader configFileReader = new ConfigFileReader();
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        driver = DriverInitializer.createWebDriver();

        driver.get(configFileReader.getApplicationUrl());
        this.mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @After
    public void shutdown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход на вкладку Булки")
    public void clickOnBunsSectionButtonAutoScroll() {
        mainPage.clickOnFillingsSectionButton();
        mainPage.clickOnBunsSectionButton();
        mainPage.checkBunsIsDisplayed();
    }

    @Test

    @DisplayName("Переход на вкладку Соусы")
    public void clickOnSousesSectionButtonAutoScroll() {
        mainPage.clickOnFillingsSectionButton();
        mainPage.clickOnSousesSectionButton();
        mainPage.checkSaucesIsDisplayed();
    }

    @Test
    @DisplayName("Переход на вкладку Начинки")
    public void clickOnFillingsSectionButtonAutoScroll() {
        mainPage.clickOnSousesSectionButton();
        mainPage.clickOnFillingsSectionButton();
        mainPage.checkFillingsIsDisplayed();
    }
}
