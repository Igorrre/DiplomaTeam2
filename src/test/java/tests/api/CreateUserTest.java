package tests.api;

import adapters.UserAdapter;
import dto.api.user.CreateUserRequest;
import dto.api.user.CreateUserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.api.AuthorizationStep;
import tests.ui.BaseTest;

public class CreateUserTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();
    UserAdapter userAdapter = new UserAdapter();
    AuthorizationStep authorizationStep = new AuthorizationStep();

    @Test(testName = "API-тест. Создание пользователя",
            description = "Проверка создания пользователя")
    @Owner("Kozachek Y.N.")
    @Description("Создание пользователя API")
    public void createUser() {
        CreateUserRequest createUserRequest = CreateUserRequest
                .builder()
                .age(56)
                .firstName("Moisha")
                .money(777888)
                .secondName("Zadnaja")
                .sex("MALE")
                .build();
        CreateUserResponse response = userAdapter.createUser(createUserRequest, authorizationStep.authorisationApi());

        softAssert.assertEquals(response.getFirstName(), createUserRequest.getFirstName(), "Не сошлось");
        softAssert.assertEquals(response.getSecondName(), createUserRequest.getSecondName(), "Не сошлось");
        softAssert.assertEquals(response.getAge(), createUserRequest.getAge(), "Не сошлось");
        softAssert.assertEquals(response.getSex(), createUserRequest.getSex(), "Не сошлось");
        softAssert.assertEquals(response.getMoney(), createUserRequest.getMoney(), "Не сошлось");
        softAssert.assertNotNull(response.getId(), "Не сошлось");
        softAssert.assertAll();
    }
}