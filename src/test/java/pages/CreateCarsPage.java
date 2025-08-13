package pages;

import dto.ui.car.Car;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Input;

@Log4j2
public class CreateCarsPage extends BasePage {
    private final By CAR_FIELD = By.xpath("//th[contains(text(), 'Car')]");
    private final By PUSH_TO_API = By.cssSelector("button.tableButton.btn.btn-primary");
    private final By CAR_ID = By.xpath("//button[@class='newId btn btn-secondary']");
    private final By VALUE_STATUS_CODE = By.xpath("//button[@class='status btn btn-secondary']");

    public CreateCarsPage(WebDriver driver) {
        super(driver);
    }
    @Step("Открытие страницы создания авто")
    public CreateCarsPage open() {
        log.info("Open page new car");
        driver.get(CREATE_CARS_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(CAR_FIELD));
        return this;
    }

    @Override
    @Step("Проверка открытия страницы")
    public CreateCarsPage isPageOpened() {
        log.info("Page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CAR_FIELD));
        return this;
    }

    @Step("Заполнение карточки авто")
    public CreateCarsPage addCarInfo(Car car) {
        log.info("engine: {}", car.getEngine());
        new Input(driver, "engine").write(car.getEngine());
        log.info("mark: {}", car.getMark());
        new Input(driver, "mark").write(car.getMark());
        log.info("model: {}", car.getModel());
        new Input(driver, "model").write(car.getModel());
        log.info("price: {}", car.getPrice());
        new Input(driver, "price").write(String.valueOf(car.getPrice()));
        return this;
    }

    @Step("Клик по кнопке PUSH TO API")
    public void clickCreateCar() {
        log.info("Click button PUSH TO API");
        wait.until(ExpectedConditions.visibilityOfElementLocated(PUSH_TO_API));
        driver.findElement(PUSH_TO_API).click();
    }

    @Step("Проверка сообщения о создании авто")
    public String checkMessageCreateCar() {
        log.info("Get message Success");
        wait.until(ExpectedConditions.visibilityOfElementLocated(VALUE_STATUS_CODE));
        return driver.findElement(VALUE_STATUS_CODE).getText();
    }

    @Step("Проверка сообщения о создании ID авто")
    public String checkMessageIdCar() {
        log.info("Get message ID");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(CAR_ID, "New car ID:"));
        return driver.findElement(CAR_ID).getText();
    }

    @Step("Получение значения Car ID")
    public String getValueCarId() {
        log.info("Get car ID");
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 201"));
        String value = driver.findElement(CAR_ID).getText();
        if (value.contains(":")) {
            String[] parts = value.split(":");
            return parts[1].trim();
        } else {
            throw new RuntimeException("Некорректный формат текста в элементе NEW_CAR_ID: " + value);
        }
    }
}