package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final
    String BASE_URI = "http://82.142.167.37:4881/";
    String ALL_READ_CAR_URL = BASE_URI + "#/read/cars";
    String CREATE_CARS_URL = BASE_URI + "#/create/cars";
    String ALL_DELETE_URL = BASE_URI + "#/delete/all";
    String CREATE_USER_URL = BASE_URI + "#/create/user";
    String READ_ALL_USERS_URL = BASE_URI + "#/read/users";
    String READ_USER_WITH_CARS_URL = BASE_URI + "#/read/userInfo";
    String BUY_OR_SELL_CAR_URL = BASE_URI + "#/update/users/buyCar";
    String ADD_MONEY_TO_USER = BASE_URI + "#/update/users/plusMoney";
    String CREATE_HOUSE_URL = BASE_URI + "#/create/house";
    String ALL_READ_HOUSE_URL = BASE_URI + "#/read/houses";

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