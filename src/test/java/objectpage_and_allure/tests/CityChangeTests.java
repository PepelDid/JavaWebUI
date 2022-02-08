package objectpage_and_allure.tests;


import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import objectpage_and_allure.pages.MainPage;
import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.example.extensions.CommonPart.webDriver;

@DisplayName("Выбор города")
public class CityChangeTests extends  TestData {

    @Test
    @DisplayName("Изменение города")
    @ExtendWith(CommonPart.class)
    @Severity(SeverityLevel.CRITICAL)
    void cityChangeTest(){
        Allure.parameter("Название выбранного города", newCity);

        new MainPage(webDriver).changeCity(newCity)
                .controlNewCity();
    }

    @Test
    @DisplayName("Выбор города,в который не осуществляется доставка")
    @ExtendWith(CommonPart.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("В этом тесте осуществляется проверка появления сообщения о невозможности доставки в выбранный город")
    void cityWithoutDeliveringTest(){

        villageLocator = new MainPage(webDriver).chooseVillage(village)
                .findVillageLocator();
        new MainPage(webDriver).moveOnSuitcase()
                .chooseSuitcase(suitcaseNumber)
                .clickBuyButton()
                .goToCart()
                .clickCheckoutButton()
                .controlNonDeliveringMessage(villageLocator);

        Allure.parameter("Название города, куда не осуществляется доставка", villageLocator);
    }
}

