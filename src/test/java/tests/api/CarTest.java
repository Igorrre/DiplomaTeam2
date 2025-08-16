package tests.api;

import adapters.CarAdapter;
import adapters.GetTokenAdapter;
import dto.api.car.CarRequest;
import dto.api.car.CarResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CarTest extends GetTokenAdapter {

    SoftAssert softAssert;
    private int createdCarId;
    private String accessToken;

    @Test(priority = 1, testName = "Проверка создания авто")
    @Owner("Biruykov I.D.")
    @Description("Проверка создания авто")
    public void createCar() {
        accessToken = getAccessToken(); // Получение токена
        CarAdapter carAdapter = new CarAdapter();
        CarRequest carRequest = CarRequest.builder()
                .engineType("Diesel")
                .mark("tesla")
                .model("x")
                .price(6888.88)
                .build();
        CarResponse carResponse = carAdapter.createCar(accessToken, carRequest);
        softAssert = new SoftAssert();
        softAssert.assertEquals(carResponse.getMark(), "tesla", "Модель не совпадает");
        softAssert.assertEquals(carResponse.getModel(), "x", "Марка не совпадает");
        softAssert.assertEquals(carResponse.getEngineType(), "Diesel", "Тип двигателя не совпадает");
        softAssert.assertEquals(carResponse.getPrice(), 6888.88, "Цена не совпадает");
        createdCarId = carResponse.getId();

        softAssert.assertAll();
    }

    @Test(priority = 2, testName = "Проверка получения авто")
    @Owner("Biruykov I.D.")
    @Description("Проверка получения авто")
    public void readCar() {
        accessToken = getAccessToken(); // Получение токена
        CarAdapter carAdapter = new CarAdapter();
        CarResponse rsReadCar = carAdapter.readCar(accessToken, createdCarId);
        softAssert = new SoftAssert();
        softAssert.assertEquals(rsReadCar.getMark(), "tesla", "Модель не совпадает");
        softAssert.assertEquals(rsReadCar.getModel(), "x", "Марка не совпадает");
        softAssert.assertEquals(rsReadCar.getEngineType(), "Diesel", "Тип двигателя не совпадает");
        softAssert.assertEquals(rsReadCar.getPrice(), 6888.88, "Цена не совпадает");

        softAssert.assertAll();
    }

    @Test(priority = 3, testName = "Проверка изменения авто авто")
    @Owner("Biruykov I.D.")
    @Description("Проверка изменения авто авто")
    public void updateCar() {
        accessToken = getAccessToken(); // Получение токена
        CarAdapter carAdapter = new CarAdapter();
        CarRequest carRequest = CarRequest.builder()
                .engineType("Diesel")
                .mark("teslaaaaa")
                .model("xxx")
                .price(7777.77)
                .build();
        CarResponse rsUpdateCar = carAdapter.updateCar(accessToken, createdCarId, carRequest);
        softAssert = new SoftAssert();
        softAssert.assertEquals(rsUpdateCar.getMark(), "teslaaaaa", "Модель не совпадает");
        softAssert.assertEquals(rsUpdateCar.getModel(), "xxx", "Марка не совпадает");
        softAssert.assertEquals(rsUpdateCar.getEngineType(), "Diesel", "Тип двигателя не совпадает");
        softAssert.assertEquals(rsUpdateCar.getPrice(), 7777.77, "Цена не совпадает");

        softAssert.assertAll();
    }

    @Test(priority = 4, testName = "Проверка удаления авто")
    @Owner("Biruykov I.D.")
    @Description("Проверка удаления авто")
    public void deleteCar() {
        accessToken = getAccessToken(); // Получение токена
        CarAdapter carAdapter = new CarAdapter();
        carAdapter.deleteCar(accessToken, createdCarId);
    }
}