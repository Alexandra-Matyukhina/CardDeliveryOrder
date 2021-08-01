package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {

    LocalDate date = LocalDate.now();
    LocalDate dateOfDelivery = LocalDate.now().plusDays(3);

    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        open("http://localhost:7777");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").setValue(dateOfDelivery.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id=name] input").setValue("Матюхина Александра");
        $("[data-test-id=phone] input").setValue("+79689111111");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000).shouldHave(exactText("Успешно! Встреча успешно забронирована на 04.08.2021"));
    }
}