package com.bajajhealth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class JsonProcessorTest {

    @TempDir
    Path tempDir;

    @Test
    void testFindDestinationValueSimpleJson() throws Exception {
        Path jsonFile = tempDir.resolve("simple.json");
        Files.writeString(jsonFile, "{\"destination\":\"testValue\"}");

        String result = JsonProcessor.findDestinationValue(jsonFile);
        assertEquals("testValue", result);
    }

    @Test
    void testFindDestinationValueNestedJson() throws Exception {
        Path jsonFile = tempDir.resolve("nested.json");
        Files.writeString(jsonFile, "{\"key\":{\"destination\":\"nestedValue\"}}");

        String result = JsonProcessor.findDestinationValue(jsonFile);
        assertEquals("nestedValue", result);
    }

    @Test
    void testFindDestinationValueInArray() throws Exception {
        Path jsonFile = tempDir.resolve("array.json");
        Files.writeString(jsonFile, "[{\"key\":1},{\"destination\":\"arrayValue\"}]");

        String result = JsonProcessor.findDestinationValue(jsonFile);
        assertEquals("arrayValue", result);
    }

    @Test
    void testFindDestinationValueNotFound() throws Exception {
        Path jsonFile = tempDir.resolve("notfound.json");
        Files.writeString(jsonFile, "{\"key\":\"value\"}");

        String result = JsonProcessor.findDestinationValue(jsonFile);
        assertNull(result);
    }

    // Add more tests as needed
}