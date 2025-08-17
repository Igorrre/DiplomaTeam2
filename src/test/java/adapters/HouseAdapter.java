package adapters;

import dto.api.house.HouseRequest;
import dto.api.house.HouseResponse;
import io.qameta.allure.Step;

public class HouseAdapter extends BaseAdapter {

    @Step("Создание тестового дома по Id")
    public HouseResponse createHouse(String accessToken, HouseRequest houseRequest) {
        return spec
                .header("Authorization", "Bearer " + accessToken)
                .body(gson.toJson(houseRequest))
                .when()
                .post(HOUSE_URI)
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(HouseResponse.class);
    }

    @Step("Чтение тестового дома по Id")
    public HouseResponse readHouse(String accessToken, Integer id) {
        return spec
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(HOUSE_URI + "/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(HouseResponse.class);
    }
}