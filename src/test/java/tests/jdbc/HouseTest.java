package tests.jdbc;

import adapters.GetTokenAdapter;
import adapters.HouseAdapter;
import dto.api.house.HouseRequest;
import dto.api.house.HouseResponse;
import dto.api.house.ParkingPlaceRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseTest extends GetTokenAdapter {

    SoftAssert softAssert;
    int createdHouseId;
    String accessToken;

    @Test(priority = 1, testName = "Проверка создания дома")
    @Owner("Biruykov I.D.")
    @Description("Проверка создания дома")
    public void checkCreateHouseInDateBase() throws SQLException {
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

        DBConnection connection = new DBConnection();
        try {
            connection.connect();
            String query = "SELECT * " +
                    "FROM house " +
                    "WHERE id = " + createdHouseId;
            ResultSet result = connection.select(query);
            if (result.next()) {
                System.out.println("=== Результаты из БД ===");
                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Количество этажей: " + result.getInt("floor_count"));
                System.out.println("Стоимость: " + result.getInt("price"));

                softAssert = new SoftAssert();
                softAssert.assertEquals(result.getInt("id"), createdHouseId, "ID Дома не найден");
                softAssert.assertEquals(rsCreateHouse.getFloorCount(), 1, "Количество этажей не совпадает");
                softAssert.assertEquals(rsCreateHouse.getPrice(), 1000, "Стоимость дома не совпадает");

                softAssert.assertAll();
            } else {
                System.out.println("Дом с ID " + createdHouseId + " не найден");
                softAssert.fail("Дом не найден в БД");
            }
        } finally {
            connection.close();
        }
        softAssert.assertAll();
    }
}