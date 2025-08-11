package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static dto.UserFaker.setUserFieldsFaker;

public class ReadAllUsersTest extends BaseTest {
    SoftAssert softAssert;
    @Test
    @Description("Проверка, что созданный пользователь находится в таблице")
    public void checkIfUserIsInTable() {
        softAssert = new SoftAssert();
        userFields = setUserFieldsFaker();
        loginStep.authorisation(user, password);
        valueId = createUserStep.getValueUserId();
        readAllUsersPage.open().isPageOpened();
        softAssert.assertTrue(readAllUsersPage.findIdInTable(valueId), "Пользователь отсутствует в таблице");
        softAssert.assertAll();
    }
}