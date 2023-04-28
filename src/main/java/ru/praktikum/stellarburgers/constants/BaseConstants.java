package ru.praktikum.stellarburgers.constants;

import ru.praktikum.stellarburgers.utils.ConfigFileReader;

public class BaseConstants {
    public final static String BASE_TEST_URL = new ConfigFileReader().getApplicationUrl();
}
