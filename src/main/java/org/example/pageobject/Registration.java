package org.example.pageobject;

import io.qameta.allure.Step;
import org.example.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {
    private static final String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";
    // локатор поля Имя на странице Регистрации
    private static final By NAME_FIELD_REGISTER = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    // локатор поля Email на странице Регистрации
    private static final By EMAIL_FIELD_REGISTER = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    // локатор поля Пароль на странице Регистрации
    private static final By PASSWORD_FIELD_REGISTER = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    // локатор кнопки Зарегистрироваться на странице Регистрации
    private static final By REGISTER_BUTTON_REGISTER = By.xpath(".//button[text()='Зарегистрироваться']");
    // локатор ошибки Некоректный пароль
    public static By ERROR_INVALID_PASSWORD = By.xpath(".//p[text()='Некорректный пароль']");
    // локатор кнопки Войти на странице авторизации
    private static final By BUTTON_LOGIN = By.xpath(".//button[text()='Войти']");
    private final WebDriver driver;

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    @Step("открыть страницу регистрации")
    public void openRegistrationPage() {
        driver.get(REGISTER_URL);
    }

    @Step("заполнить поле Имя на странице регистрации")
    private void fillNameField(String name) {
        driver.findElement(NAME_FIELD_REGISTER).clear();
        driver.findElement(NAME_FIELD_REGISTER).sendKeys(name);
    }

    @Step("заполнить поле Email на странице регистрации")
    private void fillEmailField(String email) {
        driver.findElement(EMAIL_FIELD_REGISTER).clear();
        driver.findElement(EMAIL_FIELD_REGISTER).sendKeys(email);
    }

    @Step("заполнить поле Пароль на странице регистрации")
    private void fillPasswordField(String password) {
        driver.findElement(PASSWORD_FIELD_REGISTER).clear();
        driver.findElement(PASSWORD_FIELD_REGISTER).sendKeys(password);
    }

    @Step("нажать на кнопку Зарегистрироваться на странице регистрации")
    public void clickOnTheRegisterButton() {
        driver.findElement(REGISTER_BUTTON_REGISTER).click();
    }

    @Step("зарегистрироваться на странице регистрации")
    public void registerUser(User user) {
        fillNameField(user.getName());
        fillEmailField(user.getEmail());
        fillPasswordField(user.getPassword());
        clickOnTheRegisterButton();
    }

    @Step("проверить, что регистрация прошла")
    public boolean isRegistered() {
        return driver.findElement(BUTTON_LOGIN).isDisplayed();
    }

    @Step("проверить текст ошибки неправильного пароля")
    public boolean incorrectPassword() {
        return driver.findElement(ERROR_INVALID_PASSWORD).isDisplayed();
    }
}
