package utils;

import entity.User;
import utils.PropertyReader;

public class Preconditions {
    protected final User userWithEmptyEmail = User.builder()
            .email("")
            .build();

    protected final User userSuccess = User.builder()
            .email(PropertyReader.getProperty("email"))
            .build();

    protected final User userIncorrectFields = User.builder()
            .email("refjebfle")
            .build();
}
