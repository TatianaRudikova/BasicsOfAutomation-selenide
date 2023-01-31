package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


    @BeforeEach
    public void initEach() {
        open("http://localhost:9999");
    }

    @Test
    void shouldPositiveTest() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+71111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofMillis(15000))
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + meetingDate));

    }
}
