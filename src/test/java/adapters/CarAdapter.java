package adapters;

import dto.api.car.CarRequest;
import dto.api.car.CarResponse;
import io.qameta.allure.Step;

public class CarAdapter extends BaseAdapter {

    @Step("Создание тестового авто по Id")
    public CarResponse createCar(String accessToken, CarRequest carRequest) {
        return spec
                .header("Authorization", "Bearer " + accessToken)
                .body(gson.toJson(carRequest))
                .when()
                .post(CAR_URI)
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(CarResponse.class);
    }

    @Step("Чтение тестового авто по Id")
    public CarResponse readCar(String accessToken, Integer id) {
        return spec
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(CAR_URI + "/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CarResponse.class);
    }

    @Step("Обновление тестового авто по Id")
    public CarResponse updateCar(String accessToken, Integer id, CarRequest carRequest) {
        return spec
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .body(carRequest)
                .when()
                .put(CAR_URI + "/" + id)
                .then()
                .log().all()
                .statusCode(202)
                .extract()
                .as(CarResponse.class);
    }

    @Step("Удаление тестового авто по Id")
    public void deleteCar(String accessToken, Integer id) {
        spec
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(CAR_URI + "/" + id)
                .then()
                .log().all()
                .statusCode(204);
    }
}