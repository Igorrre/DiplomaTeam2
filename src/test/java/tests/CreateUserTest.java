package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static dto.UserFaker.setUserFieldsFaker;
import static org.testng.Assert.assertEquals;

public class CreateUserTest extends BaseTest {

    @Test
    @Owner("Kozachek Y.N.")
    @Description("Проверка создания пользователя статус кода 201")
    public void checkStatusCode201() {

        userFields = setUserFieldsFaker();

        loginStep.authorisation(user, password);
        createUserPage
                .open()
                .isPageOpened()
                .setValuesToCreateUser(userFields)
                .clickButtonPushToApi();

        assertEquals(createUserPage.getTextValueStatusCode(), "Status: Successfully pushed, code: 201",
                "Статус код не равен 201");
    }
}