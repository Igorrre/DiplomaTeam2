package tests.ui;

import com.github.javafaker.Faker;
import dto.ui.car.Car;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CarTest extends BaseTest {

    SoftAssert softAssert;

    @Test(testName = "Создание авто",
            priority = 1,
            description = "Позитивный тест Проверка сообщения, что авто создан")
    @Owner("Biruykov I.D.")
    @Description("Проверка сообщения, что авто создан")
    public void createCar() {
        softAssert = new SoftAssert();
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarStep.createCar(car);
        softAssert.assertTrue(createCarsPage.checkMessageIdCar().contains("New car ID:"),
                "Авто не создано");
        softAssert.assertAll();
    }

    @Test(testName = "Создание авто с невалидными данными",
            priority = 2,
            description = "Негативный тест. Проверка сообщения, что авто не создан")
    @Owner("Biruykov I.D.")
    @Description("Проверка сообщения, что авто не создан")
    public void createCarNotAllFieldCheck() {
        softAssert = new SoftAssert();
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Car")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarsPage.open()
                .isPageOpened()
                .addCarInfo(car)
                .clickCreateCar();
        softAssert.assertEquals(createCarsPage.checkMessageCreateCar(),
                "Status: Invalid request data",
                //"Status: AxiosError: Request failed with status code 400",
                "Авто не создано");
        softAssert.assertAll();
    }

    @Test(testName = "Поиск авто в таблице авто",
            priority = 3,
            description = "Проверка, что созданный авто находится в таблице")
    @Owner("Biruykov I.D.")
    @Description("Проверка, что созданный авто находится в таблице")
    public void checkIdCarInTable() {
        softAssert = new SoftAssert();
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarStep.createCar(car);
        carId = createCarsPage.getValueCarId();
        readAllCarsPage.open().isPageOpened();
        softAssert.assertTrue(readAllCarsPage.findIdCarInTable(carId),
                "Авто отсутствует в таблице");
        softAssert.assertAll();
    }

    @Test(testName = "Удаление авто",
            priority = 4,
            description = "Проверка удаления авто")
    @Owner("Biruykov I.D.")
    @Description("Проверка удаления авто")
    public void deleteCar() {
        softAssert = new SoftAssert();
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarStep.createCar(car);
        carId = createCarsPage.getValueCarId();
        allDeletePage.open()
                .isPageOpened()
                .deleteTestCarId(carId);
        softAssert.assertEquals(allDeletePage.getMessageDeleteCar(),
                "Status: 204",
                "Авто не удалено");
        softAssert.assertAll();
    }

    @Test(testName = "Продажа авто",
            priority = 5,
            description = "Позитивный тест Продажа авто")
    @Owner("Bazhenov Y.N.")
    @Description("Проверка, что авто у пользователя удален")
    public void checkSellCarInReadUser() {
        softAssert = new SoftAssert();
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        createCarStep.createCar(car);
        carId = createCarsPage.getValueCarId();
        allDeletePage.open()
                .isPageOpened()
                .deleteTestCarId(carId);
        readUserWithCarsPage.open()
                .isPageOpened()
                .inputIdUser(userId)
                .clickButtonRead()
                .getCountAuto();
        softAssert.assertTrue(readUserWithCarsPage.getCountAuto().isEmpty(), "Авто не удалено");
        softAssert.assertAll();
    }
}