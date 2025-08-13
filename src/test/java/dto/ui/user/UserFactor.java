package dto.ui.user;

import com.github.javafaker.Faker;

public class UserFactor {

    public static Users setUserFieldsFaker() {
        Faker faker = new Faker();
        return new Users(faker.name().firstName(), faker.name().lastName(), faker.number().numberBetween(1, 100), faker.number().numberBetween(500000, 999999));
    }
}