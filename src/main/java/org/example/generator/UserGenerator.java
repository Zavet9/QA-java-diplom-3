package org.example.generator;

import com.github.javafaker.Faker;
import org.example.model.User;

public class UserGenerator {
    public static User getRandom() {
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress();
        return new User(email, password, name);
    }
}
