package AuthorizationTest.Elements;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("logo", $x("//h4[@data-qa='my-courses__title']"));
    }};

    public static final SelenideElement logo = elements.get("logo");
}