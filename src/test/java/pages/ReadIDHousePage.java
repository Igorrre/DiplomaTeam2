package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ReadIDHousePage extends BasePage {

    private final By FIELD_INPUT = By.cssSelector("#house_input");
    private final By BUTTON_READ_HOUSE = By.xpath("//button[contains(@class, 'tableButton')]");
    private final By FIND_TD_IN_TABLE_HOUSE = By.tagName("td");

    public ReadIDHousePage(WebDriver driver) {
        super(driver);
    }

    public ReadIDHousePage isPageOpened() {
        log.info("Page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_READ_HOUSE));
        return this;
    }

    @Step("Ввод ID дома")
    public ReadIDHousePage inputIdHouse(String valueId) {
        log.info("Add ID house");
        driver.findElement(FIELD_INPUT).sendKeys(valueId);
        return this;
    }

    @Step("Открытие страницы Поиска дома по идентификатору")
    public ReadIDHousePage open() {
        log.info("Open page house ID");
        driver.get(READ_ID_HOUSE_URL);
        return this;
    }

    @Step("Нажать кнопку поиска")
    public ReadIDHousePage clickButtonReadHouse() {
        log.info("Click button search");
        driver.findElement(BUTTON_READ_HOUSE).click();
        return this;
    }

    @Step("Поиск ID пользователя на странице")
    public boolean findIdInTableHouse(String targetValue) {
        log.info("Set ID: {}", targetValue);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((FIND_TD_IN_TABLE_HOUSE)));
        String script = "return document.body.innerText.toLowerCase().includes('" + targetValue.toLowerCase() + "');";
        Boolean result = (Boolean) ((JavascriptExecutor) driver).executeScript(script);
        return result != null && result;
    }
}
