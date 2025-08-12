package tests;

import com.github.javafaker.Faker;
import dto.CarFields;
import dto.SaveTestId;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static dto.UserFaker.setUserFieldsFaker;

public class BuyCarTest extends BaseTest {

    @Test
    @Description("Проверка, что пользователь может купить авто")
    public void checkUserWithCar() {
        SoftAssert softAssert;
        softAssert = new SoftAssert();
        userFields = setUserFieldsFaker();
        loginStep.authorisation(user, password);
        valueId = createUserStep.getValueUserId();
        SaveTestId id = new SaveTestId();
        Faker faker = new Faker();
        CarFields carFields = CarFields.builder()
                .engine("Electric")
                .mark("tesla")
                .model("model s")
                .price(faker.number().numberBetween(500, 5000))
                .build();
        createCarStep.createCar(carFields);
        savedId = createCarsPage.getValueCarId();
        buyOrSellCarPage.open()
            .isPageOpened()
            .setValuesToBuyCar(valueId, savedId)
            .clickButtonPushToApi();
        softAssert.assertEquals(buyOrSellCarPage.getTextValueStatusCode(),
            "Status: Successfully pushed, code: 200",
            "Ошибка: авто не куплено");
        readUserWithCarsPage.open()
            .isPageOpened()
            .inputIdUser(valueId)
            .clickButtonRead();
        softAssert.assertEquals(readUserWithCarsPage.getUserId(), valueId, "Пользователь не найден");
        softAssert.assertEquals(readUserWithCarsPage.getCarId(), savedId, "У пользователя отсутствует авто");
        softAssert.assertAll();
    }
}
