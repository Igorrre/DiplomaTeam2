package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static dto.UserFaker.setUserFieldsFaker;


public class AllDeleteUserTest extends BaseTest {

    @Test
    @Owner("Kozachek Y.N.")
    @Description("Проверка удаления пользователя код 204 и отсутствие в таблице")
    public void checkStatusCode204() {

        userFields = setUserFieldsFaker();

        loginStep.authorisation(user, password);
        valueId = createUserStep.getValueUserId();
        allDeletePage.open().isPageOpened().deleteUserById(valueId);

        softAssert.assertEquals(allDeletePage.getMessageDeleteUser(), "Status: 204", "Статус код не равен 204");
        softAssert.assertFalse(readAllUsersPage.open().findIdInTable(valueId), "Пользователь присутствует в таблице");
    }
}