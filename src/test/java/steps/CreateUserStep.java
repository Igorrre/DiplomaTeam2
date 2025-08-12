package steps;

import dto.UserFields;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CreateUserPage;

import static dto.UserFaker.setUserFieldsFaker;

public class CreateUserStep {

    WebDriver driver;
    CreateUserPage createUserPage;
    UserFields userFields;

    public CreateUserStep(WebDriver driver) {
        this.driver = driver;
        this.createUserPage = new CreateUserPage(driver);
    }

    @Step("Получение значения User ID на странице создания пользователя")
    public String getValueUserId() {
        userFields = setUserFieldsFaker();

        String valueId = createUserPage
                .open()
                .isPageOpened()
                .setValuesToCreateUser(userFields)
                .clickButtonPushToApi().getValueUserId();

        return valueId.trim();
    }
}