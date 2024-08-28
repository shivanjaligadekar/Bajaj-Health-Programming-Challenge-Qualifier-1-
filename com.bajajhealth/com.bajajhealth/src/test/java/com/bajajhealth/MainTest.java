package com.bajajhealth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @TempDir
    Path tempDir;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testMainWithValidInput() throws Exception {
        Path jsonFile = tempDir.resolve("test.json");
        Files.writeString(jsonFile, "{\"destination\":\"testValue\"}");

        Main.main(new String[]{"testPRN", jsonFile.toString()});

        String output = outContent.toString().trim();
        assertTrue(output.matches("[a-f0-9]{32};[A-Za-z0-9]{8}"),
                "Output should be in the format of MD5 hash (32 hex chars) followed by ; and 8 alphanumeric chars");
    }

    @Test
    void testMainWithInvalidArguments() {
        Main.main(new String[]{"onlyOneArg"});
        assertTrue(outContent.toString().contains("Usage:"),
                "Should print usage information when incorrect number of arguments are provided");
    }

    // Add more tests as needed

    @org.junit.jupiter.api.AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}