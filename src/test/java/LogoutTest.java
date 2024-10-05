import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.example.client.UserClient;
import org.example.driver.Driver;
import org.example.generator.UserGenerator;
import org.example.model.User;
import org.example.model.UserCredentials;
import org.example.pageobject.Login;
import org.example.pageobject.Logout;
import org.example.pageobject.PageTransition;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogoutTest {
    private WebDriver webDriver;
    private final UserClient userClient = new UserClient();
    private String accessToken;
    private Login login;

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Before
    public void startUp() {
        webDriver = Driver.getInstance().getDriver();
        login = new Login(webDriver);
    }

    @Test
    @Description("выход по кнопке «Выйти» в личном кабинете")
    public void clickExitButton() {
        User user = UserGenerator.getRandom();
        ValidatableResponse createResponse = userClient.create(user);
        int statusCode = createResponse.extract().statusCode();
        boolean isSuccess = createResponse.extract().path("success");
        assertEquals("Неверный status code", 200, statusCode);
        assertTrue("Пользователь не создан", isSuccess);

        accessToken = createResponse.extract().path("accessToken");

        login.openPage();
        login.openLoginPageThroughLandingPage();
        login.loginUser(UserCredentials.from(user));
        boolean isLogged = login.isLogged();
        assertTrue("Пользователь не авторизован", isLogged);

        PageTransition pageTransition = new PageTransition(webDriver);
        pageTransition.openPersonalAccount();

        Logout logout = new Logout(webDriver);
        logout.clickLogoutButton();
        boolean isLoggedOut = logout.isLoggedOut();
        assertTrue("Пользователь не разлогинился", isLoggedOut);
    }

    @After
    public void teardown() {
        webDriver.quit();
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}
