package ru.praktikum.stellarburgers.page_objects;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Data
public class MainPage {
    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Соберите бургер']")
    private WebElement burgerConstructorHeader;
    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement homeSignInButton;
    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private WebElement homeAccountButton;
    @FindBy(xpath = "//span[text()='Булки']/parent::div")
    private WebElement bunsSectionButton;
    @FindBy(xpath = "//span[text()='Соусы']/parent::div")
    private WebElement sousesSectionButton;
    @FindBy(xpath = "//span[text()='Начинки']/parent::div")
    private WebElement fillingsSectionButton;
    @FindBy(xpath = "//h2[text()='Соусы']")
    private WebElement sousesSectionHeader;
    @FindBy(xpath = "//h2[text()='Булки']")
    private WebElement bunsSectionHeader;
    @FindBy(xpath = "//h2[text()='Начинки']")
    private WebElement fillingsSectionHeader;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAccountButton() {
        homeAccountButton.click();
    }

    public void clickSignInButton() {
        homeSignInButton.click();
    }

    public void clickOnBunsSectionButton() {
        bunsSectionButton.click();
    }

    public void clickOnSousesSectionButton() {
        sousesSectionButton.click();
    }

    public void clickOnFillingsSectionButton() {
        fillingsSectionButton.click();
    }

    public boolean isSectionButtonSelected(WebElement sectionButton) {
        return sectionButton.getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }
    public void checkSaucesIsDisplayed() {
        assertThat("Вкладка Соусы отображается", true,
                equalTo(driver.findElement(By.xpath("//div[contains(span/text(),'Соусы') and contains(@class,'current')]")).isDisplayed()));
    }

    public void checkFillingsIsDisplayed() {
        assertThat("Вкладка Начинки отображается", true,
                equalTo(driver.findElement(By.xpath("//div[contains(span/text(),'Начинки') and contains(@class,'current')]")).isDisplayed()));
    }

    public void checkBunsIsDisplayed() {
        assertThat("Вкладка Булки отображается", true,
                equalTo(driver.findElement(By.xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]")).isDisplayed()));
    }
}
