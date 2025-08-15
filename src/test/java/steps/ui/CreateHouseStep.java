package steps.ui;

import dto.ui.house.House;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CreateHousePage;

public class CreateHouseStep {
    WebDriver driver;
    CreateHousePage createHousePage;

    public CreateHouseStep(WebDriver driver) {
        this.driver = driver;
        createHousePage = new CreateHousePage(driver);
    }

    @Step("Получение значения User ID на странице создания пользователя")
    public void createHouse(House house) {
        createHousePage
                .open()
                .isPageOpened()
                .addHouseInfo(house)
                .clickCreateHouse();
    }
}