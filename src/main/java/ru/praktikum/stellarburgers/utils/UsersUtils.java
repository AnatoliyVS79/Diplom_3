package ru.praktikum.stellarburgers.utils;

import org.apache.commons.lang3.RandomStringUtils;
import ru.praktikum.stellarburgers.pojos.UserRequest;

public class UsersUtils {
    public static UserRequest getUniqueUser() {
        return new UserRequest(
                RandomStringUtils.randomAlphabetic(10) + "@gmail.com",
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)
        );
    }
}
