package steps.api;

import adapters.UserAdapter;
import dto.api.user.CreateUserRequest;
import dto.api.user.CreateUserResponse;
import io.qameta.allure.Step;
import tests.ui.BaseTest;

public class CreateUserStep extends BaseTest {

    UserAdapter userAdapter = new UserAdapter();
    AuthorizationStep authorizationStep = new AuthorizationStep();

    @Step("Создание пользавателя API")
    public CreateUserResponse createUserGetID() {
        CreateUserRequest createUserRequest = CreateUserRequest
                .builder()
                .age(56)
                .firstName("Moisha")
                .money(777888)
                .secondName("Zadnaja")
                .sex("MALE")
                .build();
        CreateUserResponse response = userAdapter.createUser(createUserRequest, authorizationStep.authorisationApi());
        return response;
    }
}