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
}