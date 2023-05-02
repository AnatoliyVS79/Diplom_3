package ru.praktikum.stellarburgers.page_objects;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class AccountPage {
   WebDriver driver;

    @FindBy
    private WebElement editPasswordButton;
    @FindBy(xpath = "//button[text()='Сохранить']")
    private WebElement saveProfileDataButton;
    @FindBy(xpath = "//button[text()='Отмена']")
    private WebElement cancelProfileEditData;
    @FindBy(xpath = "//a[text()='Профиль']")
    private WebElement profileButton;
    @FindBy(xpath = "//a[text()='История заказов']")
    private WebElement goToOrderHistoryButton;
    @FindBy(xpath = "//button[text()='Выход']")
    private WebElement logoutButton;
    @FindBy(xpath = "//p[text()='Конструктор']")
    private WebElement goToConstructorButton;
    @FindBy(xpath = "//div[@class='AppHeader_header__logo__2D0X2']")
    private WebElement goToConstructorLogoButton;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickGoToConstructorButton() {
        goToConstructorButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }
}
