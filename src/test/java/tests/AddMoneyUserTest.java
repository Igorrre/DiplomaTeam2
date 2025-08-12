package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static dto.UserFaker.setUserFieldsFaker;

public class AddMoneyUserTest extends BaseTest {

    String addMoney = "10";

    @Test
    @Owner("Kozachek Y.N.")
    @Description("Проверка добавление денег пользователю")
    public void checkAddNewMoneyByUser() {

        userFields = setUserFieldsFaker();

        loginStep.authorisation(user, password);
        valueId = createUserStep.getValueUserId();
        moneyUp = createUserPage.getMoneyValue();
        newMoneyValue = addMoneyUserPage.open().isPageOpened().setUserId(valueId).setMoney(addMoney).clickButtonPushToApi().getNewAmountOfMoney();

        softAssert.assertEquals(addMoneyUserPage.getTextValueStatusCode(), "Status: Successfully pushed, code: 200",
                "Статус код не равен 200");
        softAssert.assertEquals(Integer.parseInt(newMoneyValue), Integer.parseInt(moneyUp) + Integer.parseInt(addMoney),
                "Не сошлись деньгами");
    }
}