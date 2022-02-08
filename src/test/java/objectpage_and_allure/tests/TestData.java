package objectpage_and_allure.tests;

import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class TestData {
    String login = "npapublic@gmail.com";
    String password = "60740520";
    Random random = new Random();  // рандомный неправильный пароль
    String wrongPassword = String.valueOf(random.nextInt(999999) + 100000);
    String errorText = "Вход не выполнен";

    String newCity = "Липецк";
    String village = "Липов"; // 'Липовая Грива (Кумохинский с/с)'
    String villageLocator;
    int suitcaseNumber = 1;

    int productNumber1 = 1;
    int productNumber2 = 4;
    List<WebElement> products;
    String urlProduct1;
    String urlProduct2;
}
