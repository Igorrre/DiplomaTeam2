package adapters;

import dto.api.authorization.AuthorizationRequest;
import dto.api.authorization.AuthorizationResponse;
import io.restassured.mapper.ObjectMapperType;

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