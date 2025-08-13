package adapters;

import dto.api.authorization.AuthorizationRequest;
import dto.api.authorization.AuthorizationResponse;
import io.restassured.mapper.ObjectMapperType;

import static io.restassured.RestAssured.given;

public class AuthorizationAdapter extends BaseAdapter {

    public AuthorizationResponse authorization(AuthorizationRequest authorizationRequest) {
        return spec
                .body(gson.toJson(authorizationRequest))
                .when()
                .post(BASE_URI + "login")
                .then()
                .log().all()
                .statusCode(202)
                .extract()
                .as(AuthorizationResponse.class, ObjectMapperType.GSON);

    }
}
