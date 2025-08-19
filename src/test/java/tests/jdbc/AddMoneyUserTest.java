package tests.jdbc;

import adapters.UserAdapter;
import database.DBConnection;
import dto.api.user.AddMoneyUserResponse;
import dto.api.user.CreateUserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.api.AuthorizationStep;
import steps.api.CreateUserStep;
import tests.ui.BaseTest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMoneyUserTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    UserAdapter userAdapter = new UserAdapter();
    CreateUserStep createUserStep = new CreateUserStep();
    AuthorizationStep authorizationStep = new AuthorizationStep();
    DBConnection connection = new DBConnection();
    int money = 10;

    @Test(testName = "Проверка добавления денег пользователю в БД",
            description = "Проверка добавления денег пользователю в БД")
    @Owner("Kozachek Y.N.")
    @Description("Проверка добавления денег пользователю в БД")
    public void checkAddMoneyUserInDateBase() throws SQLException {

        CreateUserResponse createUserStepResponse = createUserStep.createUserGetID();
        AddMoneyUserResponse response = userAdapter.addMoneyUser(authorizationStep.authorisationApi(), createUserStepResponse.getId(), money);

        connection.connect();

        ResultSet result = connection.select("SELECT * FROM person WHERE id = " + response.getId());

        while (result.next()) {
            softAssert.assertEquals(result.getInt("id"), createUserStepResponse.getId());
            softAssert.assertEquals(result.getString("first_name"), createUserStepResponse.getFirstName());
            softAssert.assertEquals(result.getString("second_name"), createUserStepResponse.getSecondName());
            softAssert.assertEquals(result.getInt("age"), createUserStepResponse.getAge());
            softAssert.assertEquals(result.getString("sex"), "t");
            softAssert.assertEquals(result.getBigDecimal("money").intValue(), createUserStepResponse.getMoney() + money);
            softAssert.assertAll();
        }
        connection.close();
    }
}
