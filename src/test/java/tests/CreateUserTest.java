package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static dto.UserFaker.setUserFieldsFaker;
import static org.testng.Assert.assertEquals;

public class CreateUserTest extends BaseTest {
    SoftAssert softAssert;
    @Test
    @Owner("Kozachek Y.N.")
    @Description("Проверка создания пользователя статус кода 201")
    public void checkStatusCode201() {
        softAssert = new SoftAssert();
        userFields = setUserFieldsFaker();

        loginStep.authorisation(user, password);
        createUserPage
                .open()
                .isPageOpened()
                .setValuesToCreateUser(userFields)
                .clickButtonPushToApi();

        assertEquals(createUserPage.getTextValueStatusCode(), "Status: Successfully pushed, code: 201",
                "Статус код не равен 201");
        softAssert.assertAll();
    }
}