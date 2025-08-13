package tests.api;

import dto.api.user.GetUserRequest;
import dto.api.user.GetUserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import pageModels.BaseTest;

public class UserTest extends BaseTest {

    @Test
    @Owner("Kozachek Y.N.")
    @Description("Проверка получения пользователя")
    public void getUser() {

        GetUserRequest getUserRequest = GetUserRequest
                .builder()
                .userId("8018")
                .build();
        GetUserResponse response = userPage.getUser(getUserRequest);

        softAssert.assertEquals(response.getId(), 8018);
        softAssert.assertEquals(response.getFirstName(), "qwerty1112");
        softAssert.assertEquals(response.getSecondName(), "qwerty1112");
        softAssert.assertEquals(response.getAge(), 121);
        softAssert.assertEquals(response.getSex(), "MALE");
        softAssert.assertEquals(response.getMoney(), 121212.00);
    }
}