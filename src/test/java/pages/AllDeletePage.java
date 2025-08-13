package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AllDeletePage extends BasePage {

    private final By DELETE_FIELD_CAR = By.xpath("//div[@role='group']//button[@value='car']//following::input");
    private final By DELETE_BUTTON_CAR = By.xpath("//div[@role='group']//button[@value='car']");
    private final By STATUS_FIELD_CAR = By.xpath("//div[@role='group']//button[contains(text(), 'Status: 204')]");

    private final By BUTTON_USER_DELETE = By.xpath("//button[@value='user']");
    private final By INPUT_USER_FIELD = By.xpath("(//button[@value='user']/following::input)[1]");
    private final By MESSAGE_USER_TEXT = By.xpath("(//button[@value='user']/following::button)[2]");

    private final By DELETE_FIELD_HOUSE = By.xpath("//div[@role='group']//button[@value='house']//following::input");
    private final By DELETE_BUTTON_HOUSE = By.xpath("//div[@role='group']//button[@value='house']");
    private final By STATUS_FIELD_HOUSE = By.xpath("//div[@role='group']//button[contains(text(), 'Status: 204')]");

    public AllDeletePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы для удаления")
    public AllDeletePage open() {
        driver.get(ALL_DELETE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_FIELD_CAR));
        return this;
    }

    @Override
    @Step("Проверка открытия страницы")
    public AllDeletePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_FIELD_CAR));
        return this;
    }

    @Step("Удаление тестового авто по Id")
    public void deleteTestCarId(String SaveTestId) {
        driver.findElement(DELETE_FIELD_CAR).sendKeys(SaveTestId);
        driver.findElement(DELETE_BUTTON_CAR).click();
    }

    @Step("Получение сообщения об удалении авто")
    public String getMessageDeleteCar() {
        log.info("Get message delete");
        wait.until(ExpectedConditions.visibilityOfElementLocated(STATUS_FIELD_CAR));
        return driver.findElement(STATUS_FIELD_CAR).getText();
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

    @Step("Удаление тестового дома по Id")
    public void deleteTestHouseId(String SaveTestId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_FIELD_HOUSE));
        driver.findElement(DELETE_FIELD_HOUSE).sendKeys(SaveTestId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_BUTTON_HOUSE));
        driver.findElement(DELETE_BUTTON_HOUSE).click();
    }

    @Step("Получение сообщения об удалении дома")
    public String getMessageDeleteHouse() {
        log.info("Get message delete house");
        wait.until(ExpectedConditions.visibilityOfElementLocated(STATUS_FIELD_HOUSE));
        return driver.findElement(STATUS_FIELD_HOUSE).getText();
    }
}