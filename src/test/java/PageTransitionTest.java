import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.driver.Driver;
import org.example.pageobject.PageTransition;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class PageTransitionTest {
    private WebDriver webDriver;

    private PageTransition pageTransition;

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Before
    public void startUp() {
        webDriver = Driver.getInstance().getDriver();
    }

    @Test
    @Description("проверить переход по клику на Личный кабинет")
    public void openPersonalAccount() {
        pageTransition = new PageTransition(webDriver);
        pageTransition.openPage();
        pageTransition.openPersonalAccount();
        boolean isOpenPersonalAccount = pageTransition.isOpenPersonalAccount();
        assertTrue("Страница Личного кабинета не открылась", isOpenPersonalAccount);
    }

    @Test
    @Description("переход из личного кабинета в конструктор (клик на конструктор)")
    public void openConstructorFromPersonalAccount() {
        openPersonalAccount();
        pageTransition.clickConstructor();
        boolean isConstructor = pageTransition.isConstructor();
        assertTrue("Страница Конструктора не открылась", isConstructor);
    }

    @Test
    @Description("переход из личного кабинета в конструктор (клик на логотип)")
    public void openConstructorFromPersonalAccountByLogoClick() {
        openPersonalAccount();
        pageTransition.clickLogo();
        boolean isConstructor = pageTransition.isConstructor();
        assertTrue("Страница Конструктора не открылась", isConstructor);
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
