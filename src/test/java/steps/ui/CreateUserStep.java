package steps.ui;

import dto.ui.user.Users;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CreateUserPage;

import static dto.ui.user.UserFactor.setUserFieldsFaker;

public class CreateUserStep {

    WebDriver driver;
    CreateUserPage createUserPage;
    Users users;

    public CreateUserStep(WebDriver driver) {
        this.driver = driver;
        this.createUserPage = new CreateUserPage(driver);
    }

    @Step("Получение значения User ID на странице создания пользователя")
    public String getValueUserId() {
        users = setUserFieldsFaker();

        String valueId = createUserPage
                .open()
                .isPageOpened()
                .setValuesToCreateUser(users)
                .clickButtonPushToApi().getValueUserId();

        return valueId.trim();
    }
}