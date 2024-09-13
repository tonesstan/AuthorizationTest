package AuthorizationTest.Elements;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {

    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("email", $("#email"));
        put("password", $("#password"));
        put("confirmPassword", $("#password_confirmation"));
        put("checkBox", $("input#terms"));
        put("loginButton", $("span.register-button"));
        put("registerButton", $x("//button[@data-qa='register-btn']"));
    }};

    public static final SelenideElement email = elements.get("email");
    public static final SelenideElement password = elements.get("password");
    public static final SelenideElement confirmPassword = elements.get("confirmPassword");
    public static final SelenideElement checkBox = elements.get("checkBox");
    public static final SelenideElement loginButton = elements.get("loginButton");
    public static final SelenideElement registerButton = elements.get("registerButton");
}
