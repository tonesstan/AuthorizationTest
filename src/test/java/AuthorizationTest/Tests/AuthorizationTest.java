package AuthorizationTest.Tests;

import AuthorizationTest.Pages.LoginPage;
import AuthorizationTest.Pages.MainPage;
import AuthorizationTest.Pages.RegisterPage;
import AuthorizationTest.Pages.TempMailPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.stream.Stream;

import static AuthorizationTest.Utils.generateRandomEmail;
import static AuthorizationTest.Utils.generateRandomPassword;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationTest {

    static String email = "1sg05c0flz@tidissajiiu.com";
    static String password = "12345678";

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://platform.productstar.ru";
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = new ChromeOptions().setPageLoadStrategy(PageLoadStrategy.EAGER).setPageLoadTimeout(Duration.ofSeconds(30)).addArguments("--headless", "--window-size=1920,1080", "--disable-notifications", "--disable-gpu", "--disable-dev-tools", "--fastSetValue");
        Configuration.timeout = 30000;
    }

    @BeforeEach
    public void openLoginPage() {
        open("/");
        LoginPage.waitForPageLoad();
    }

    @AfterEach
    public void cleanUp() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        executeJavaScript("window.sessionStorage.clear()");
    }

    @AfterAll
    public static void tearDown() {closeWebDriver();}

    @ParameterizedTest(name = "Login with {3} test")
    @Tag("login")
    @MethodSource("authorizationTestData")
    public void authorization(boolean valid, String email, String password, String testName) {
        String errorEvent = (valid) ? "Authorization failure" : "Successful authorization";
        LoginPage.enterEmail(email);
        LoginPage.enterPassword(password);
        LoginPage.clickLoginButton();
        MainPage.waitForPageLoad();
        assertEquals(MainPage.authorizationCheck(), valid, "\nError: " + errorEvent + " with " + testName + ".\nTest failed.\n");
    }

    @Test
    @Tag("register")
    @DisplayName("Registration with real new email")
    public void registrationWithRealEmail() {
        open("https://temp-mail.io");
        TempMailPage.waitForPageLoad();
        TempMailPage.waitForTempMailCreation();
        String email = TempMailPage.getEmail();
        System.out.println("Email: " + email);
        String password = generateRandomPassword();
        System.out.println("Password: " + password);
        open("/");
        LoginPage.waitForPageLoad();
        LoginPage.clickRegistrationButton();
        RegisterPage.waitForPageLoad();
        RegisterPage.enterEmail(email);
        RegisterPage.enterPassword(password);
        RegisterPage.enterConfirmPassword(password);
        RegisterPage.checkCheckBox();
        RegisterPage.clickRegistrationButton();
        MainPage.waitForPageLoad();
        assertTrue(MainPage.authorizationCheck(), "\nError: Registration failed.\nTest failed.\n");
        open("https://temp-mail.io");
        TempMailPage.waitForPageLoad();
        TempMailPage.waitForTempMailCreation();
        try {TempMailPage.waitForConfirmationMessage();} catch (Exception e) {fail("\nConfirmation message not received.\nTest failed.\n");}
        assertEquals(TempMailPage.getSendersEmail(), "platform@productstar.ru", "Error: trash message.\nTest failed.");
        TempMailPage.clickMessage();
        TempMailPage.waitForMessageLoad();
        TempMailPage.clickEmailConfirmButton();
        MainPage.waitForPageLoad();
        assertTrue(MainPage.authorizationCheck(), "\nError: email confirmation failed.\nTest failed.\n");
    }

    @Test
    @Tag("register")
    @DisplayName("Registration with fake email")
    public void registrationWithFakeEmail() {
        String email = generateRandomEmail();
        System.out.println("Email: " + email);
        String password = generateRandomPassword();
        System.out.println("Password: " + password);
        LoginPage.clickRegistrationButton();
        RegisterPage.waitForPageLoad();
        RegisterPage.enterEmail(email);
        RegisterPage.enterPassword(password);
        RegisterPage.enterConfirmPassword(password);
        RegisterPage.checkCheckBox();
        RegisterPage.clickRegistrationButton();
        MainPage.waitForPageLoad();
        assertFalse(MainPage.authorizationCheck(), "\nError: Successful registration with fake email.\nTest failed.\n");
    }

    public static Stream<Arguments> authorizationTestData() {
        return Stream.of(
                Arguments.of(true, email, password, "valid credentials"),
                Arguments.of(false, generateRandomEmail(), password, "invalid email"),
                Arguments.of(false, email, generateRandomPassword(), "invalid password")
        );
    }
}