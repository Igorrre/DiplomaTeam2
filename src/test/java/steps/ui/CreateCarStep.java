package steps.ui;

import dto.ui.car.Car;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CreateCarsPage;

public class CreateCarStep {
    WebDriver driver;
    CreateCarsPage createCarsPage;

    public CreateCarStep(WebDriver driver) {
        this.driver = driver;
        createCarsPage = new CreateCarsPage(driver);
    }

    @Step("Создание Car")
    public void createCar(Car car) {
        createCarsPage
                .open()
                .isPageOpened()
                .addCarInfo(car)
                .clickCreateCar();
    }
}