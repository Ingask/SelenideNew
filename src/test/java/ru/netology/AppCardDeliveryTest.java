package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class AppCardDeliveryTest {
    @Test
    void getTrueInputCardDeliveryAppTest(){
        open("http://localhost:9999");
        $("[data-test-id = 'city'] input").setValue("Москва");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        SimpleDateFormat formDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String chooseDate = formDateFormat.format(calendar.getTime());
        SelenideElement dateElement =  $("[data-test-id=date] input[class=input__control]");
        dateElement.sendKeys("\b\b\b\b\b\b\b\b\b\b");
        dateElement.setValue(chooseDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+71234567891");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]")
                .$(withText("Встреча успешно забронирована на"))
                .waitUntil(visible, 15000);

    }
}

