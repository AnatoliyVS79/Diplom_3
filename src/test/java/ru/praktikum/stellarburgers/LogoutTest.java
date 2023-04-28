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
public class LogoutTest {
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;
    Browser browserEnum;
    UserRequest testUser;
    String accessToken;
    SignInRequest signInRequest;

    public LogoutTest(Browser browserEnum) {
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
        testUser = UsersUtils.getUniqueUser();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        SuccessSignInSignUpResponse signUpResponse = UsersSteps.createUniqueUser(testUser)
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignInSignUpResponse.class);
        accessToken = signUpResponse.getAccessToken();
        signInRequest = new SignInRequest(testUser.getEmail(), testUser.getPassword());

        driver = DriverInitializer.getDriver(browserEnum);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        driver.get(new ConfigFileReader().getApplicationUrl() + "/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void shutdown() {
        driver.quit();
    }

    @Test
    @DisplayName("Выход по кнопке Выйти в личном кабинете")
    public void logoutWithLogoutButtonSuccess() {
        loginPage.loginWithCredentials(signInRequest);
        mainPage.clickAccountButton();
        accountPage.clickLogoutButton();
        mainPage.clickAccountButton();

        boolean displayed = loginPage.getSignInButton().isDisplayed();
        Assert.assertTrue("Выход из личного кабинета не выполнен", displayed);
    }
}
