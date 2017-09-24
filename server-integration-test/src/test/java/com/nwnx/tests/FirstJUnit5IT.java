package com.nwnx.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FirstJUnit5IT {

    @Test
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }
}
