package AuthorizationTest.Elements;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("email", $("#email"));
        put("password", $("#password"));
        put("loginButton", $x("//button[@data-qa='login-btn']"));
        put("registerButton", $("span.register-button"));
        put("forgotPassword", $x("//span[@data-qa='forgot-password-btn']"));
    }};

    public static final SelenideElement email = elements.get("email");
    public static final SelenideElement password = elements.get("password");
    public static final SelenideElement loginButton = elements.get("loginButton");
    public static final SelenideElement registerButton = elements.get("registerButton");
    public static final SelenideElement forgotPassword = elements.get("forgotPassword");

}
