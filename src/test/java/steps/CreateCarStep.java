package steps;

import dto.CarFields;
import dto.SaveTestId;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CreateCarsPage;

public class CreateCarStep {
    WebDriver driver;
    CreateCarsPage createCarsPage;
    CarFields carFields;


    public CreateCarStep(WebDriver driver) {
        this.driver = driver;
        createCarsPage = new CreateCarsPage(driver);
    }

    @Step("Получение значения User ID на странице создания пользователя")
    public void createCar(CarFields carFields) {
        SaveTestId id = new SaveTestId();
        createCarsPage
                .openCarPageCreate()
                .isPageOpened()
                .addCarInfo(carFields)
                .clickCreateCar()
                .saveIdCar(id);
    }
}