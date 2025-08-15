package adapters;

import dto.api.authorization.AuthorizationRequest;
import dto.api.authorization.AuthorizationResponse;
import io.qameta.allure.Step;
import tests.ui.BaseTest;

public class GetTokenStep extends BaseTest {

    @Step("Получить accessToken через API авторизации")
    public String getAccessToken() {
        AuthorizationAdapter authorizationAdapter = new AuthorizationAdapter();
        AuthorizationRequest authorizationRequest = AuthorizationRequest.builder()
                .password(password)
                .username(user)
                .build();

        AuthorizationResponse rs = authorizationAdapter.authorization(authorizationRequest);
        String token = rs.getAccessToken();
        return token;
    }
}