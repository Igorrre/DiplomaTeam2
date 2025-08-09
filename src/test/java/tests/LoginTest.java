package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    @Description("Проверка, что пользователь может войти в систему, когда вводит валидные данные")
    public void checkLogin() {
        loginPage.open()
                .isPageOpened()
                .login(user, password);
        Assert.assertEquals(loginPage.getTextAlert(),
                "Successful authorization",
                "Ошибка:не удалось авторизоваться");
    }

    @Test
    @Description("Проверка, что пользователь не может войти в магазин, когда вводит невалидные данные")
    public void checkLoginWithNegativeValue() {
        loginPage.open()
                .isPageOpened()
                .login("test@pflb.ru", "test");
        Assert.assertEquals(loginPage.getTextAlert(),
                "Bad request",
                "Неверный текст ошибки");
    }

    @Test
    @Description("Проверка, что пользователь не может войти в систему с некорректным email")
    public void checkEmailField() {
        loginPage.open()
                .isPageOpened()
                .login("userpflb.ru", password);
        Assert.assertEquals(loginPage.getTextAlert(),
                "Incorrect input data",
                "Неверный текст ошибки");
    }

    @Test
    @Description("Проверка, что пользователь не может войти в систему с некорректным паролем")
    public void checkPasswordField() {
        loginPage.open()
                .isPageOpened()
                .login(user, "test");
        Assert.assertEquals(loginPage.getTextAlert(),
                "Bad request",
                "Неверный текст ошибки");
    }
}