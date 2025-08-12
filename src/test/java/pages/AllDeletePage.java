package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AllDeletePage extends BasePage {

    private final By DELETE_FIELD = By.xpath("//div[@role='group']//button[contains(text(), '-- DELETE CAR --')] //following::input");
    private final By DELETE_BUTTON = By.xpath("//div[@role='group']//button[contains(text(), '-- DELETE CAR --')]");
    private final By STATUS_FIELD = By.xpath("//div[@role='group']//button[contains(text(), 'Status: 204')]");
    private final By BUTTON_USER_DELETE = By.xpath("//button[@value='user']");
    private final By INPUT_USER_FIELD = By.xpath("(//button[@value='user']/following::input)[1]");
    private final By MESSAGE_USER_TEXT = By.xpath("(//button[@value='user']/following::button)[2]");

    public AllDeletePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы для удаления")
    public AllDeletePage open() {
        driver.get(ALL_DELETE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_FIELD));
        return this;
    }

    @Override
    @Step("Проверка открытия страницы")
    public AllDeletePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_FIELD));
        return this;
    }

    @Step("Удаление тестового авто по Id")
    public void deleteTestCarId(String SaveTestId) {
        driver.findElement(DELETE_FIELD).sendKeys(SaveTestId);
        driver.findElement(DELETE_BUTTON).click();
    }

    @Step("Получение сообщения об удалении")
    public String getMessageDeleteCar() {
        log.info("Get message delete");
        return driver.findElement(STATUS_FIELD).getText();
    }

    @Step("Удаление пользователя по Id")
    public AllDeletePage deleteUserById(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(INPUT_USER_FIELD));
        driver.findElement(INPUT_USER_FIELD).sendKeys(id);
        driver.findElement(BUTTON_USER_DELETE).click();
        return this;
    }

    @Step("Получение сообщения об удалении пользователя")
    public String getMessageDeleteUser() {
        wait.until(ExpectedConditions.textToBe(MESSAGE_USER_TEXT, "Status: 204"));
        return driver.findElement(MESSAGE_USER_TEXT).getText();
    }
}