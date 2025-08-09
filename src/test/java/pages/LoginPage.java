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

    @Override
    @Step("Проверка открытия страницы логина")
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_FIELD));
        return this;
    }

    @Step("Вход в систему с валидным именем пользователя: {user} и валидным паролем: {password}")
    public void login(String user, String password) {
        driver.findElement(LOGIN_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Получение текста алерта")
    public String getTextAlert() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    @Step("Клик по кнопке 'ок' алерта")
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
