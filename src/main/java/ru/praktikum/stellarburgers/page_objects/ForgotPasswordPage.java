package ru.praktikum.stellarburgers.page_objects;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class ForgotPasswordPage {
    WebDriver driver;
    @FindBy(xpath = "//input[@name='name']")
    private WebElement emailField;
    @FindBy(xpath = "//button[text()='Восстановить']")
    private WebElement recoverButton;
    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signInButton;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void clickRecoverButton() {
        recoverButton.click();
    }

    public void clickSignInButton() {
        signInButton.click();
    }
}
