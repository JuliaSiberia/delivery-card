import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class deliveryCardTest {

    String generateData(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void enteringValidValues() {
        String data = generateData(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Иркутск");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(data);
        $("[data-test-id=name] input").setValue("Кульпина Юлия");
        $("[data-test-id=phone] input").setValue("+98765432109");
        $("[data-test-id=agreement").click();
        $$("[type=button").find(exactText("Забронировать")).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + data));


    }
}
