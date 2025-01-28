package com.yourpackage;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;

public class EnhancedDataProcessor {
    private static final Logger logger = Logger.getLogger(EnhancedDataProcessor.class.getName());

    public static List<DataRecord> readDataFromFile(String filePath) {
        List<DataRecord> records = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    logger.warning("Пропуск некорректной строки: " + line);
                    continue;
                }
                try {
                    String name = parts[0].trim();
                    int value = Integer.parseInt(parts[1].trim());
                    records.add(new DataRecord(name, value));
                } catch (NumberFormatException e) {
                    logger.warning("Некорректный формат числа в строке: " + line);
                }
            }
        } catch (IOException e) {
            logger.severe("Ошибка при чтении файла: " + e.getMessage());
        }

        return records;
    }

    public static void writeDataToFile(String filePath, List<DataRecord> records, boolean asJson) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            if (asJson) {
                Gson gson = new Gson();
                writer.write(gson.toJson(records));
            } else {
                for (DataRecord record : records) {
                    writer.write(record.getName() + "," + record.getValue());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            logger.severe("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
