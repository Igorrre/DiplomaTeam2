package pageModels;

import dto.api.user.GetUserRequest;
import dto.api.user.GetUserResponse;

public class UserPage extends BaseURL {

    public GetUserResponse getUser(GetUserRequest getUserRequest) {
        return specification
                .body(gson.toJson(getUserRequest))
                .when()
                .get(GET_USER_URI)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(GetUserResponse.class);
    }
}