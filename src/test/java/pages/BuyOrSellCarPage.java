package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.ClickValue;
import wrappers.WriteText;

@Log4j2
public class BuyOrSellCarPage extends BasePage {

    private final By BUTTON_PUSH_TO_API = By.xpath("//button[contains(text(), 'PUSH')]");
    private final By VALUE_STATUS_CODE = By.xpath("(//div[@role='group']//button[@type='button'])[2]");

    public BuyOrSellCarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BuyOrSellCarPage isPageOpened() {
        log.info("Page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_PUSH_TO_API));
        return this;
    }

    @Step("Открытие страницы просмотра пользователей с авто")
    public BuyOrSellCarPage open() {
        log.info("Open page buy or sell car");
        driver.get(BUY_OR_SELL_CAR_URL);
        return this;
    }

    @Step("Внесение значений в поля формы для покупки авто")
    public BuyOrSellCarPage setValuesToBuyCar(String valueId, String savedId) {
        log.info("id_send: {}", valueId);
        new WriteText(driver, "id_send").writeText(valueId);
        log.info("car_send: {}", savedId);
        new WriteText(driver, "car_send").writeText(savedId);
        log.info("Click button buyCar");
        new ClickValue(driver, "buyCar").clickValue();
        return this;
    }

    @Step("Нажать кнопку PUSH TO API")
    public BuyOrSellCarPage clickButtonPushToApi() {
        log.info("Click button PUSH TO API");
        driver.findElement(BUTTON_PUSH_TO_API).click();
        return this;
    }

    @Step("Получение текста со статус кодом")
    public String getTextValueStatusCode() {
        log.info("Get status code");
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 200"));
        return driver.findElement(VALUE_STATUS_CODE).getText();
    }
}
