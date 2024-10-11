package com.onkonfeton;

import com.onkonfeton.map.WorldMap;

import java.util.Scanner;

public class MapConstructor {

    private static final int MIN_MAP_SIZE = 10;
    private static final int MAX_MAP_SIZE = 20;

    private final Scanner scanner = new Scanner(System.in);

    public WorldMap construct() {
        System.out.printf("Введите размер карты (в пределах от %d до %d)\n", MIN_MAP_SIZE, MAX_MAP_SIZE);

        System.out.println("Введите высоту карты");
        int maxX = getUserInput();

        System.out.println("Введите длинну карты");
        int maxY = getUserInput();

        return new WorldMap(maxX, maxY);
    }

    private int getUserInput() {

        while (true) {
            String input = scanner.nextLine();
            if (isInteger(input)) {
                int maxSize = Integer.parseInt(input);
                if (maxSize >= MIN_MAP_SIZE && maxSize <= MAX_MAP_SIZE) {
                    return maxSize;
                }
                System.out.printf("Число не в диапазоне от %d до %d\n", MIN_MAP_SIZE, MAX_MAP_SIZE);
            }
        }
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Введите число");
            return false;
        }
    }
}
