package org.example.pageobject;

import io.qameta.allure.Step;
import org.example.model.UserCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    private static final String LANDING_URL = "https://stellarburgers.nomoreparties.site";
    // локатор кнопки Войти на странице авторизации
    private static final By BUTTON_LOGIN = By.xpath(".//button[text()='Войти']");

    // локатор поля Email на странице авторизации
    private static final By EMAIL_FIELD_LOGIN = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    // локатор поля Пароль на странице авторизации
    private static final By PASSWORD_FIELD_LOGIN = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    // локатор кнопки Войти в Аккаунт на главной странице
    private static final By LANDING_LOGIN_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    // локатор кнопки Личный кабинет на главной странице
    private static final By ACCOUNT_BUTTON = By.xpath(".//p[text()='Личный Кабинет']");
    // локатор кнопки Зарегистрироваться в личном кабинете
    private static final By ACCOUNT_REGISTER_BUTTON = By.xpath(".//a[text()='Зарегистрироваться']");
    // локатор кнопки Войти на странице регистрации и Восстановления пароля
    private static final By LINK_LOGIN_BUTTON = By.xpath(".//a[text()='Войти']");
    // локатор кнопки Восстановить пароль из личного кабинета
    private static final By ACCOUNT_RESET_PASSWORD_BUTTON = By.xpath(".//a[text()='Восстановить пароль']");
    // локатор кнопки Оформить заказ
    private static final By ORDER_BUTTON = By.xpath(".//button[text()='Оформить заказ']");

    private final WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    @Step("открыть главную страницу")
    public void openPage() {
        driver.get(LANDING_URL);
    }

    @Step("заполнить поле Email на странице авторизации")
    private void fillEmailField(String email) {
        driver.findElement(EMAIL_FIELD_LOGIN).clear();
        driver.findElement(EMAIL_FIELD_LOGIN).sendKeys(email);

    }

    @Step("заполнить поле Пароль на странице авторизации")
    private void fillPasswordField(String password) {
        driver.findElement(PASSWORD_FIELD_LOGIN).clear();
        driver.findElement(PASSWORD_FIELD_LOGIN).sendKeys(password);
    }

    @Step("залогинить пользователя")
    public void loginUser(UserCredentials credentials) {
        fillEmailField(credentials.getEmail());
        fillPasswordField(credentials.getPassword());
        clickOnTheLoginButton();
    }

    @Step("нажать на кнопку Войти на странице входа")
    private void clickOnTheLoginButton() {
        driver.findElement(BUTTON_LOGIN).click();
    }

    @Step("перейти на страницу входа через главную страницу")
    public void openLoginPageThroughLandingPage() {
        driver.findElement(LANDING_LOGIN_BUTTON).click();
    }

    @Step("перейти на страницу входа через личный кабинет")
    public void openLoginPageThroughAccountPage() {
        driver.findElement(ACCOUNT_BUTTON).click();
    }

    @Step("перейти на страницу входа через страницу восстановления пароля")
    public void openLoginPageThroughResetPasswordPage() {
        driver.findElement(ACCOUNT_BUTTON).click();
        driver.findElement(ACCOUNT_RESET_PASSWORD_BUTTON).click();
        driver.findElement(LINK_LOGIN_BUTTON).click();
    }

    @Step("перейти на страницу входа через страницу регистрации")
    public void openLoginPageThroughRegistrationPage() {
        driver.findElement(ACCOUNT_BUTTON).click();
        driver.findElement(ACCOUNT_REGISTER_BUTTON).click();
        driver.findElement(LINK_LOGIN_BUTTON).click();
    }

    public boolean isLogged() {
        return driver.findElement(ORDER_BUTTON).isDisplayed();
    }
}
