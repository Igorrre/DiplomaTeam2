package adapters;

import dto.api.user.*;

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

    public AddMoneyUserResponse addMoneyUser(String token, int id, int money) {
        return spec
                .header("Authorization", "Bearer " + token)
                .when()
                .post(ADD_MONEY_USER_URI + "user/" + id + "/money/" + money)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(AddMoneyUserResponse.class);
    }
}