package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static dto.ui.user.UserFactor.setUserFieldsFaker;

public class ReadAllUsersTest extends BaseTest {

    SoftAssert softAssert;

    @Test
    @Owner("Kozachek Y.N.")
    @Description("Проверка, что созданный пользователь находится в таблице")
    public void checkIfUserIsInTable() {
        softAssert = new SoftAssert();
        users = setUserFieldsFaker();
        loginStep.authorisation(user, password);
        userId = createUserStep.getValueUserId();
        readAllUsersPage.open()
                .isPageOpened();
        softAssert.assertTrue(readAllUsersPage.findIdInTable(userId),
                "Пользователь отсутствует в таблице");
        softAssert.assertAll();
    }
}