package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {
    @Test
    void getTrueInputCardDeliveryAppTest(){
        open("http://localhost:9999");
        $("[data-test-id = 'city'] input").setValue("Москва");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        SimpleDateFormat formDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String chooseDate = formDateFormat.format(calendar.getTime());
        SelenideElement dateElement =  $("[data-test-id=date] input[class=input__control]");
        dateElement.sendKeys("\b\b\b\b\b\b\b\b\b\b");
        dateElement.setValue(chooseDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]")
                .$(withText("Успешно"))
                .waitUntil(visible, 15000);

    }
}

