package steps.ui;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginStep {

    WebDriver driver;
    LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        //productsPage = new ProductsPage(driver);
    }

    public void authorisation (String user, String password) {
        loginPage.open()
                .isPageOpened()
                .login(user, password)
                .acceptAlert()
                .isPageOpened();
    }
}