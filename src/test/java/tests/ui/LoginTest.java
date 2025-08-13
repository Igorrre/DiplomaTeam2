package tests.ui;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {
    SoftAssert softAssert;

    @Test
    @Description("Проверка, что пользователь может войти в систему, когда вводит валидные данные")
    public void checkLogin() {
        softAssert = new SoftAssert();
        loginPage.open()
                .isPageOpened()
                .login(user, password);
        softAssert.assertEquals(loginPage.getTextAlert(),
                "Successful authorization",
                "Ошибка:не удалось авторизоваться");
        softAssert.assertAll();
    }

    @Test
    @Description("Проверка, что пользователь не может войти в магазин, когда вводит невалидные данные")
    public void checkLoginWithNegativeValue() {
        softAssert = new SoftAssert();
        loginPage.open()
                .isPageOpened()
                .login("test@pflb.ru", "test");
        softAssert.assertEquals(loginPage.getTextAlert(),
                "Bad request",
                "Неверный текст ошибки");
        softAssert.assertAll();
    }

    @Test
    @Description("Проверка, что пользователь не может войти в систему с некорректным email")
    public void checkEmailField() {
        softAssert = new SoftAssert();
        loginPage.open()
                .isPageOpened()
                .login("userpflb.ru", password);
        softAssert.assertEquals(loginPage.getTextAlert(),
                "Incorrect input data",
                "Неверный текст ошибки");
        softAssert.assertAll();
    }

    @Test
    @Description("Проверка, что пользователь не может войти в систему с некорректным паролем")
    public void checkPasswordField() {
        softAssert = new SoftAssert();
        loginPage.open()
                .isPageOpened()
                .login(user, "test");
        softAssert.assertEquals(loginPage.getTextAlert(),
                "Bad request",
                "Неверный текст ошибки");
        softAssert.assertAll();
    }
}