package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final String BASE_URL = "http://82.142.167.37:4881/";
    protected final String ALL_READ_CAR_URL = BASE_URL + "#/read/cars";
    protected final String CREATE_CARS_URL = BASE_URL + "#/create/cars";
    protected final String ALL_DELETE_URL = BASE_URL + "#/delete/all";
    protected final String CREATE_USER_URL = BASE_URL + "#/create/user";
    protected final String READ_ALL_USERS_URL = BASE_URL + "#/read/users";
    protected final String READ_USER_WITH_CARS_URL = BASE_URL + "#/read/userInfo";
    protected final String BUY_OR_SELL_CAR_URL = BASE_URL + "#/update/users/buyCar";
    protected final String ADD_MONEY_TO_USER = BASE_URL + "#/update/users/plusMoney";
    protected final String CREATE_HOUSE_URL = BASE_URL + "#/create/house";
    protected final String ALL_READ_HOUSE_URL = BASE_URL + "#/read/houses";

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