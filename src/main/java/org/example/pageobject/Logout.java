package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Logout {

    // локатор кнопки Войти на странице авторизации
    private static final By BUTTON_LOGIN = By.xpath(".//button[text()='Войти']");
    // локатор кнопки Выход на странице профиля
    private static final By BUTTON_LOGOUT = By.xpath(".//button[text()='Выход']");
    private final WebDriver driver;

    public Logout(WebDriver driver) {
        this.driver = driver;
    }

    @Step("нажать на кнопку Выход")
    public void clickLogoutButton() {
        driver.findElement(BUTTON_LOGOUT).click();
    }

    @Step("проверить, что пользователь разлогинился")
    public boolean isLoggedOut() {
        return driver.findElement(BUTTON_LOGIN).isDisplayed();
    }
}
