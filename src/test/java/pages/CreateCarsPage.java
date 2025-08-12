package pages;

import dto.CarFields;
import dto.SaveTestId;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Input;

@Log4j2
public class CreateCarsPage extends BasePage {
    private final By CAR_FIELD = By.xpath("//th[contains(text(), 'Car')]");
    private final By CARS = By.xpath("//a[contains(text(), 'Cars')]");
    private final By PUSH_TO_API = By.cssSelector("button.tableButton.btn.btn-primary");
    private final By CREATE_CARS = By.xpath("//a[contains(@class, 'dropdown-item') and text() = 'Create new']");
    private final By GET_ID = By.xpath("//button[@class='newId btn btn-secondary']");
    private final By GET_STATUS = By.xpath("//button[@class='status btn btn-secondary']");
    private final By ALL_DELETE_FIELD = By.xpath("//a[@href=\"#/delete/all\"]");

    public CreateCarsPage(WebDriver driver) {
        super(driver);
    }
    @Step("Открытие страницы создания авто")
    public CreateCarsPage openCarPageCreate() {
        driver.findElement(CARS).click();
        driver.findElement(CREATE_CARS).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CAR_FIELD));
        return this;
    }

    @Override
    @Step("Проверка открытия страницы")
    public CreateCarsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CAR_FIELD));
        return this;
    }

    @Step("Заполнение карточки авто")
    public CreateCarsPage addCarInfo(CarFields carFields) {
        log.info("engine: {}", carFields.getEngine());
        new Input(driver, "engine").write(carFields.getEngine());
        log.info("mark: {}", carFields.getMark());
        new Input(driver, "mark").write(carFields.getMark());
        log.info("model: {}", carFields.getModel());
        new Input(driver, "model").write(carFields.getModel());
        log.info("price: {}", carFields.getPrice());
        new Input(driver, "price").write(String.valueOf(carFields.getPrice()));
        return this;
    }

    @Step("Клик по кнопке PUSH TO API")
    public CreateCarsPage clickCreateCar() {
        driver.findElement(PUSH_TO_API).click();
        return this;
    }

    @Step("Проверка сообщения о создании авто")
    public String checkMessageCreateCar() {
        log.info("Get message Success");
        wait.until(ExpectedConditions.visibilityOfElementLocated(GET_STATUS));
        return driver.findElement(GET_STATUS).getText();
    }

    @Step("Проверка сообщения о создании ID авто")
    public String checkMessageIdCar() {
        log.info("Get message ID");
        wait.until(ExpectedConditions.visibilityOfElementLocated(GET_ID));
        return driver.findElement(GET_ID).getText();
    }

    @Step("Получение ID авто")
    public String saveIdCar(SaveTestId id) {
        String savedText = driver.findElement(GET_ID).getText();
        String savedId = savedText.replaceAll("\\D", "");
        id.setSavedId(savedId);
        return savedId;
    }

    @Step("Клик по вкладке All DELETE")
    public AllDeletePage clickAllDelete() {
        driver.findElement(ALL_DELETE_FIELD).click();
        return new AllDeletePage(driver);
    }

    //Если использовать этот метод, то ID авто при создании сохраняется и значение можно использовать дальше
    private final By VALUE_STATUS_CODE = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[2]");
    private final By NEW_CAR_ID = By.xpath("//*[@id=\"root\"]/div/section/div/div/button[3]");
    @Step("Получение значения Car ID")
    public String getValueCarId() {
        wait.until(ExpectedConditions.textToBe(VALUE_STATUS_CODE, "Status: Successfully pushed, code: 201"));
        String value = driver.findElement(NEW_CAR_ID).getText();
        if (value.contains(":")) {
            String[] parts = value.split(":");
            return parts[1].trim();
        } else {
            throw new RuntimeException("Некорректный формат текста в элементе NEW_CAR_ID: " + value);
        }
    }
}