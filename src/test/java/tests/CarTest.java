package tests;

import com.github.javafaker.Faker;
import dto.Car;
import dto.SaveTestId;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class CarTest extends BaseTest {

    @Test
    @Description("Проверка сообщения, что авто создан")
    public void createCar() {
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarsPage.openCarPageCreate()
                .isPageOpened()
                .addCarInfo(car)
                .clickCreateCar();
        softAssert.assertTrue(createCarsPage.checkMessageIdCar().contains("New car ID:"),
                "Авто не создано");
        softAssert.assertEquals(createCarsPage.checkMessageCreateCar(),
                "Status: Successfully pushed, code: 201",
                "Авто не создано");
    }

    @Test
    @Description("Проверка сообщения, что авто не создан")
    public void createCarNotAllFieldCheck() {
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Electric")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarsPage.openCarPageCreate()
                .isPageOpened()
                .addCarInfo(car)
                .clickCreateCar();
        softAssert.assertEquals(createCarsPage.checkMessageCreateCar(),
                "Status: Invalid request data",
                "Авто не создано");
    }

    @Test
    @Description("Проверка, что созданный пользователь находится в таблице")
    public void checkCarInTable() {
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarStep.createCar(car);
        readAllCarsPage.
                open().
                isPageOpened();
//        softAssert.assertTrue(readAllCarsPage.findIdInTable(valueId), "Пользователь отсутствует в таблице");
    }

    @Test
    @Description("Проверка удаления авто")
    public void deleteCar() {
        SaveTestId id = new SaveTestId();
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarStep.createCar(car);
        String savedId = createCarsPage.saveIdCar(id);
        allDeletePage.open()
                .isPageOpened()
                .deleteTestCarId(savedId);
        softAssert.assertEquals(allDeletePage.getMessageDeleteCar(),
                "Status: 204",
                "Авто не удалено");
    }
}