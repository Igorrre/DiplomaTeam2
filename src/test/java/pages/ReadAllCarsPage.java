package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReadAllCarsPage extends BasePage {
    private final By BUTTON_SORT = By.xpath("//button[contains(., 'Sort by:')]");
    private final By ALL_FIND = By.tagName("td");

    public ReadAllCarsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_SORT));
        return this;
    }

    @Step("Открытие страницы просмотра всех созданных пользователей")
    public ReadAllCarsPage open() {
        driver.get(ALL_READ_CAR_URL);
        return this;
    }

    @Step("Сохранение всех элементов td и поиск нужного значения")
    public boolean findIdCarInTable(String targetValue) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((ALL_FIND)));
        String script = "return document.body.innerText.toLowerCase().includes('" + targetValue.toLowerCase() + "');";
        Boolean result = (Boolean) ((JavascriptExecutor) driver).executeScript(script);
        return result != null && result;
    }
}