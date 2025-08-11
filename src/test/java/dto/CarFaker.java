package dto;

import com.github.javafaker.Faker;

public class CarFaker {
    public static CarFields getCar
            (String engine, String mark, String model ) {
        Faker faker = new Faker();
        return new CarFields(
                engine,
                mark,
                model,
                faker.number().numberBetween(500, 5000));
    }
}