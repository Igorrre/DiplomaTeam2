package tests.ui;

import com.github.javafaker.Faker;
import dto.CarFields;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CarTest extends BaseTest {
    SoftAssert softAssert;
    @Test(priority = 1, testName = "Позитивный тест Проверка сообщения, что авто создан")
    @Description("Проверка сообщения, что авто создан")
    public void createCar() {
        softAssert = new SoftAssert();
        CarFields carFields = CarFields.builder().build();
        loginStep.authorisation(user, password);
        createCarStep.createCar(carFields);
        softAssert.assertTrue(createCarsPage.checkMessageIdCar().contains("New car ID:"),
                "Авто не создано");
        softAssert.assertEquals(createCarsPage.checkMessageCreateCar(),
                "Status: Successfully pushed, code: 201",
                "car ID: не создано");
        softAssert.assertAll();
    }

    @Test(priority = 2, testName = "Негативный тест. Проверка сообщения, что авто не создан")
    @Description("Проверка сообщения, что авто не создан")
    public void createCarNotAllFieldCheck() {
        softAssert = new SoftAssert();
        Faker faker = new Faker();
        CarFields carFields = CarFields.builder()
                .engine("Car")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarsPage.open()
                .isPageOpened()
                .addCarInfo(carFields)
                .clickCreateCar();
        softAssert.assertEquals(createCarsPage.checkMessageCreateCar(),
                //"Status: Invalid request data",
                "Status: AxiosError: Request failed with status code 400",
                "Авто не создано");
        softAssert.assertAll();
    }

    @Test(priority = 3, testName = "Проверка удаления авто")
    @Description("Проверка удаления авто")
    public void deleteCar() {
        softAssert = new SoftAssert();
        Faker faker = new Faker();
        CarFields carFields = CarFields.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarStep.createCar(carFields);
        carId = createCarsPage.getValueCarId();
        allDeletePage.open()
                .isPageOpened()
                .deleteTestCarId(carId);
        softAssert.assertEquals(allDeletePage.getMessageDeleteCar(),
                "Status: 204",
                "Авто не удалено");
        softAssert.assertAll();
    }

    @Test(priority = 4, testName = "Проверка, что созданный авто находится в таблице")
    @Description("Проверка, что созданный авто находится в таблице")
    public void checkIdCarInTable() {
        softAssert = new SoftAssert();
        Faker faker = new Faker();
        CarFields carFields = CarFields.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarStep.createCar(carFields);
        carId = createCarsPage.getValueCarId();
        readAllUsersPage.open().isPageOpened();
        softAssert.assertTrue(readAllCarsPage.findIdCarInTable(carId),
                "Авто отсутствует в таблице");
        softAssert.assertAll();
    }
}