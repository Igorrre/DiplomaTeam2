package pages;

import dto.UserFields;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.ClickValue;
import wrappers.WriteText;

public class CreateUserPage extends BasePage {

    private final By BUTTON_PUSH_TO_API = By.xpath("//button[contains(text(), 'PUSH')]");
    private final By VALUE_STATUS_CODE = By.xpath("(//div[@role='group']//button[@type='button'])[2]");
    private final By NEW_USER_ID = By.xpath("(//div[@role='group']//button[@type='button'])[3]");

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CreateUserPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_PUSH_TO_API));
        return this;
    }

    @Step("Открытие страницы создания нового пользователя")
    public CreateUserPage open() {
        driver.get(CREATE_USER_URL);
        return this;
    }

    @Step("Внесение значений в поля формы")
    public CreateUserPage setValuesToCreateUser(UserFields userFields) {
        new WriteText(driver, "first_name_send").writeText(userFields.getFirstName());
        new WriteText(driver, "last_name_send").writeText(userFields.getLastName());
        new WriteText(driver, "age_send").writeText(String.valueOf(userFields.getAge()));
        new ClickValue(driver, "MALE").clickValue();
        new WriteText(driver, "money_send").writeText(String.valueOf(userFields.getMoney()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_PUSH_TO_API));
        return this;
    }

    @Step("Нажать кнопку PUSH TO API")
    public CreateUserPage clickButtonPushToApi() {
        driver.findElement(BUTTON_PUSH_TO_API).click();
        return this;
    }

    @Step("Получение текста со статус кодом")
    public String getTextValueStatusCode() {
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 201"));
        return driver.findElement(VALUE_STATUS_CODE).getText();
    }

    @Step("Получение значения User ID")
    public String getValueUserId() {
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 201"));
        String value = driver.findElement(NEW_USER_ID).getText();
        if (value.contains(":")) {
            String[] parts = value.split(":");
            return parts[1].trim();
        } else {
            throw new RuntimeException("Некорректный формат текста в элементе NEW_USER_ID: " + value);
        }
    }
}