package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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

        List<WebElement> cells = driver.findElements(ALL_FIND);

        for (WebElement cell : cells) {
            if (cell.getText().trim().equals(targetValue)) {
                return true;
            }
        }
        return false;
    }
}