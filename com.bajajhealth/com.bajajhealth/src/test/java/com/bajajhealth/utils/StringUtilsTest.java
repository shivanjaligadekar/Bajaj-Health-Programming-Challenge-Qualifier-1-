package com.bajajhealth.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testGenerateRandomStringLength() {
        int length = 8;
        String result = StringUtils.generateRandomString(length);
        assertEquals(length, result.length());
    }

    @Test
    void testGenerateRandomStringContent() {
        int length = 100;
        String result = StringUtils.generateRandomString(length);
        assertTrue(result.matches("[A-Za-z0-9]+"), "Should only contain alphanumeric characters");
    }

    @Test
    void testGenerateRandomStringUniqueness() {
        int length = 16;
        String first = StringUtils.generateRandomString(length);
        String second = StringUtils.generateRandomString(length);
        assertNotEquals(first, second, "Two generated strings should not be equal");
    }

    @Test
    void testGenerateRandomStringZeroLength() {
        String result = StringUtils.generateRandomString(0);
        assertEquals("", result);
    }

    // Add more tests as needed
}