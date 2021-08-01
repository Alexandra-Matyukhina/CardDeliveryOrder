package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {
    String generateDate(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldRegister() {
        open("http://localhost:7777");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").setValue(generateDate(2));
        $("[data-test-id=name] input").setValue("Матюхина Александра");
        $("[data-test-id=phone] input").setValue("+79689111111");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно! Встреча успешно забронирована на " + generateDate(2)));
    }
}