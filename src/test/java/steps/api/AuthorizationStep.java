package steps.api;

import adapters.AuthorizationAdapter;
import dto.api.authorization.AuthorizationRequest;
import dto.api.authorization.AuthorizationResponse;
import io.qameta.allure.Step;
import tests.ui.BaseTest;

public class AuthorizationStep extends BaseTest {

    @Step("Авторизация пользователя API")
    public String authorisationApi(String user, String password) {
        AuthorizationAdapter authorizationAdapter = new AuthorizationAdapter();
        AuthorizationRequest authorizationRequest = AuthorizationRequest.builder()
                .password(password)
                .username(user)
                .build();
        AuthorizationResponse rs = authorizationAdapter.authorization(authorizationRequest);
        return rs.getAccessToken();
    }
}