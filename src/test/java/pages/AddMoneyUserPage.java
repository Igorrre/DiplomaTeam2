package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AddMoneyUserPage extends BasePage {

    private final By INPUT_USER_ID = By.xpath("//input[@id='id_send']");
    private final By INPUT_MONEY = By.xpath("//input[@id='money_send']");
    private final By BUTTON_PUSH_TO_API = By.xpath("//button[contains(text(), 'PUSH')]");
    private final By VALUE_STATUS_CODE = By.xpath("(//div[@role='group']//button[@type='button'])[2]");
    private final By NEW_USER_MONEY = By.xpath("(//div[@role='group']//button[@type='button'])[3]");

    public AddMoneyUserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddMoneyUserPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(INPUT_USER_ID));
        return this;
    }

    @Step("Открытие страницы добавление денег пользователю")
    public AddMoneyUserPage open() {
        driver.get(ADD_MONEY_TO_USER);
        return this;
    }

    @Step("Вписать User ID")
    public AddMoneyUserPage setUserId(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(INPUT_USER_ID));
        driver.findElement(INPUT_USER_ID).sendKeys(id);
        return this;
    }

    @Step("Вписать сумму в тугриках")
    public AddMoneyUserPage setMoney(String money) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(INPUT_MONEY));
        driver.findElement(INPUT_MONEY).sendKeys(money);
        return this;
    }

    @Step("Нажать кнопку PUSH TO API")
    public AddMoneyUserPage clickButtonPushToApi() {
        driver.findElement(BUTTON_PUSH_TO_API).click();
        return this;
    }

    @Step("Получение текста со статус кодом")
    public String getTextValueStatusCode() {
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 200"));
        return driver.findElement(VALUE_STATUS_CODE).getText();
    }

    @Step("Получение значения новой суммы у пользователя")
    public String getNewAmountOfMoney() {
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 200"));
        return driver.findElement(NEW_USER_MONEY).getText().trim();
    }
}