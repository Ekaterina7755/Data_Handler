package com.yourpackage;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод пути к файлам от пользователя
        System.out.print("Введите путь к входному файлу: ");
        String inputFilePath = scanner.nextLine();

        if (!Files.exists(Paths.get(inputFilePath))) {
            System.out.println("Файл не существует. Завершение программы.");
            return;
        }

        System.out.print("Введите путь к выходному файлу: ");
        String outputFilePath = scanner.nextLine();

        System.out.print("Хотите ли вы вывести данные в формате JSON? (да/нет): ");
        boolean asJson = scanner.nextLine().equalsIgnoreCase("да");

        System.out.print("Введите минимальное значение для фильтрации: ");
        int minValue;
        try {
            minValue = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректное число. Завершение программы.");
            return;
        }

        // Чтение данных из файла
        List<DataRecord> records = EnhancedDataProcessor.readDataFromFile(inputFilePath);

        if (records.isEmpty()) {
            System.out.println("В файле не найдено допустимых данных.");
            return;
        }

        // Сортировка и фильтрация данных
        List<DataRecord> sortedAndFiltered = records.stream()
                .sorted()
                .filter(record -> record.getValue() >= minValue)
                .collect(Collectors.toList());

        // Вывод результатов
        System.out.println("\nИсходные данные:");
        records.forEach(System.out::println);

        System.out.println("\nОтсортированные и отфильтрованные данные:");
        sortedAndFiltered.forEach(System.out::println);

        // Запись отфильтрованных данных в выходной файл
        EnhancedDataProcessor.writeDataToFile(outputFilePath, sortedAndFiltered, asJson);
        System.out.println("\nОбработанные данные сохранены в файл: " + outputFilePath);
    }
}
