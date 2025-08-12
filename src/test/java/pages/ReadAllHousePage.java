package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReadAllHousePage extends BasePage{
    private final By BUTTON_RELOAD = By.xpath("//button[contains(., 'Reload')]");
    private final By ALL_FIND = By.tagName("td");

    public ReadAllHousePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_RELOAD));
        return this;
    }

    @Step("Открытие страницы просмотра всех созданных пользователей")
    public ReadAllHousePage open() {
        driver.get(ALL_READ_HOUSE_URL);
        return this;
    }

    @Step("Сохранение всех элементов td и поиск нужного значения")
    public boolean findIdHouseInTable(String targetValue) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((ALL_FIND)));
        String script = "return document.body.innerText.toLowerCase().includes('" + targetValue.toLowerCase() + "');";
        Boolean result = (Boolean) ((JavascriptExecutor) driver).executeScript(script);
        return result != null && result;
    }
}
