package tests.api;

import adapters.UserAdapter;
import dto.api.user.AddMoneyUserResponse;
import dto.api.user.CreateUserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.api.AuthorizationStep;
import steps.api.CreateUserStep;
import tests.ui.BaseTest;

public class AddMoneyUserTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    UserAdapter userAdapter = new UserAdapter();
    CreateUserStep createUserStep = new CreateUserStep();
    AuthorizationStep authorizationStep = new AuthorizationStep();
    int money = 10;

    CreateUserResponse createUserStepResponse = createUserStep.createUserGetID();

    @Test(testName = "API-тест. Добавление денег пользователю",
            description = "Проверка добавления денег пользователю")
    @Owner("Kozachek Y.N.")
    @Description("Проверка добавления денег пользователю")
    public void addMoneyUser() {

        AddMoneyUserResponse response = userAdapter.addMoneyUser(authorizationStep.authorisationApi(), createUserStepResponse.getId(), money);

        softAssert.assertEquals(response.getFirstName(), createUserStepResponse.getFirstName(), "Не сошлось");
        softAssert.assertEquals(response.getSecondName(), createUserStepResponse.getSecondName(), "Не сошлось");
        softAssert.assertEquals(response.getAge(), createUserStepResponse.getAge(), "Не сошлось");
        softAssert.assertEquals(response.getSex(), createUserStepResponse.getSex(), "Не сошлось");
        softAssert.assertEquals(response.getId(), createUserStepResponse.getId(), "Не сошлось");
        softAssert.assertEquals(response.getMoney(), createUserStepResponse.getMoney() + money + 0.0, "Не сошлось");
        softAssert.assertAll();
    }
}