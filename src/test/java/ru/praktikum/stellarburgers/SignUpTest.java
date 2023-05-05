package ru.praktikum.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.api.UsersSteps;
import ru.praktikum.stellarburgers.page_objects.LoginPage;
import ru.praktikum.stellarburgers.page_objects.MainPage;
import ru.praktikum.stellarburgers.page_objects.SignUpPage;
import ru.praktikum.stellarburgers.pojos.SignInRequest;
import ru.praktikum.stellarburgers.pojos.SuccessSignInSignUpResponse;
import ru.praktikum.stellarburgers.utils.ConfigFileReader;
import ru.praktikum.stellarburgers.utils.DriverInitializer;

import java.time.Duration;

public class SignUpTest {
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    SignUpPage signUpPage;


    @Before
    public void init() {
        driver = DriverInitializer.createWebDriver();
        driver.get(new ConfigFileReader().getApplicationUrl());
        this.mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @After
    public void shutdown() {
        driver.close();
    }

    @DisplayName("Успешная регистрация с корректными данными")
    @Test
    public void signUpWithValidDataSuccess() {
        String name = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphanumeric(10, 12) + "@gmail.com";
        String password = RandomStringUtils.randomAlphanumeric(6, 12);

        mainPage.clickSignInButton();
        loginPage = new LoginPage(driver);
        loginPage.clickSignUpButton();
        signUpPage = new SignUpPage(driver);
        signUpPage.enterName(name);
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();

        boolean displayed = loginPage.getSignInButton().isDisplayed();
        Assert.assertTrue("Регистрация не выполнена", displayed);

        Response response = UsersSteps.signInWithSignInRequest(new SignInRequest(email, password));

        String accessToken = response
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignInSignUpResponse.class).getAccessToken();

        UsersSteps.deleteUser(accessToken);
    }

    @DisplayName("Ошибка при регистрации - пароль меньше 6 символов")
    @Test
    public void signUpWithInvalidPasswordFails() {
        String name = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphanumeric(10, 12) + "@gmail.com";
        String password = RandomStringUtils.randomAlphanumeric(5);

        mainPage.clickSignInButton();
        loginPage = new LoginPage(driver);
        loginPage.clickSignUpButton();
        signUpPage = new SignUpPage(driver);
        signUpPage.enterName(name);
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();

        boolean displayed = signUpPage.getPasswordErrorMessage().isDisplayed();

        if (!displayed) {
            Response response = UsersSteps.signInWithSignInRequest(new SignInRequest(email, password));

            if (response.getStatusCode() == 200) {
                String accessToken = response.then().extract().as(SuccessSignInSignUpResponse.class).getAccessToken();
                UsersSteps.deleteUser(accessToken);
            }
        }
        Assert.assertTrue("Не выведена ошибка о некорректном пароле", displayed);
    }
}
