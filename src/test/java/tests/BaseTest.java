package tests;

import dto.UserFields;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import pages.*;
import steps.CreateCarStep;
import steps.CreateUserStep;
import steps.LoginStep;
import utils.PropertyReader;
import java.time.Duration;
import java.util.HashMap;
import static utils.AllureUtils.takeScreenshot;

public class BaseTest {

    WebDriver driver;
    UserFields userFields;
    LoginPage loginPage;
    LoginStep loginStep;
    CreateCarStep createCarStep;
    //HousesPage housesPage;
    CreateCarsPage createCarsPage;
    ReadAllCarsPage readAllCarsPage;
    AllDeletePage allDeletePage;
    //UsersPage usersPage;
    CreateUserPage createUserPage;
    CreateUserStep createUserStep;
    ReadAllUsersPage readAllUsersPage;
    String valueId;
    String carId;
    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod(alwaysRun = true, description = "Настройка браузера")
    public void setup(@Optional("chrome") String browser, ITestContext iTestContext) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            //options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            driver = new EdgeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        iTestContext.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        loginStep = new LoginStep(driver);
        createCarStep = new CreateCarStep(driver);
        readAllCarsPage = new ReadAllCarsPage(driver);
        createUserPage = new CreateUserPage(driver);
        createUserStep = new CreateUserStep(driver);
        readAllUsersPage = new ReadAllUsersPage(driver);
        allDeletePage = new AllDeletePage(driver);
        createCarsPage = new CreateCarsPage(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}