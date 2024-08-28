package com.bajajhealth;

import com.bajajhealth.utils.StringUtils;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar app.jar <PRN_NUMBER> <JSON_FILE_PATH>");
            System.exit(1);
        }

        String prnNumber = args[0];
        String jsonFilePath = args[1];

        try {
            String destinationValue = JsonProcessor.findDestinationValue(Paths.get(jsonFilePath));
            String randomString = StringUtils.generateRandomString(8);
            String concatenatedString = prnNumber + destinationValue + randomString;
            String hashedValue = HashGenerator.generateMD5Hash(concatenatedString);

            System.out.println(hashedValue + ";" + randomString);
        } catch (Exception e) {
            System.err.println("Error processing the request: " + e.getMessage());
            System.exit(1);
        }
    }
}