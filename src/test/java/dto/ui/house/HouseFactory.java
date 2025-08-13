package dto.ui.house;

import com.github.javafaker.Faker;

public class HouseFactory {
    public static House getHouse() {
        Faker faker = new Faker();
        return new House(
                faker.number().numberBetween(1, 5),
                faker.number().numberBetween(500, 5000),
                faker.number().numberBetween(0, 5),
                faker.number().numberBetween(0, 5),
                faker.number().numberBetween(0, 5),
                faker.number().numberBetween(0, 5));
    }
}