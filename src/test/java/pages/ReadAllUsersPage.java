package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReadAllUsersPage extends BasePage {

    private final By FIRST_ID_IN_TABLE = By.xpath("(//*[contains(text(), ' ID:')]/following::td)[1]");
    private final By ALL_FIND_TD_IN_TABLE = By.tagName("td");

    public ReadAllUsersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_ID_IN_TABLE));
        return this;
    }

    @Step("Открытие страницы просмотра всех созданных пользователей")
    public ReadAllUsersPage open() {
        driver.get(READ_ALL_USERS_URL);
        return this;
    }

    @Step("Поиск нужного ID на странице")
    public boolean findIdInTable(String targetValue) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((ALL_FIND_TD_IN_TABLE)));
        String script = "return document.body.innerText.toLowerCase().includes('" + targetValue.toLowerCase() + "');";
        Boolean result = (Boolean) ((JavascriptExecutor) driver).executeScript(script);
        return result != null && result;
    }
}