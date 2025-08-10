package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final String BASE_URL = "http://82.142.167.37:4881/";
    protected final String CREATE_USER_URL = "http://82.142.167.37:4881/#/create/user";
    protected final String READ_ALL_USERS_URL = "http://82.142.167.37:4881/#/read/users";
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    public abstract BasePage isPageOpened();
}
