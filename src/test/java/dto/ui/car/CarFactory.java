package dto.ui.car;

import com.github.javafaker.Faker;

public class CarFactory {
    public static Car getCar(String engine, String mark, String model ) {
        Faker faker = new Faker();
        return new Car(
                engine,
                mark,
                model,
                faker.number().numberBetween(500, 5000));
    }
}