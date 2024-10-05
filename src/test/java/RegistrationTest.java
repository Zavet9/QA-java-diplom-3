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
import org.example.pageobject.Registration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class RegistrationTest {
    private WebDriver webDriver;
    private final UserClient userClient = new UserClient();
    private String accessToken;

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Before
    public void startUp() {
        webDriver = Driver.getInstance().getDriver();
    }

    @Test
    @Description("успешно зарегистрировать пользователя")
    public void successfulRegistration() {
        Registration registration = new Registration(webDriver);
        User user = UserGenerator.getRandom();
        registration.openRegistrationPage();
        registration.registerUser(user);
        boolean isRegistered = registration.isRegistered();
        assertTrue("Пользователь не зарегистрирован", isRegistered);

        ValidatableResponse loginResponse = userClient.login(UserCredentials.from(user));
        accessToken = loginResponse.extract().path("accessToken");
    }

    @Test
    @Description("зарегистрировать пользователя, когда пароль некоррекный")
    public void registrationEndedWithAnError() {
        Registration registration = new Registration(webDriver);
        User user = UserGenerator.getRandom();
        user.setPassword("1111");
        registration.openRegistrationPage();
        registration.registerUser(user);
        boolean isIncorrectPassword = registration.incorrectPassword();
        assertTrue("Пользователь зарегистрирован", isIncorrectPassword);
    }

    @After
    public void teardown() {
        webDriver.quit();
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}
