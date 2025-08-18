package tests.jdbc;

import adapters.UserAdapter;
import dto.api.user.CreateUserRequest;
import dto.api.user.CreateUserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.api.AuthorizationStep;
import tests.ui.BaseTest;
import utils.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();
    UserAdapter userAdapter = new UserAdapter();
    AuthorizationStep authorizationStep = new AuthorizationStep();

    @Test(testName = "БД-тест. Создание пользователя",
            description = "Проверка записи в БД при создании пользователя")
    @Owner("Bulycheva D.A.")
    @Description("Проверка пользователя в БД")
    public void checkCreateUserInDateBase() throws SQLException {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .age(35)
                .firstName("Moisha")
                .money(777888)
                .secondName("Zadnaja")
                .sex("MALE")
                .build();
        CreateUserResponse response = userAdapter.createUser(createUserRequest, authorizationStep.authorisationApi());
        int createdUserId = response.getId();
        DBConnection connection = new DBConnection();
        try {
            connection.connect();
            String query = "SELECT * FROM person WHERE id = " + createdUserId;
            ResultSet result = connection.select(query);
            if (result.next()) {
                softAssert.assertEquals(result.getInt("id"), createdUserId, "ID не совпадает");
                softAssert.assertEquals(result.getString("first_name"), createUserRequest.getFirstName(), "Имя не совпадает");
                softAssert.assertEquals(result.getString("second_name"), createUserRequest.getSecondName(), "Фамилия не совпадает");
                softAssert.assertEquals(result.getInt("age"), createUserRequest.getAge(), "Возраст не совпадает");
                softAssert.assertEquals(result.getBigDecimal("money").intValue(), createUserRequest.getMoney(), "Деньги не совпадают");
            } else {
                softAssert.fail("Пользователь не найден в БД");
            }
        } finally {
            connection.close();
        }
        softAssert.assertAll();
    }
}



