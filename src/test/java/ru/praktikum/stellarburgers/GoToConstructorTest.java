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
import ru.praktikum.stellarburgers.api.UsersSteps;
import ru.praktikum.stellarburgers.constants.Browser;
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

@RunWith(Parameterized.class)
public class GoToConstructorTest {

    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;
    Browser browserEnum;

    public GoToConstructorTest(Browser browserEnum) {
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
        driver = DriverInitializer.getDriver(browserEnum);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        driver.get(new ConfigFileReader().getApplicationUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void shutdown() {
        driver.quit();
    }

    @Test
    @DisplayName("Успешный переход в конструктор из аккаунта")
    public void goToConstructorFromAccountSuccess() {
        UserRequest user = UsersUtils.getUniqueUser();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        SuccessSignInSignUpResponse signUpResponse = UsersSteps.createUniqueUser(user)
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignInSignUpResponse.class);

        mainPage.clickAccountButton();
        loginPage.loginWithCredentials(new SignInRequest(user.getEmail(), user.getPassword()));
        mainPage.clickAccountButton();
        accountPage.clickGoToConstructorButton();

        boolean displayed = mainPage.getBurgerConstructorHeader().isDisplayed();
        Assert.assertTrue("Конструктор не открыт",displayed);

        UsersSteps.deleteUser(signUpResponse.getAccessToken());
    }

}
