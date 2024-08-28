package com.bajajhealth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import java.io.IOException;

public class JsonProcessor {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String findDestinationValue(Path jsonFilePath) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonFilePath.toFile());
        return findDestination(rootNode);
    }

    private static String findDestination(JsonNode node) {
        if (node.isObject()) {
            if (node.has("destination")) {
                return node.get("destination").asText();
            }
            for (JsonNode child : node) {
                String result = findDestination(child);
                if (result != null) {
                    return result;
                }
            }
        } else if (node.isArray()) {
            for (JsonNode element : node) {
                String result = findDestination(element);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}