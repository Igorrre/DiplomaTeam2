package pageModels;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    public SoftAssert softAssert;
    public UserPage userPage;

    @BeforeMethod()
    public void setUp() {
        softAssert = new SoftAssert();
        userPage = new UserPage();
    }

    @AfterMethod()
    public void tearDown() {
        softAssert.assertAll();
    }
}