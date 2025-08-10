package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static dto.UserFaker.setUserFieldsFaker;
import static org.testng.Assert.assertTrue;

public class ReadAllTest extends BaseTest {

    @Test
    @Description("Проверка, что созданный пользователь находится в таблице")
    public void checkIfUserIsInTable() {

        userFields = setUserFieldsFaker();

        loginStep.authorisation(user, password);

        valueId = createUserStep.getValueUserId();

        readAllPage.open().isPageOpened();

        assertTrue(readAllPage.findIdInTable(valueId), "Пользователь отсутствует в таблице");
    }
}