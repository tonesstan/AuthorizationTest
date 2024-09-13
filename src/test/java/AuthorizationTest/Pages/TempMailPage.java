package AuthorizationTest.Pages;

import java.time.Duration;
import java.util.Objects;

import static AuthorizationTest.Elements.TempMailPage.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class TempMailPage{

    public static void waitForPageLoad() {logo.shouldBe(visible);}

    public static void waitForTempMailCreation() {email.shouldBe(visible).shouldHave(attribute("title")).shouldNotBe(attribute("title", ""));}

    public static String getEmail() {return email.getAttribute("title");}

    public static void clickRefreshButton() {refreshButton.shouldBe(exist).click();}

    public static void waitForConfirmationMessage() {messageSubject.shouldBe(exist, Duration.ofMinutes(1));}

    public static String getSendersEmail() {return Objects.requireNonNull(messageFrom.getAttribute("title")).replaceAll(".*<([^>]+)>.*", "$1");}

    public static String getSubject() {return messageSubject.getText();}

    public static void clickMessage() {messageSubject.click();}

    public static void waitForMessageLoad() {messageCloseButton.shouldBe(exist);}

    public static void clickEmailConfirmButton() {
        String href = Objects.requireNonNull(emailConfirmButton.getAttribute("href"));
        System.out.println("Confirmation link: " + href);
        open(href);
    }

}