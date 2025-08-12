package tests.ui;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static dto.HouseFaker.getHouse;

public class HouseTest extends BaseTest{
    SoftAssert softAssert;
    @Test(priority = 1, testName = "Позитивный тест Проверка сообщения, что дом создан")
    @Description("Проверка сообщения, что дом создан")
    public void createHouse() {
        softAssert = new SoftAssert();
        houseFields = getHouse();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(houseFields);
        softAssert.assertTrue(createHousePage.checkMessageIdHouse().contains("New house ID:"),
                "Дом не создан");
        softAssert.assertEquals(createHousePage.checkMessageCreateHouse(),
                "Status: Successfully pushed, code: 201",
                "house ID: не создано");
        softAssert.assertAll();
    }

    @Test(priority = 2, testName = "Негативный тест. Проверка сообщения, что дом не создан")
    @Description("Проверка сообщения, что дом не создан")
    public void createHouseNotAllFieldCheck() {
        softAssert = new SoftAssert();
        loginStep.authorisation(user, password);
        createHousePage.open()
                .isPageOpened()
                .clickCreateHouse();
        softAssert.assertEquals(createHousePage.checkMessageCreateHouse(),
                "Status: Invalid input data",
                "Дом не создан");
        softAssert.assertAll();
    }

    @Test(priority = 3, testName = "Проверка удаления дома")
    @Description("Проверка удаления дома")
    public void deleteHouse() {
        softAssert = new SoftAssert();
        houseFields = getHouse();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(houseFields);
        houseId = createHousePage.getValueHouseId();
        allDeletePage.open()
                .isPageOpened()
                .deleteTestHouseId(houseId);
        softAssert.assertEquals(allDeletePage.getMessageDeleteHouse(),
                "Status: 204",
                "Дом не удален");
        softAssert.assertAll();
    }

    @Test(priority = 4, testName = "Проверка, что созданный дом находится в таблице")
    @Description("Проверка, что созданный дом находится в таблице")
    public void checkIdHouseInTable() {
        softAssert = new SoftAssert();
        houseFields = getHouse();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(houseFields);
        houseId = createHousePage.getValueHouseId();
        readAllHousePage.open().isPageOpened();
        softAssert.assertTrue(readAllHousePage.findIdHouseInTable(houseId),
                "Дом отсутствует в таблице");
        softAssert.assertAll();
    }
}