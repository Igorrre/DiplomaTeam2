package tests.ui;

import com.github.javafaker.Faker;
import dto.CarFields;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static dto.UserFaker.setUserFieldsFaker;

public class BuyCarTest extends BaseTest {
    SoftAssert softAssert;
    @Test
    @Description("Проверка, что пользователь может купить авто")
    public void checkUserWithCar() {
        softAssert = new SoftAssert();
        userFields = setUserFieldsFaker();
        Faker faker = new Faker();
        CarFields carFields = CarFields.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        userId = createUserStep.getValueUserId();
        createCarStep.createCar(carFields);
        carId = createCarsPage.getValueCarId();
        buyOrSellCarPage.open()
            .isPageOpened()
            .setValuesToBuyCar(userId, carId)
            .clickButtonPushToApi();
        softAssert.assertEquals(buyOrSellCarPage.getTextValueStatusCode(),
            "Status: Successfully pushed, code: 200",
            "Ошибка: авто не куплено");
        readUserWithCarsPage.open()
            .isPageOpened()
            .inputIdUser(userId)
            .clickButtonRead();
        softAssert.assertEquals(readUserWithCarsPage.getUserId(), userId, "Пользователь не найден");
        softAssert.assertEquals(readUserWithCarsPage.getCarId(), carId, "У пользователя отсутствует авто");
        softAssert.assertAll();
    }
}
