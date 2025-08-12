package pages;

import dto.HouseFields;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Input;

@Log4j2
public class CreateHousePage extends BasePage {

    private final By HOUSE_FIELD = By.xpath("//th[contains(text(), ' Floors:')]");
    private final By PUSH_TO_API = By.cssSelector("button.tableButton.btn.btn-primary");
    private final By GET_ID = By.xpath("//button[@class='newId btn btn-secondary']");
    private final By GET_STATUS = By.xpath("//button[@class='status btn btn-secondary']");
    private final By ALL_DELETE_FIELD = By.xpath("//a[@href=\"#/delete/all\"]");

    public CreateHousePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы создания дома")
    public CreateHousePage open() {
        driver.get(CREATE_HOUSE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(HOUSE_FIELD));
        return this;
    }

    @Override
    @Step("Проверка открытия страницы")
    public CreateHousePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(HOUSE_FIELD));
        return this;
    }

    @Step("Заполнение карточки дома Faker")
    public CreateHousePage addHouseInfo(HouseFields houseFields) {
        log.info("floor_send: {}", houseFields.getFloor_send());
        new Input(driver, "floor_send").write(String.valueOf(houseFields.getFloor_send()));
        log.info("price_send: {}", houseFields.getPrice_send());
        new Input(driver, "price_send").write(String.valueOf(houseFields.getPrice_send()));
        log.info("parking_first_send: {}", houseFields.getParking_first_send());
        new Input(driver, "parking_first_send").write(String.valueOf(houseFields.getParking_first_send()));
        log.info("parking_second_send: {}", houseFields.getParking_second_send());
        new Input(driver, "parking_second_send").write(String.valueOf(houseFields.getParking_second_send()));
        log.info("parking_third_send: {}", houseFields.getParking_third_send());
        new Input(driver, "parking_third_send").write(String.valueOf(houseFields.getParking_third_send()));
        log.info("parking_fourth_send: {}", houseFields.getParking_fourth_send());
        new Input(driver, "parking_fourth_send").write(String.valueOf(houseFields.getParking_fourth_send()));
        return this;
    }

    @Step("Клик по кнопке PUSH TO API")
    public void clickCreateHouse() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PUSH_TO_API));
        driver.findElement(PUSH_TO_API).click();
    }

    @Step("Проверка сообщения о создании дома")
    public String checkMessageCreateHouse() {
        log.info("Get message Success");
        wait.until(ExpectedConditions.visibilityOfElementLocated(GET_STATUS));
        return driver.findElement(GET_STATUS).getText();
    }

    @Step("Проверка сообщения о создании ID дома")
    public String checkMessageIdHouse() {
        log.info("Get message ID");
        wait.until(ExpectedConditions.visibilityOfElementLocated(GET_ID));
        return driver.findElement(GET_ID).getText();
    }

//    @Step("Получение ID дома")
//    public String saveIdHouse(SaveTestId id) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(GET_ID));
//        String savedText = driver.findElement(GET_ID).getText();
//        String houseId = savedText.replaceAll("\\D", "");
//        id.setHouseId(houseId);
//        return houseId;
//    }

    @Step("Клик по вкладке All DELETE")
    public AllDeletePage clickAllDelete() {
        driver.findElement(ALL_DELETE_FIELD).click();
        return new AllDeletePage(driver);
    }

    @Step("Получение значения Car ID")
    public String getValueHouseId() {
        wait.until(ExpectedConditions.textToBe(GET_STATUS, "Status: Successfully pushed, code: 201"));
        String value = driver.findElement(GET_ID).getText();
        if (value.contains(":")) {
            String[] parts = value.split(":");
            return parts[1].trim();
        } else {
            throw new RuntimeException("Некорректный формат текста в элементе NEW_CAR_ID: " + value);
        }
    }
}