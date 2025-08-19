package tests.jdbc;

import adapters.CarAdapter;
import adapters.GetTokenAdapter;
import database.DBConnection;
import dto.api.car.CarRequest;
import dto.api.car.CarResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarTest extends GetTokenAdapter {

    SoftAssert softAssert;
    private int createdCarId;
    private String accessToken;

    @Test(testName = "БД-тест. Создание авто",
            description = "Проверка создания авто")
    @Owner("Biruykov I.D.")
    @Description("Проверка авто в БД")
    public void checkCreateCarInDateBase() throws SQLException {
        accessToken = getAccessToken(); // Получение токена
        CarAdapter carAdapter = new CarAdapter();
        CarRequest carRequest = CarRequest.builder()
                .engineType("Diesel")
                .mark("tesla")
                .model("x")
                .price(6888.88)
                .build();
        CarResponse carResponse = carAdapter.createCar(accessToken, carRequest);
        createdCarId = carResponse.getId();

        DBConnection connection = new DBConnection();
        try {
            connection.connect();
            String query = "SELECT * " +
                    "FROM car " +
                    "JOIN engine_type " +
                    "ON car.engine_type_id = engine_type.id " +
                    "WHERE car.id = " + createdCarId;
            ResultSet result = connection.select(query);
            if (result.next()) {
                softAssert = new SoftAssert();
                softAssert.assertEquals(result.getInt("id"), createdCarId, "ID Авто не найден");
                softAssert.assertEquals(result.getString("mark"), "tesla", "Модель не совпадает");
                softAssert.assertEquals(result.getString("model"), "x", "Марка не совпадает");
                softAssert.assertEquals(result.getString("type_name"), "Diesel", "Тип двигателя не совпадает");
                softAssert.assertEquals(result.getDouble("price"), 6888.88, "Цена не совпадает");
            } else {
                softAssert.fail("Авто не найден в БД");
            }
        } finally {
            connection.close();
        }
        softAssert.assertAll();
    }
}