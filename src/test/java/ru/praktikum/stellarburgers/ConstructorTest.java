package ru.praktikum.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.constants.Browser;
import ru.praktikum.stellarburgers.page_objects.MainPage;
import ru.praktikum.stellarburgers.utils.ConfigFileReader;
import ru.praktikum.stellarburgers.utils.DriverInitializer;

import java.time.Duration;

@RunWith(Parameterized.class)
public class ConstructorTest {
    WebDriver driver;
    MainPage mainPage;
    Browser browserEnum;
    ConfigFileReader configFileReader = new ConfigFileReader();

    public ConstructorTest(Browser browserEnum) {
        this.browserEnum = browserEnum;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {Browser.CHROME},
                {Browser.YANDEX}
        };
    }

    @Before
    public void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        this.driver = DriverInitializer.getDriver(browserEnum);

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
        boolean isSelected = mainPage.isSectionButtonSelected(mainPage.getBunsSectionButton());
        Assert.assertTrue("Переход к разделу Булки не выполнен", isSelected);
    }

    @Test
    @DisplayName("Переход на вкладку Соусы")
    public void clickOnSousesSectionButtonAutoScroll() {
        mainPage.clickOnSousesSectionButton();
        boolean isSelected = mainPage.isSectionButtonSelected(mainPage.getSousesSectionButton());
        Assert.assertTrue("Переход к разделу Соусы не выполнен", isSelected);
    }

    @Test
    @DisplayName("Переход на вкладку Начинки")
    public void clickOnFillingsSectionButtonAutoScroll() {
        mainPage.clickOnFillingsSectionButton();
        boolean isSelected = mainPage.isSectionButtonSelected(mainPage.getFillingsSectionButton());
        Assert.assertTrue("Переход к разделу Начинки не выполнен", isSelected);
    }
}
