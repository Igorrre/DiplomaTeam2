package tests.ui;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static dto.ui.house.HouseFactory.getHouse;

public class HouseTest extends BaseTest{

    SoftAssert softAssert;

    @Test(testName = "Создание дома",
            priority = 1,
            description = "Позитивный тест Проверка сообщения, что дом создан")
    @Description("Проверка сообщения, что дом создан")
    public void createHouse() {
        softAssert = new SoftAssert();
        house = getHouse();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(house);
        softAssert.assertTrue(createHousePage.checkMessageIdHouse().contains("New house ID:"),
                "Дом не создан");
        softAssert.assertAll();
    }

    @Test(testName = "Создание дома с невалидными данными",
            priority = 2,
            description = "Негативный тест. Проверка сообщения, что дом не создан")
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

    @Test(testName = "Поиск дома в таблице",
            priority = 3,
            description = "Проверка, что созданный дом находится в таблице")
    @Description("Проверка, что созданный дом находится в таблице")
    public void checkIdHouseInTable() {
        softAssert = new SoftAssert();
        house = getHouse();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(house);
        houseId = createHousePage.getValueHouseId();
        readAllHousePage.open().isPageOpened();
        softAssert.assertTrue(readAllHousePage.findIdHouseInTable(houseId),
                "Дом отсутствует в таблице");
        softAssert.assertAll();
    }

    @Test(testName = "Удаление дома",
            priority = 4,
            description = "Проверка удаления дома")
    @Description("Проверка удаления дома")
    public void deleteHouse() {
        softAssert = new SoftAssert();
        house = getHouse();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(house);
        houseId = createHousePage.getValueHouseId();
        allDeletePage.open()
                .isPageOpened()
                .deleteTestHouseId(houseId);
        softAssert.assertEquals(allDeletePage.getMessageDeleteHouse(),
                "Status: 204",
                "Дом не удален");
        softAssert.assertAll();
    }
}