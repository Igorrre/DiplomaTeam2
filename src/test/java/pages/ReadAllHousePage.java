package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
@Log4j2
public class ReadAllHousePage extends BasePage{
    private final By BUTTON_RELOAD = By.xpath("//button[contains(., 'Reload')]");
    private final By ALL_FIND = By.tagName("td");

    public ReadAllHousePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        log.info("Page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_RELOAD));
        return this;
    }

    @Step("Открытие страницы просмотра всех созданных пользователей")
    public ReadAllHousePage open() {
        log.info("Open page read house");
        driver.get(ALL_READ_HOUSE_URL);
        return this;
    }

    @Step("Сохранение всех элементов td и поиск нужного значения")
    public boolean findIdHouseInTable(String targetValue) {
        log.info("Find house ID");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((ALL_FIND)));
        String script = "return document.body.innerText.toLowerCase().includes('" + targetValue.toLowerCase() + "');";
        Boolean result = (Boolean) ((JavascriptExecutor) driver).executeScript(script);
        return result != null && result;
    }
}
