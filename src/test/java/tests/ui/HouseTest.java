package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SettleToHousePage;

import static dto.ui.house.HouseFactory.getHouse;
import static dto.ui.user.UserFactor.setUserFieldsFaker;

public class HouseTest extends BaseTest{

    SoftAssert softAssert;

    @Test(priority = 1, description = "Позитивный тест Проверка сообщения, что дом создан")
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

    @Test(priority = 2, description = "Негативный тест. Проверка сообщения, что дом не создан")
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

    @Test(priority = 3, description = "Проверка удаления дома")
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

    @Test(priority = 4, description = "Проверка, что созданный дом находится в таблице")
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

    @Test(priority = 5, description = "Проверка на заселение пользователя в дом")
    @Owner("Bazhenov Y.N.")
    @Description("Проверка статус-кода при успешном заселения пользователя")
    public void checkStatusIfUserSettleInHouse() {
        softAssert = new SoftAssert();
        users = setUserFieldsFaker();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(house);
        houseId = createHousePage.getValueHouseId();
        userId = createUserStep
                .getValueUserId();
        settleToHouseStep.settleToHouse(userId, houseId);
        softAssert.assertEquals(settleToHousePage.checkMessage(), "Status: Successfully pushed, code: 200", "Статус-код неверный");
        softAssert.assertAll();
    }
    @Test(priority = 6, description = "Проверка на выселения пользователя в дом")
    @Owner("Bazhenov Y.N.")
    @Description("Проверка статус-кода при успешном выселении пользователя")
    public void checkStatusIfUserSEvictInHouse() {
        softAssert = new SoftAssert();
        users = setUserFieldsFaker();
        house = getHouse();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(house);
        houseId = createHousePage.getValueHouseId();
        userId = createUserStep
                .getValueUserId();
        settleToHouseStep.evictToHouse(userId, houseId);
        softAssert.assertEquals(settleToHousePage.checkMessage(), "Status: Successfully pushed, code: 200", "Статус-код неверный");
        softAssert.assertAll();
    }
    @Test(priority = 7, description = "Проверка привязки пользователя к дому после заселения")
    @Owner("Bazhenov Y.N.")
    @Description("Поиск пользователя в таблице Read one by ID")
    public void checkUserIDInTableReadOneById() {
        softAssert = new SoftAssert();
        users = setUserFieldsFaker();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(house);
        houseId = createHousePage.getValueHouseId();
        userId = createUserStep
                .getValueUserId();
        settleToHouseStep.settleToHouse(userId, houseId);
        readIDHousePage.open()
                .isPageOpened()
                .inputIdHouse(houseId)
                .clickButtonReadHouse();
        softAssert.assertTrue(readAllHousePage.findIdHouseInTable(userId),"Пользователь не заселен в дом");
        softAssert.assertAll();
    }
    @Test(priority = 6, description = "Проверка привязки пользователя к дому после выселения")
    @Owner("Bazhenov Y.N.")
    @Description("Поиск пользователя в таблице Read one by ID после выселения")
    public void checkUserIdInTableReadOneByIdIsEmpty() {
        softAssert = new SoftAssert();
        users = setUserFieldsFaker();
        loginStep.authorisation(user, password);
        createHouseStep.createHouse(house);
        houseId = createHousePage.getValueHouseId();
        userId = createUserStep
                .getValueUserId();
        settleToHouseStep.evictToHouse(userId, houseId);
        readIDHousePage.open()
                .isPageOpened()
                .inputIdHouse(houseId)
                .clickButtonReadHouse();
        softAssert.assertFalse(readAllHousePage.findIdHouseInTable(userId),"Пользователь не выселен в дом");
        softAssert.assertAll();
    }
}
