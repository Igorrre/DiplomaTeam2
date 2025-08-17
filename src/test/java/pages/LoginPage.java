package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private final By LOGIN_FIELD = By.name("email");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.cssSelector("button.btn-primary");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы входа в систему")
    public LoginPage open() {
        log.info("Open authorization page");
        driver.get(BASE_URI);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_FIELD));
        return this;
    }

    @Override
    @Step("Проверка открытия страницы логина")
    public LoginPage isPageOpened() {
        log.info("Page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Вход в систему с валидным именем пользователя: {user} и валидным паролем: {password}")
    public LoginPage login(String user, String password) {
        log.info("login: {}", user);
        driver.findElement(LOGIN_FIELD).sendKeys(user);
        log.info("password: {}", user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        log.info("Click button GO");
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    @Step("Получение текста алерта")
    public String getTextAlert() {
        log.info("Get text alert");
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    @Step("Клик по кнопке 'ок' алерта")
    public LoginPage acceptAlert() {
        log.info("Click button OK on alert");
        wait.until(ExpectedConditions.alertIsPresent()).getText();
        driver.switchTo().alert().accept();
        return this;
    }
}
