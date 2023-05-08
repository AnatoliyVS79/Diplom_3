package ru.praktikum.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.api.UsersSteps;
import ru.praktikum.stellarburgers.page_objects.AccountPage;
import ru.praktikum.stellarburgers.page_objects.LoginPage;
import ru.praktikum.stellarburgers.page_objects.MainPage;
import ru.praktikum.stellarburgers.pojos.SignInRequest;
import ru.praktikum.stellarburgers.pojos.SuccessSignInSignUpResponse;
import ru.praktikum.stellarburgers.pojos.UserRequest;
import ru.praktikum.stellarburgers.utils.ConfigFileReader;
import ru.praktikum.stellarburgers.utils.DriverInitializer;
import ru.praktikum.stellarburgers.utils.UsersUtils;

import java.time.Duration;

public class GoToAccountTest {
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;
    ConfigFileReader configFileReader = new ConfigFileReader();


    @Before
    public void init() {
        driver = DriverInitializer.createWebDriver();
        driver.get(configFileReader.getApplicationUrl());
        mainPage = new MainPage(driver);
        accountPage = new AccountPage(driver);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void shutdown() {
        driver.close();
    }

    @Test
    @DisplayName("Успешный переход по клику на Личный кабинет")
    public void goToAccountWithAccountButtonSuccess() {
        UserRequest user = UsersUtils.getUniqueUser();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String accessToken = UsersSteps.createUniqueUser(user)
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignInSignUpResponse.class)
                .getAccessToken();
        mainPage.clickAccountButton();
        loginPage.loginWithCredentials(new SignInRequest(user.getEmail(), user.getPassword()));
        mainPage.clickAccountButton();

        boolean displayed = accountPage.getProfileButton().isDisplayed();
        Assert.assertTrue("Личный кабинет не открыт", displayed);

        UsersSteps.deleteUser(accessToken);
    }

}
