package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static dto.UserFaker.setUserFieldsFaker;

public class AddMoneyUserTest extends BaseTest {
    SoftAssert softAssert;
    String addMoney = "10";
    @Test
    @Owner("Kozachek Y.N.")
    @Description("Проверка добавление денег пользователю")
    public void checkAddNewMoneyByUser() {
        softAssert = new SoftAssert();
        userFields = setUserFieldsFaker();
        loginStep.authorisation(user, password);
        userId = createUserStep.getValueUserId();
        moneyUp = createUserPage.getMoneyValue();
        newMoneyValue = addMoneyUserPage
                .open()
                .isPageOpened()
                .setUserId(userId)
                .setMoney(addMoney)
                .clickButtonPushToApi()
                .getNewAmountOfMoney();
        softAssert.assertEquals(addMoneyUserPage.getTextValueStatusCode(),
                "Status: Successfully pushed, code: 200",
                "Статус код не равен 200");
        softAssert.assertEquals(Integer.parseInt(newMoneyValue), Integer.parseInt(moneyUp) + Integer.parseInt(addMoney),
                "Не сошлись деньгами");
        softAssert.assertAll();
    }
}