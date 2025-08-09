package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By LOGIN_FIELD = By.name("email");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.cssSelector("button.btn-primary");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы входа в систему")
    public void open() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_FIELD));
    }

    @Step("Проверка открытия страницы логина")
    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_FIELD));
    }

    @Step("Вход в систему с валидным именем пользователя: {user} и валидным паролем: {password}")
    public void login(String user, String password) {
        driver.findElement(LOGIN_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Получение текста алерта")
    public String findAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        return alertText;
    }
}
