package tests.api;

import adapters.UserAdapter;
import dto.api.user.GetUserRequest;
import dto.api.user.GetUserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserTest {

    SoftAssert softAssert = new SoftAssert();
    UserAdapter userAdapter = new UserAdapter();

    @Test
    @Owner("Kozachek Y.N.")
    @Description("Проверка получения пользователя")
    public void getUser() {

        GetUserRequest getUserRequest = GetUserRequest
                .builder()
                .userId("8018")
                .build();
        GetUserResponse response = userAdapter.getUser(getUserRequest);

        softAssert.assertEquals(response.getId(), 8018, "Не сошлось");
        softAssert.assertEquals(response.getFirstName(), "qwerty1112", "Не сошлось");
        softAssert.assertEquals(response.getSecondName(), "qwerty1112", "Не сошлось");
        softAssert.assertEquals(response.getAge(), 121, "Не сошлось");
        softAssert.assertEquals(response.getSex(), "MALE", "Не сошлось");
        softAssert.assertEquals(response.getMoney(), 121212.00, "Не сошлось");
        softAssert.assertAll();
    }
}