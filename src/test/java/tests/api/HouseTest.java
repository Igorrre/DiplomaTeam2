package tests.api;

import adapters.GetTokenAdapter;
import adapters.HouseAdapter;
import dto.api.house.HouseRequest;
import dto.api.house.HouseResponse;
import dto.api.house.ParkingPlaceRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HouseTest extends GetTokenAdapter {

    SoftAssert softAssert;
    int createdHouseId;
    String accessToken;

    @Test(priority = 1, description = "Проверка создания дома")
    @Owner("Biruykov I.D.")
    @Description("Проверка создания дома")
    public void createHouse() {
        accessToken = getAccessToken(); // Получение токена
        HouseAdapter houseAdapter = new HouseAdapter();
        HouseRequest houseRequest = HouseRequest.builder()
                .floorCount(1)
                .parkingPlaces(ParkingPlaceRequest.builder()
                        .isCovered(false)
                        .isWarm(true)
                        .placesCount(1)
                        .build())
                .price(1000)
                .build();
        HouseResponse rsCreateHouse = houseAdapter.createHouse(accessToken, houseRequest);
        createdHouseId = rsCreateHouse.getId();

        softAssert = new SoftAssert();
        softAssert.assertEquals(rsCreateHouse.getFloorCount(), 1, "Количество этажей не совпадает");
        softAssert.assertEquals(rsCreateHouse.getPrice(), 1000, "Стоимость дома не совпадает");
        softAssert.assertNotNull(rsCreateHouse.getId(), "ID дома равен null");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Проверка получения дома")
    @Owner("Biruykov I.D.")
    @Description("Проверка получения дома")
    public void readHouse() {
        accessToken = getAccessToken(); // Получение токена
        HouseAdapter houseAdapter = new HouseAdapter();
        HouseResponse rsReadHouse = houseAdapter.readHouse(accessToken, createdHouseId);

        softAssert = new SoftAssert();
        softAssert.assertEquals(rsReadHouse.getFloorCount(), 1, "Количество этажей не совпадает");
        softAssert.assertEquals(rsReadHouse.getPrice(), 1000, "Стоимость дома не совпадает");
        softAssert.assertEquals(rsReadHouse.getId(), createdHouseId, "ID Дома не найден");
        softAssert.assertAll();
    }
}