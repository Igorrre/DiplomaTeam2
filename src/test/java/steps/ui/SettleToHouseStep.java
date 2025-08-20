package steps.ui;

import org.openqa.selenium.WebDriver;
import pages.SettleToHousePage;

public class SettleToHouseStep {

    WebDriver driver;
    SettleToHousePage settleToHousePage;

    public SettleToHouseStep(WebDriver driver) {
        this.driver = driver;
        settleToHousePage = new SettleToHousePage(driver);
    }

    public void settleToHouse(String user, String house) {
        settleToHousePage.open()
                .isPageOpened()
                .inputIdUser(user)
                .inputIdHouse(house)
                .clickSettle()
                .clickButtonAction()
                .checkMessage();
    }
    public void evictToHouse(String user, String house) {
        settleToHousePage.open()
                .isPageOpened()
                .inputIdUser(user)
                .inputIdHouse(house)
                .clickEvict()
                .clickButtonAction();
    }
}