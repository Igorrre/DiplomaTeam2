package dto;

import com.github.javafaker.Faker;

public class UserFaker {

    public static UserFields setUserFieldsFaker() {
        Faker faker = new Faker();
        return new UserFields(faker.name().firstName(), faker.name().lastName(), faker.number().numberBetween(1, 100), faker.number().numberBetween(500000, 999999));
    }
}