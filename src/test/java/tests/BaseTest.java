package tests;

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
import org.testng.asserts.SoftAssert;
import pages.CarsPage;
import pages.HousesPage;
import pages.LoginPage;
import pages.UsersPage;

import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    WebDriver driver;
    SoftAssert softAssert;
    LoginPage loginPage;
    HousesPage housesPage;
    CarsPage carsPage;
    UsersPage usersPage;
    String user = System.getProperty("user");
    String password = System.getProperty("password");

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
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        iTestContext.setAttribute("driver", driver);
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        //housesPage = new HousesPage(driver);
        //carsPage = new CarsPage(driver);
        //usersPage = new UsersPage(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDown(ITestResult result) {
        driver.quit();
    }
}
