package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static dto.ui.user.UserFactor.setUserFieldsFaker;

public class AllDeleteUserTest extends BaseTest {

    SoftAssert softAssert;

    @Test(testName = "Удаление пользователя в таблице",
            description = "Проверка удаления пользователя код 204 и отсутствие в таблице")
    @Owner("Kozachek Y.N.")
    @Description("Проверка удаления пользователя код 204 и отсутствие в таблице")
    public void checkStatusCode204() {
        softAssert = new SoftAssert();
        users = setUserFieldsFaker();
        loginStep.authorisation(user, password);
        userId = createUserStep
                .getValueUserId();
        allDeletePage
                .open()
                .isPageOpened()
                .deleteUserById(userId);
        softAssert.assertEquals(allDeletePage.getMessageDeleteUser(),
                "Status: 204",
                "Статус код не равен 204");
        softAssert.assertFalse(readAllUsersPage.open().findIdInTable(userId),
                "Пользователь присутствует в таблице");
        softAssert.assertAll();
    }
}