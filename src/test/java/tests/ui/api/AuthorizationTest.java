package tests.ui.api;

import adapters.AuthorizationAdapter;
import dto.api.AuthorizationRq;
import dto.api.AuthorizationRs;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.ui.BaseTest;

public class AuthorizationTest extends BaseTest {

    @Test
    @Description("Проверка получения токена для авторизации")
    public void authorization() {
        AuthorizationAdapter authorizationAdapter = new AuthorizationAdapter();
        AuthorizationRq authorizationRq = AuthorizationRq.builder()
                .password(password)
                .username(user)
                .build();
        AuthorizationRs rs = authorizationAdapter.authorization(authorizationRq);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(rs.getAccessToken(), "Токен не должен быть null");
        softAssert.assertFalse(rs.getAccessToken().isEmpty(), "Токен не должен быть пустым");
        softAssert.assertAll();
    }
}
