package pages;

import dto.ui.user.Users;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.ClickValue;
import wrappers.WriteText;

@Log4j2
public class CreateUserPage extends BasePage {

    private final By BUTTON_PUSH_TO_API = By.xpath("//button[contains(text(), 'PUSH')]");
    private final By VALUE_STATUS_CODE = By.xpath("(//div[@role='group']//button[@type='button'])[2]");
    private final By NEW_USER_ID = By.xpath("(//div[@role='group']//button[@type='button'])[3]");
    private final By INPUT_USER_MONEY = By.xpath("//input[@id='money_send']");

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CreateUserPage isPageOpened() {
        log.info("Page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_PUSH_TO_API));
        return this;
    }

    @Step("Открытие страницы создания нового пользователя")
    public CreateUserPage open() {
        log.info("Page Create User is open");
        driver.get(CREATE_USER_URL);
        return this;
    }

    @Step("Внесение значений в поля формы")
    public CreateUserPage setValuesToCreateUser(Users user) {
        log.info("Set Values To Create User: {}", user);
        new WriteText(driver, "first_name_send").writeText(user.getFirstName());
        new WriteText(driver, "last_name_send").writeText(user.getLastName());
        new WriteText(driver, "age_send").writeText(String.valueOf(user.getAge()));
        new ClickValue(driver, "MALE").clickValue();
        new WriteText(driver, "money_send").writeText(String.valueOf(user.getMoney()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_PUSH_TO_API));
        return this;
    }

    @Step("Нажать кнопку PUSH TO API")
    public CreateUserPage clickButtonPushToApi() {
        log.info("Click Button Push To Api");
        driver.findElement(BUTTON_PUSH_TO_API).click();
        return this;
    }

    @Step("Получение текста со статус кодом")
    public String getTextValueStatusCode() {
        log.info("Get Text Value Status Code");
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 201"));
        return driver.findElement(VALUE_STATUS_CODE).getText();
    }

    @Step("Получение значения User ID")
    public String getValueUserId() {
        log.info("Get Value User Id");
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 201"));
        String value = driver.findElement(NEW_USER_ID).getText();
        if (value.contains(":")) {
            String[] parts = value.split(":");
            return parts[1].trim();
        } else {
            throw new RuntimeException("Некорректный формат текста в элементе NEW_USER_ID: " + value);
        }
    }

    @Step("Получение денеХ у пользователя")
    public String getMoneyValue() {
        log.info("Get Money Value");
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 201"));
        return driver.findElement(INPUT_USER_MONEY).getAttribute("valueAsNumber").trim();
    }
}