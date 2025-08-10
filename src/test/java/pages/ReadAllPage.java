package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ReadAllPage extends BasePage {

    private final By FIRST_ID_IN_TABLE = By.xpath("(//*[contains(text(), ' ID:')]/following::td)[1]");
    private final By ALL_FIND_TD_IN_TABLE = By.tagName("td");

    public ReadAllPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_ID_IN_TABLE));
        return this;
    }

    @Step("Открытие страницы просмотра всех созданных пользователей")
    public ReadAllPage open() {
        driver.get("http://82.142.167.37:4881/#/read/users");
        return this;
    }

    @Step("Сохранение всех элементов td и поиск нужного значения")
    public boolean findIdInTable(String targetValue) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((ALL_FIND_TD_IN_TABLE)));

        List<WebElement> cells = driver.findElements(ALL_FIND_TD_IN_TABLE);

        for (WebElement cell : cells) {
            if (cell.getText().trim().equals(targetValue)) {
                return true;
            }
        }
        return false;
    }
}