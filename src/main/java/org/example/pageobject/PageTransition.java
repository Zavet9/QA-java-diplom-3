package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageTransition {
    private static final String LANDING_URL = "https://stellarburgers.nomoreparties.site";
    // локатор кнопки Личный кабинет на главной странице
    private static final By ACCOUNT_BUTTON = By.xpath(".//p[text()='Личный Кабинет']");
    // локатор кнопки Войти на странице авторизации
    private static final By BUTTON_LOGIN = By.xpath(".//button[text()='Войти']");
    // локатор кнопки констурктор
    private static final By BUTTON_CONSTRUCTOR = By.xpath(".//p[text()='Конструктор']");
    // локатор кнопки логотипа
    private static final By LOGO_BUTTON = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]");

    // локатор Заголовка Соберите бургер
    private static final By MAKE_A_BURGER_HEADER = By.xpath(".//h1[text()='Соберите бургер']");
    private final WebDriver driver;

    public PageTransition(WebDriver driver) {
        this.driver = driver;
    }

    @Step("открыть главную страницу")
    public void openPage() {
        driver.get(LANDING_URL);
    }

    @Step("нажать на кнопку Личный кабинет")
    public void openPersonalAccount() {
        driver.findElement(ACCOUNT_BUTTON).click();
    }

    @Step("проверить, что Личный аккаунт открыт")
    public boolean isOpenPersonalAccount() {
        return driver.findElement(BUTTON_LOGIN).isDisplayed();
    }

    @Step("нажать кнопку конструктор из Личного Аккаунта")
    public void clickConstructor() {
        openPersonalAccount();
        driver.findElement(BUTTON_CONSTRUCTOR).click();
    }

    @Step("нажать на логотип")
    public void clickLogo() {
        openPersonalAccount();
        driver.findElement(LOGO_BUTTON).click();
    }

    @Step("проверить отображение страницы Конструктор")
    public boolean isConstructor() {
        return driver.findElement(MAKE_A_BURGER_HEADER).isDisplayed();
    }
}
