package adapters;

import dto.api.user.CreateUserRequest;
import dto.api.user.CreateUserResponse;
import dto.api.user.GetUserRequest;
import dto.api.user.GetUserResponse;

public class UserAdapter extends BaseAdapter {

    public GetUserResponse getUser(GetUserRequest getUserRequest) {
        return spec
                .body(gson.toJson(getUserRequest))
                .when()
                .get(GET_USER_URI)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(GetUserResponse.class);
    }

    public CreateUserResponse createUser(CreateUserRequest createUserRequest, String token) {
        return spec
                .header("Authorization", "Bearer " + token)
                .body(gson.toJson(createUserRequest))
                .when()
                .post(CREATE_USER_URI)
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);
    }
}