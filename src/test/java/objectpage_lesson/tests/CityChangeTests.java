package objectpage_lesson.tests;

import objectpage_lesson.pages.MainPage;
import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.example.extensions.CommonPart.webDriver;

public class CityChangeTests extends  TestData {

    @Test
    @DisplayName("Изменение города")
    @ExtendWith(CommonPart.class)
    void cityChangeTest(){
        new MainPage(webDriver).changeCity(newCity);
    }

    @Test
    @DisplayName("Выбор города,в который не осуществляется доставка")
    @ExtendWith(CommonPart.class)
    void cityWithoutDeliveringTest(){
        villageLocator = new MainPage(webDriver).chooseVillage(village)
                .findVillageLocator();
        new MainPage(webDriver).moveOnSuitcase()
                .chooseSuitcase(suitcaseNumber)
                .clickBuyButton()
                .goToCart()
                .clickCheckoutButton()
                .controlNonDeliveringMessage(villageLocator);
    }
}

