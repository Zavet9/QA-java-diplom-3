import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.driver.Driver;
import org.example.pageobject.SectionConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class SectionConstructorTest {
    private WebDriver webDriver;
    private SectionConstructor sectionConstructor;

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Before
    public void startUp() {
        webDriver = Driver.getInstance().getDriver();
        sectionConstructor = new SectionConstructor(webDriver);
        sectionConstructor.openPage();
    }

    @Test
    @Description("открыта вкладка Булки")
    public void bunsTabOpened() {
        boolean isOpened = sectionConstructor.isBunsTabOpened();
        assertTrue("Таб Булки не открыт", isOpened);
    }

    @Test
    @Description("открыта вкладка Соусы")
    public void saucesTabOpened() {
        sectionConstructor.openSaucesTab();
        boolean isOpened = sectionConstructor.isSaucesTabOpened();
        assertTrue("Таб Соусы не открыт", isOpened);
    }

    @Test
    @Description("открыта вкладка Начинки")
    public void fillingsTabOpened() {
        sectionConstructor.openFillingsTab();
        boolean isOpened = sectionConstructor.isFillingsTabOpened();
        assertTrue("Таб Начинки не открыт", isOpened);
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
