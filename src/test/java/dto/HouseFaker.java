package dto;

import com.github.javafaker.Faker;

public class HouseFaker {
    public static HouseFields getHouse() {
        Faker faker = new Faker();
        return new HouseFields(
                faker.number().numberBetween(0, 5),
                faker.number().numberBetween(500, 5000),
                faker.number().numberBetween(0, 5),
                faker.number().numberBetween(0, 5),
                faker.number().numberBetween(0, 5),
                faker.number().numberBetween(0, 5));
    }
}