package com.nwnx.components;

import java.util.Optional;

public final class SystemProperties {
    private static final boolean DEV_MODE = Optional.ofNullable(System.getenv("spring.profiles.active"))
            .map(s -> s.equalsIgnoreCase("development"))
            .orElse(false);

    private SystemProperties() {
    }

    public static boolean isDevMode() {
        return DEV_MODE;
    }
}
