package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.ClickValue;
import wrappers.WriteText;

public class BuyOrSellCarPage extends BasePage {

    private final By BUTTON_PUSH_TO_API = By.xpath("//button[contains(text(), 'PUSH')]");
    private final By VALUE_STATUS_CODE = By.xpath("(//div[@role='group']//button[@type='button'])[2]");

    public BuyOrSellCarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BuyOrSellCarPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_PUSH_TO_API));
        return this;
    }

    @Step("Открытие страницы просмотра пользователей с авто")
    public BuyOrSellCarPage open() {
        driver.get(BUY_OR_SELL_CAR_URL);
        return this;
    }

    @Step("Внесение значений в поля формы для покупки авто")
    public BuyOrSellCarPage setValuesToBuyCar(String valueId, String savedId) {
        new WriteText(driver, "id_send").writeText(valueId);
        new WriteText(driver, "car_send").writeText(savedId);
        new ClickValue(driver, "buyCar").clickValue();
        return this;
    }

    @Step("Нажать кнопку PUSH TO API")
    public BuyOrSellCarPage clickButtonPushToApi() {
        driver.findElement(BUTTON_PUSH_TO_API).click();
        return this;
    }

    @Step("Получение текста со статус кодом")
    public String getTextValueStatusCode() {
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 200"));
        return driver.findElement(VALUE_STATUS_CODE).getText();
    }
}
