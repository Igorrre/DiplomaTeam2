package tests.ui;

import com.github.javafaker.Faker;
import dto.ui.car.Car;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static dto.ui.user.UserFactor.setUserFieldsFaker;

public class BuyCarTest extends BaseTest {

    SoftAssert softAssert;

    @Test (testName = "Покупка авто пользователем",
            description = "Проверка, что пользователь может купить авто")
    @Owner("Bulycheva D.A.")
    @Description("Проверка, что пользователь может купить авто")
    public void checkUserWithCar() {
        softAssert = new SoftAssert();
        Faker faker = new Faker();
        Car car = Car.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        loginStep.authorisation(user, password);
        userId = createUserStep.getValueUserId();
        createCarStep.createCar(car);
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
