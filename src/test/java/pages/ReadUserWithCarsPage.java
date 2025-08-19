package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReadUserWithCarsPage extends BasePage {

    private final By BUTTON_READ = By.cssSelector("button.btn-primary");
    private final By FIELD_NUMBER = By.id("user_input");
    private final By USER_ID = By.xpath("//table[1]//td[1]");
    private final By CAR_ID = By.xpath("//table[2]//td[1]");
    private final By COUNT_AUTO = By.xpath("//table[1]//td[7]/font/font");

    public ReadUserWithCarsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ReadUserWithCarsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_READ));
        return this;
    }

    @Step("Открытие страницы просмотра пользователей с авто")
    public ReadUserWithCarsPage open() {
        driver.get(READ_USER_WITH_CARS_URL);
        return this;
    }

    @Step("Ввод ID пользователя")
    public ReadUserWithCarsPage inputIdUser(String valueId) {
        driver.findElement(FIELD_NUMBER).sendKeys(valueId);
        return this;
    }

    @Step("Нажать кнопку Read")
    public ReadUserWithCarsPage clickButtonRead() {
        driver.findElement(BUTTON_READ).click();
        return this;
    }

    @Step("Получение ID пользователя")
    public String getUserId() {
         return driver.findElement(USER_ID).getText();
    }

    @Step("Получение ID авто")
    public String getCarId() {
        return driver.findElement(CAR_ID).getText();
    }

    @Step("Получение количества авто в таблице Пользователя с авто")
    public String getCountAuto() {
        return driver.findElement(COUNT_AUTO).getText();
    }
}
