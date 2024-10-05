package org.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SectionConstructor {
    private static final String CONSTRUCTOR_PAGE = "https://stellarburgers.nomoreparties.site/";
    // локатор активного таба Булки
    private static final By BUNS_TAB_ACTIVE = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/span[text()='Булки']");
    // локатор таба Соусы
    private static final By SAUCES_TAB = By.xpath(".//span[text()='Соусы']");
    // локатор активного таба Соусы
    private static final By SAUCES_TAB_ACTIVE = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/span[text()='Соусы']");
    // локатор таба Начинки
    private static final By FILLINGS_TAB = By.xpath(".//span[text()='Начинки']");
    // локатор активного таба Начинки
    private static final By FILLINGS_TAB_ACTIVE = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/span[text()='Начинки']");

    private final WebDriver driver;

    public SectionConstructor(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(CONSTRUCTOR_PAGE);
    }

    public boolean isBunsTabOpened() {
        return driver.findElement(BUNS_TAB_ACTIVE) != null;
    }

    public void openSaucesTab() {
        driver.findElement(SAUCES_TAB).click();
    }

    public boolean isSaucesTabOpened() {
        return driver.findElement(SAUCES_TAB_ACTIVE) != null;
    }

    public void openFillingsTab() {
        driver.findElement(FILLINGS_TAB).click();
    }

    public boolean isFillingsTabOpened() {
        return driver.findElement(FILLINGS_TAB_ACTIVE) != null;
    }
}
