package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static dto.UserFaker.setUserFieldsFaker;
import static org.testng.Assert.assertTrue;

public class ReadAllUsersTest extends BaseTest {

    @Test
    @Owner("Kozachek Y.N.")
    @Description("Проверка, что созданный пользователь находится в таблице")
    public void checkIfUserIsInTable() {

        userFields = setUserFieldsFaker();

        loginStep.authorisation(user, password);
        valueId = createUserStep.getValueUserId();
        readAllUsersPage.open().isPageOpened();

        assertTrue(readAllUsersPage.findIdInTable(valueId), "Пользователь отсутствует в таблице");
    }
}