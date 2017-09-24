package com.nwnx.components;

import java.util.Optional;
import java.util.function.Supplier;

public final class SystemProperties {
    private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
    private static final String DEVELOPMENT = "development";
    private static final String PRODUCTION = "production";

    private static final boolean DEV_MODE = Optional.ofNullable(System.getenv(SPRING_PROFILES_ACTIVE))
            .map(DEVELOPMENT::equalsIgnoreCase)
            .orElseGet(isDevProfileInSystemProperty());

    private static Supplier<Boolean> isDevProfileInSystemProperty() {
        return () -> System.getProperty(SPRING_PROFILES_ACTIVE, PRODUCTION).equalsIgnoreCase(DEVELOPMENT);
    }

    private SystemProperties() {
    }

    public static boolean isDevMode() {
        return DEV_MODE;
    }
}
