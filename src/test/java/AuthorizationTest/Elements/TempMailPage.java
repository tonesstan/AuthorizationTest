package AuthorizationTest.Elements;

import java.util.HashMap;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TempMailPage {

    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("logo", $(".logo__img"));
        put("email", $x("//input[@data-qa='current-email']"));
        put("refreshButton", $("//button[@data-qa='refresh-button']"));
        put("messageFrom", $(".message__from"));
        put("messageSubject", $(".message__subject"));
        put("messageCloseButton", $("button.btn.message-close"));
        put("emailConfirmButton", $x("//a[@data-qa='mail-action-link']"));
    }};

    public static final SelenideElement logo = elements.get("logo");
    public static final SelenideElement email = elements.get("email");
    public static final SelenideElement refreshButton = elements.get("refreshButton");
    public static final SelenideElement messageFrom = elements.get("messageFrom");
    public static final SelenideElement messageSubject = elements.get("messageSubject");
    public static final SelenideElement messageCloseButton = elements.get("messageCloseButton");
    public static final SelenideElement emailConfirmButton = elements.get("emailConfirmButton");

}