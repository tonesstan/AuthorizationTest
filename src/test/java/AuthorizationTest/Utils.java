package AuthorizationTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.fail;


public class Utils {

    //генерируем случайный ненастоящий типичный адрес электронной почты
    public static String generateRandomEmail() {
        return randomWord(randomNumber(5, 15)) + randomNumber(10, 999) + "@" + randomWord(randomNumber(4, 8)) + ".com";
    }

    //генерируем логин в виде случайной комбинации букв и цифр
    public static String generateRandomLogin() {
        return generateRandomString(6, 12, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-");
    }

    //генерируем случайный пароль, соответствующий допустимым значениям и требованиям
    public static String generateRandomPassword() {
        String password;
        do {
            password = generateRandomString(8, 20, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%^&*(){}[]<>!?:;.,-_+/=~|");
        } while (!(password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[@#$%^&*(){}\\[\\]<>!?:;.,\\-_+/=~|].*") &&
                password.matches(".*[@#$%^&*].*")));
        return password;
    }

    //генерируем случайное целое положительное число в заданном диапазоне
    private static int randomNumber(int min, int max) {
        return new SecureRandom().nextInt(max - min + 1) + min;
    }

    //получаем случайное английское слово заданной длины с random-word-api.herokuapp.com
    private static String randomWord(int length) {
        String url = "https://random-word-api.herokuapp.com/word?length=" + length;
        return getResponseAsString(url).substring(2, length + 2);
    }

    //получаем по заданной ссылке API-ответ в виде одной единственной строки из JSON-ответа
    private static String getResponseAsString(String url) {
        try {
            URL urlObj = new URI(url).toURL();
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception e) {fail("Error: " + e.getMessage());}
        return "";
    }

    //генерируем случайную строку с заданными допустимыми длиной и символами
    private static String generateRandomString(int minLength, int maxLength, String allowedChars) {
        SecureRandom random = new SecureRandom();
        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            sb.append(allowedChars.charAt(index));
        }
        return sb.toString();
    }

}