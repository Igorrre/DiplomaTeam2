package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class SettleToHousePage extends BasePage {

    private final By BUTTON_PUSH_TO_API_IN_SETTLE = By.xpath("//button[contains(text(), 'PUSH')]");
    private final By VALUE_STATUS_CODE = By.xpath("//button[contains(@class, 'status')]");
    private final By FIELD_INPUT_ID_USER = By.xpath("//input[@id='id_send']");
    private final By FIELD_INPUT_ID_HOUSE = By.xpath("//input[@id='house_send']");
    private final By RADAR_SETTLE = By.xpath("//input[@value='settle']");
    private final By RADAR_EVICT = By.xpath("//input[@value='evict']");

    public SettleToHousePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SettleToHousePage isPageOpened() {
        log.info("Page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_PUSH_TO_API_IN_SETTLE));
        return this;
    }

    @Step("Ввод ID дома")
    public SettleToHousePage inputIdHouse(String houseId) {
        log.info("Input id house");
        driver.findElement(FIELD_INPUT_ID_HOUSE).sendKeys(houseId);
        return this;
    }

    @Step("Открытие страницы заселения")
    public SettleToHousePage open() {
        log.info("Open page settle");
        driver.get(SETTLE_TO_HOUSE_URL);
        return this;
    }

    @Step("Ввод ID пользователя")
    public SettleToHousePage inputIdUser(String userId) {
        driver.findElement(FIELD_INPUT_ID_USER).sendKeys(userId);
        return this;
    }

    @Step("Выбор действия 'Заселения'")
    public SettleToHousePage clickSettle() {
        driver.findElement(RADAR_SETTLE).click();
        return this;
    }

    @Step("Выбор действия 'Выселения'")
    public SettleToHousePage clickEvict() {
        driver.findElement(RADAR_EVICT).click();
        return this;
    }

    @Step("Клик по кнопке PUSH TO API")
    public SettleToHousePage clickButtonAction() {
        log.info("Click button PUSH TO API");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_PUSH_TO_API_IN_SETTLE));
        driver.findElement(BUTTON_PUSH_TO_API_IN_SETTLE).click();
        return this;
    }

    @Step("Получения текста статуса")
    public String checkMessage() {
        log.info("Get message Success");
        wait.until(ExpectedConditions.visibilityOfElementLocated(VALUE_STATUS_CODE));
        return driver.findElement(VALUE_STATUS_CODE).getText();
    }
}
